/*
 * Copyright 2018 Tigran Dadaiants
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
import com.github.tddts.jet.consts.RouteOption;
import com.github.tddts.jet.model.app.OrderRoute;
import com.github.tddts.jet.model.app.RouteParams;
import com.github.tddts.jet.consts.OrderType;
import com.github.tddts.jet.dao.MarketPriceDao;
import com.github.tddts.jet.dao.OrderDao;
import com.github.tddts.jet.dao.StationDao;
import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.jet.model.app.SearchParams;
import com.github.tddts.jet.model.client.esi.market.MarketOrder;
import com.github.tddts.jet.model.client.esi.universe.UniverseName;
import com.github.tddts.jet.model.db.CachedMarketPrice;
import com.github.tddts.jet.model.db.CachedOrder;
import com.github.tddts.jet.model.db.ResultOrder;
import com.github.tddts.jet.model.db.StaticStation;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.esi.MarketClient;
import com.github.tddts.jet.rest.client.esi.UniverseClient;
import com.github.tddts.jet.service.DotlanService;
import com.github.tddts.jet.service.RouteService;
import com.github.tddts.jet.service.SearchService;
import com.github.tddts.jet.tools.filter.ResultOrderFilter;
import com.github.tddts.tools.core.pagination.Pagination;
import com.github.tddts.tools.core.pagination.builder.SerialPaginationBuilder;
import com.github.tddts.tools.core.pagination.builder.SerialPaginationConditionData;
import com.github.tddts.tools.core.pagination.builder.SinglePageErrorHandler;
import com.github.tddts.tools.core.pagination.executor.PaginationExecutor;
import com.github.tddts.tools.core.pagination.executor.SimplePaginationExecutor;
import com.github.tddts.tools.core.pagination.impl.PaginationBuilders;
import com.github.tddts.tools.core.task.TaskChain;
import com.github.tddts.tools.core.task.impl.ReusableTaskChain;
import com.google.common.eventbus.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.github.tddts.jet.context.events.SearchStatusEvent.*;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class SearchServiceImpl implements SearchService {

  private final Logger logger = LogManager.getLogger(SearchServiceImpl.class);

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
  private RouteService routeService;

  @Autowired
  private EventBus eventBus;

  @Value("${header.pages}")
  private String pagesHeader;

  @Value("#{${static.regions}}")
  private Map<String, Integer> regionsMap;

  @Value("${service.search.expiration.timeout}")
  private int expirationTimeout;

  private PaginationExecutor paginationExecutor = new SimplePaginationExecutor();
  private ResultOrderFilter resultFilter = new ResultOrderFilter();

  private TaskChain<?> taskQueue;
  private SearchParams searchParams;

  public SearchServiceImpl() {
    taskQueue = new ReusableTaskChain<>()
        .perform(this::loadPrices)
        .perform(this::loadOrders)
        .onStop(this::stopOrdersLoading)
        .perform(this::filter)
        .supply(this::find)
        .consume(this::processResults)
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

    marketClient.getAllItemPrices().process(
        list -> marketPriceDao.saveAll(list.stream().map(CachedMarketPrice::new).collect(Collectors.toList())));
  }

  /**
   * Loads orders for given set of regions.
   */
  private void loadOrders() {
    eventBus.post(LOADING_ORDERS);

    Collection<String> regions = searchParams.getRegions();

    // Get region IDs by names
    Collection<Integer> regionIds;
    if (regions.isEmpty()) {
      // All regions
      regionIds = regionsMap.values();
    } else {
      // Only selected regions
      regionIds = regions.stream().map(region -> regionsMap.get(region)).collect(Collectors.toList());
    }

    // Run paginated loading of orders
    regionIds.forEach(region -> paginationExecutor.add(createPaginationForRegion(region)));
    paginationExecutor.execute();
  }

  private void stopOrdersLoading() {
    paginationExecutor.stop();
  }

  /**
   * Creates Pagination object for given region id
   *
   * @param regionId region id
   * @return pagination object
   */
  private Pagination<RestResponse<List<MarketOrder>>> createPaginationForRegion(long regionId) {
    SerialPaginationBuilder<RestResponse<List<MarketOrder>>> builder = PaginationBuilders.serial();

    // Load first page to get a get a header with overall page number
    RestResponse<List<MarketOrder>> first = marketClient.getOrders(OrderType.ALL, regionId, 1);
    savePage(first);

    if (first.getHeader(pagesHeader) == null) return null;

    int pages = Integer.parseInt(first.getHeader(pagesHeader).get(0));

    if (pages == 1) return null;

    return builder
        // Begin with second page
        .startWith(2)
        // Set last page number
        .finishOn(pages)
        // Load market orders for given region and page
        .loadPage((handler, page) -> loadPage(regionId, page, handler))
        //Process page
        .processPage((response, page) -> savePage(response))
        //Set loading rate - 30 requests per second
        .withRate(1000 / 30, TimeUnit.MILLISECONDS)
        // Build pagination
        .build();
  }

  private RestResponse<List<MarketOrder>> loadPage(long regionId, int page, SinglePageErrorHandler handler) {
    RestResponse<List<MarketOrder>> response = marketClient.getOrders(OrderType.ALL, regionId, page);
    if (response.hasError()) handler.retryPage();
    return response;
  }

  private void savePage(RestResponse<List<MarketOrder>> response) {
    response.ifSuccessful((orders) -> orderDao.merge(orders.stream().map(CachedOrder::new).collect(Collectors.toList())));
  }

  /**
   * Filter loaded orders by amount of available funds and space.
   * Also orders that are soon to expire.
   */
  private void filter() {
    eventBus.post(FILTERING_ORDERS);

    orderDao.removeSoonExpiredOrders(expirationTimeout);
    orderDao.removeLargeItemOrders(searchParams.getCargo());
    orderDao.removeTooExpensiveOrders(searchParams.getIsk());
  }

  /**
   * Find list of profitable orders using given parameters
   *
   * @return list of profitable orders
   */
  private List<ResultOrder> find() {
    eventBus.post(SEARCHING_FOR_PROFIT);

    List<ResultOrder> searchResults = orderDao.findProfitableOrders(
        searchParams.getSecurityLevel().getValue(),
        searchParams.getCargo(),
        searchParams.getTax());

    if (searchResults.isEmpty()) {
      eventBus.post(NO_ORDERS_FOUND);
      return Collections.emptyList();
    }

    return searchResults;
  }

  /**
   * Process list of resulted orders.
   */
  private void processResults(List<ResultOrder> searchResults) {
    // Filter results
    searchResults = resultFilter.filter(searchResults);

    RestResponse<List<UniverseName>> namesResponse = universeClient.getNames(searchResults.stream().mapToInt(ResultOrder::getTypeId).distinct().toArray());
    namesResponse.checkError();

    // Loaded names for given types
    Map<Integer, String> typeNames = namesResponse.getObject().stream().collect(Collectors.toMap(UniverseName::getId, UniverseName::getName));

    eventBus.post(SEARCHING_FOR_ROUTES);

    List<OrderSearchRow> result = searchResults.stream()
        .map(searchResult -> findRoute(searchResult, searchParams.getRouteOption(), typeNames.get(searchResult.getTypeId())))
        .collect(Collectors.toList());

    eventBus.post(FINISHED);

    searchParams.consumeResult(result);
  }

  /**
   * Finds route and creates OrderSearchRow using given parameters
   *
   * @param searchResult search result
   * @param routeOption  route option
   * @param typeName     item type name
   * @return new OrderSearchRow
   */
  private OrderSearchRow findRoute(ResultOrder searchResult, RouteOption routeOption, String typeName) {
    StaticStation sellStation = stationDao.find(searchResult.getSellLocation());
    StaticStation buyStation = stationDao.find(searchResult.getBuyLocation());

    OrderRoute orderRoute = routeService.getRoute(RouteParams
        .of(sellStation.getSolarSystemID(), buyStation.getSolarSystemID())
        .with(routeOption));

    return new OrderSearchRow(typeName, sellStation.getStationName(), buyStation.getStationName(), searchResult, orderRoute);
  }

}
