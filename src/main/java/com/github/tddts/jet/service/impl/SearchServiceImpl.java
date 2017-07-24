/*
 * Copyright 2017 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.jet.service.impl;

import com.github.tddts.jet.config.spring.annotations.Profiling;
import com.github.tddts.jet.consts.DotlanRouteOption;
import com.github.tddts.jet.consts.OrderType;
import com.github.tddts.jet.dao.MarketPriceDao;
import com.github.tddts.jet.dao.OrderDao;
import com.github.tddts.jet.dao.StationDao;
import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.jet.model.app.SearchParams;
import com.github.tddts.jet.model.client.dotlan.DotlanRoute;
import com.github.tddts.jet.model.client.esi.market.MarketOrder;
import com.github.tddts.jet.model.client.esi.universe.UniverseName;
import com.github.tddts.jet.model.db.CachedMarketPrice;
import com.github.tddts.jet.model.db.CachedOrder;
import com.github.tddts.jet.model.db.ResultOrder;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.esi.MarketClient;
import com.github.tddts.jet.rest.client.esi.UniverseClient;
import com.github.tddts.jet.service.DotlanService;
import com.github.tddts.jet.service.SearchService;
import com.github.tddts.jet.tools.filter.ResultOrderFilter;
import com.github.tddts.jet.tools.pagination.PaginationExecutor;
import com.github.tddts.tools.core.pagination.Pagination;
import com.github.tddts.tools.core.pagination.impl.PaginationBuilder;
import com.github.tddts.tools.core.task.TaskChain;
import com.github.tddts.tools.core.task.impl.ReusableTaskChain;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.tddts.jet.context.events.SearchStatusEvent.*;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class SearchServiceImpl implements SearchService {

  @Autowired
  private OrderDao orderDao;
  @Autowired
  private MarketPriceDao marketPriceDao;
  @Autowired
  private StationDao stationDao;
  @Autowired
  private MarketClient marketClient;
  @Autowired
  private DotlanService dotlanService;
  @Autowired
  private UniverseClient universeClient;

  @Autowired
  private EventBus eventBus;

  @Value("#{${static.regions}}")
  private Map<String, Integer> regionsMap;

  @Value("${service.search.expiration.timeout}")
  private int expirationTimeout;


  private PaginationExecutor paginationExecutor = new PaginationExecutor();
  private ResultOrderFilter resultFilter = new ResultOrderFilter();

  private TaskChain<?> taskQueue;
  private SearchParams searchParams;

  public SearchServiceImpl() {
    // Build TaskQueue
    taskQueue = new ReusableTaskChain<>()
        // Load market prices
        .perform(this::loadPrices)
        // Load market orders
        .perform(() -> loadOrders(searchParams.getRegions()))
        // Stop loading orders when queue execution is stopped
        .onStop(() -> paginationExecutor.stop())
        // Filter loaded orders
        .perform(() -> filter(searchParams.getIsk(), searchParams.getCargo()))
        // Find profitable orders
        .supply(() -> find(searchParams.getRouteOption(), searchParams.getCargo(), searchParams.getTax()))
        // Supply orders to result consumer
        .consume((result) -> searchParams.consumeResult(result))
        // Clean cached data
        .finallyAction(this::cleanUp);
  }

  @Override
  @Profiling
  public void searchForOrders(SearchParams searchParams) {
    this.searchParams = searchParams;
    taskQueue.execute();
  }

  @Override
  public void stopSearch() {
    taskQueue.stop();
  }

  /**
   * Clear all cached data.
   */
  private void cleanUp() {
    eventBus.post(CLEARING_CACHE);
    orderDao.deleteAll();
    marketPriceDao.deleteAll();
    eventBus.post(FINISHED);
  }

  /**
   * Loads prices for all items in the market.
   */
  private void loadPrices() {
    eventBus.post(LOADING_PRICES);
    marketClient.getAllItemPrices().process(list ->
        marketPriceDao.saveAll(list.stream().map(CachedMarketPrice::new).collect(Collectors.toList())));
  }

  /**
   * Loads orders for given set of regions.
   *
   * @param regions set of regions for which orders will be loaded.
   */
  private void loadOrders(Collection<String> regions) {
    eventBus.post(LOADING_ORDERS);

    // Get region IDs by names
    Collection<Integer> regionIds = regions == null || regions.isEmpty() ?
        // All regions
        regionsMap.values() : // or
        // Only required regions
        regions.stream().map(region -> regionsMap.get(region)).collect(Collectors.toList());

    // Run paginated loading of orders
    regionIds.forEach(region -> paginationExecutor.add(createPaginationForRegion(region)));
    paginationExecutor.execute();
  }

  /**
   * Creates Pagination object for given region id
   *
   * @param regionId region id
   * @return pagination object
   */
  private Pagination createPaginationForRegion(Integer regionId) {
    return new PaginationBuilder<RestResponse<List<MarketOrder>>>()
        // Begin with first page
        .startWith(1)
        // Load market orders for given region and page
        .loadPage(page -> marketClient.getOrders(OrderType.ALL, regionId, page))
        //Process page
        .processPage(this::processOrdersPage)
        // Set condition for pagination
        .whileTrue(this::checkPaginationCondition)
        // Build pagination
        .build();
  }

  private boolean checkPaginationCondition(Pagination<RestResponse<List<MarketOrder>>> pagination) {
    RestResponse<List<MarketOrder>> lastResponse = pagination.getLastPage();
    return lastResponse.isSuccessful() && lastResponse.getObject().size() > 0;
  }

  private void processOrdersPage(Pagination<?> pagination, RestResponse<List<MarketOrder>> response) {
    if (response.hasError()) {
      // Skip page on error
      pagination.getErrorHandler().retryPage();
    }
    else {
      // Convert and save loaded orders to DB
      orderDao.saveAll(response.getObject().stream().map(CachedOrder::new).collect(Collectors.toList()));
    }
  }

  /**
   * Filter loaded orders by amount of available funds and space.
   * Also deletes duplicate orders and orders that are soon to expire.
   *
   * @param funds  amount of funds available
   * @param volume amount of cargo volume available
   */
  private void filter(long funds, double volume) {
    eventBus.post(FILTERING_ORDERS);

    orderDao.removeDuplicateOrders();
    orderDao.removeSoonExpiredOrders(expirationTimeout);
    orderDao.removeLargeItemOrders(volume);
    orderDao.removeTooExpensiveOrders(funds);
  }

  /**
   * Returns list of profitable orders using given parameters
   *
   * @param routeOption route option
   * @param volume      available cargo volume
   * @param taxRate     tax rate
   * @return list of profitable orders
   */
  private List<OrderSearchRow> find(DotlanRouteOption routeOption, double volume, double taxRate) {
    eventBus.post(SEARCHING_FOR_PROFIT);

    List<ResultOrder> searchResults = orderDao.findProfitableOrders(routeOption.getSecurity(), volume, taxRate);

    if (searchResults.isEmpty()) {
      eventBus.post(NO_ORDERS_FOUND);
      return Collections.emptyList();
    }

    // Filter results
    searchResults = resultFilter.filter(searchResults);

    // Loaded names for given types
    Map<Integer, String> typeNames = universeClient.getNames(
        // Get ids of types of loaded items
        searchResults.stream().mapToInt(ResultOrder::getTypeId).distinct().toArray())
        // Convert loaded names to the map of type ids and names
        .getObject().stream().collect(Collectors.toMap(UniverseName::getId, UniverseName::getName));

    eventBus.post(SEARCHING_FOR_ROUTES);

    List<OrderSearchRow> result = searchResults.stream()
        .map(searchResult -> findRoute(searchResult, routeOption, typeNames.get(searchResult.getTypeId())))
        .collect(Collectors.toList());

    eventBus.post(FINISHED);
    return result;
  }

  /**
   * Finds route and creates OrderSearchRow using given parameters
   *
   * @param searchResult search result
   * @param routeOption  route option
   * @param typeName     item type name
   * @return new OrderSearchRow
   */
  private OrderSearchRow findRoute(ResultOrder searchResult, DotlanRouteOption routeOption, String typeName) {
    String sellSystemName = stationDao.findStationSystemName(searchResult.getSellLocation());
    String buySystemName = stationDao.findStationSystemName(searchResult.getBuyLocation());
    DotlanRoute dotlanRoute = dotlanService.getRoute(routeOption, sellSystemName, buySystemName);
    return new OrderSearchRow(typeName, sellSystemName, buySystemName, searchResult, dotlanRoute);
  }

}
