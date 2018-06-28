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

import com.github.tddts.jet.config.spring.annotations.SendSearchEvents;
import com.github.tddts.jet.consts.OrderType;
import com.github.tddts.jet.consts.RouteOption;
import com.github.tddts.jet.dao.MarketPriceDao;
import com.github.tddts.jet.dao.OrderDao;
import com.github.tddts.jet.dao.StationDao;
import com.github.tddts.jet.model.app.OrderRoute;
import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.jet.model.app.RouteParams;
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
import com.github.tddts.jet.service.RouteService;
import com.github.tddts.jet.service.SearchOperations;
import com.github.tddts.jet.tools.filter.ResultOrderFilter;
import com.github.tddts.tools.core.pagination.Pagination;
import com.github.tddts.tools.core.pagination.builder.SerialPaginationBuilder;
import com.github.tddts.tools.core.pagination.builder.SinglePageErrorHandler;
import com.github.tddts.tools.core.pagination.executor.PaginationExecutor;
import com.github.tddts.tools.core.pagination.executor.SimplePaginationExecutor;
import com.github.tddts.tools.core.pagination.impl.PaginationBuilders;
import com.google.common.eventbus.EventBus;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.github.tddts.jet.context.events.SearchStatusEvent.*;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@Scope("prototype")
public class SearchOperationsImpl implements SearchOperations {

  @Autowired
  private OrderDao orderDao;
  @Autowired
  private MarketPriceDao marketPriceDao;
  @Autowired
  private StationDao stationDao;
  @Autowired
  private MarketClient marketClient;
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

  @Value("${service.search.loading.rate}")
  private long pageLoadingRate;

  private PaginationExecutor paginationExecutor = new SimplePaginationExecutor();
  private ResultOrderFilter resultFilter = new ResultOrderFilter();

  private SearchParams searchParams;


  /**
   * Clear all cached data.
   */
  @Override
  @SendSearchEvents(before = CLEARING_CACHE, after = FINISHED)
  public void cleanUp() {
    orderDao.deleteAll();
    marketPriceDao.deleteAll();
  }

  /**
   * Loads prices for all items in the market.
   */
  @Override
  @SendSearchEvents(before = LOADING_PRICES)
  public void loadPrices() {
    marketClient.getAllItemPrices().process(
        list -> marketPriceDao.saveAll(list.stream().map(CachedMarketPrice::new).collect(Collectors.toList())));
  }

  /**
   * Loads orders for given set of regions.
   */
  @Override
  @SendSearchEvents(before = LOADING_ORDERS)
  public void loadOrders() {
    Collection<Integer> regionIds = getRegionIds(searchParams.getRegions());
    regionIds.forEach(region -> paginationExecutor.add(createPaginationForRegion(region)));
    paginationExecutor.execute();
  }

  /**
   * Creates Pagination object for given region id
   *
   * @param regionId region id
   * @return pagination object
   */
  private Pagination<RestResponse<List<MarketOrder>>> createPaginationForRegion(long regionId) {
    // Load first page to get a get a header with overall page number
    RestResponse<List<MarketOrder>> first = loadAndSaveFirstPage(regionId);

    if (!checkFirstPage(first)) return null;

    return buildPaginationForRegion(regionId, getPages(first));
  }

  private RestResponse<List<MarketOrder>> loadAndSaveFirstPage(long regionId) {
    RestResponse<List<MarketOrder>> first = marketClient.getOrders(OrderType.ALL, regionId, 1);
    savePage(first);
    return first;
  }

  private boolean checkFirstPage(RestResponse<List<MarketOrder>> first) {
    return getPages(first) > 1;
  }

  private Integer getPages(RestResponse<List<MarketOrder>> page) {
    List<String> header = page.getHeader(pagesHeader);
    if (header == null) return -1;
    return Integer.valueOf(page.getHeader(pagesHeader).get(0));
  }

  private Pagination<RestResponse<List<MarketOrder>>> buildPaginationForRegion(long regionId, int pages) {
    SerialPaginationBuilder<RestResponse<List<MarketOrder>>> builder = PaginationBuilders.serial();
    return builder
        // Begin with second page
        .startWith(2)
        // Set last page number
        .finishOn(pages)
        // Load market orders for given region and page
        .loadPage((handler, page) -> loadPage(regionId, page, handler))
        //Process page
        .processPage((response, page) -> savePage(response))
        //Set loading rate
        .withRate(pageLoadingRate, TimeUnit.MILLISECONDS)
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

  private Collection<Integer> getRegionIds(Collection<String> regions) {
    if (regions.isEmpty())
      return regionsMap.values();// All regions
    else
      return regions.stream().map(region -> regionsMap.get(region)).collect(Collectors.toList());// Only selected regions
  }

  @Override
  public void stopLoadingOrders() {
    paginationExecutor.stop();
  }

  /**
   * Filter loaded orders by amount of available funds and space.
   * Also orders that are soon to expire.
   */
  @Override
  @SendSearchEvents(before = FILTERING_ORDERS)
  public void filterLoaded() {
    orderDao.removeSoonExpiredOrders(expirationTimeout);
    orderDao.removeLargeItemOrders(searchParams.getCargo());
    orderDao.removeTooExpensiveOrders(searchParams.getIsk());
  }

  /**
   * Find list of profitable orders using given parameters
   *
   * @return list of profitable orders
   */
  @Override
  @SendSearchEvents(before = SEARCHING_FOR_PROFIT, onEmpty = NO_ORDERS_FOUND)
  public List<ResultOrder> findProfitableOrders() {
    return orderDao.findProfitableOrders(
        searchParams.getSecurityLevel().getValue(),
        searchParams.getCargo(),
        searchParams.getTax());
  }

  @Override
  public List<ResultOrder> filterResults(List<ResultOrder> searchResults) {
    return resultFilter.filter(searchResults);
  }

  @Override
  public Pair<List<ResultOrder>, Map<Integer, String>> extractTypeNames(List<ResultOrder> searchResults) {
    Map<Integer, String> typeNames = getUniverseNames(searchResults).getObject().stream().collect(Collectors.toMap(UniverseName::getId, UniverseName::getName));
    return Pair.of(searchResults, typeNames);
  }

  private RestResponse<List<UniverseName>> getUniverseNames(List<ResultOrder> searchResults) {
    RestResponse<List<UniverseName>> namesResponse = universeClient.getNames(searchResults.stream().mapToInt(ResultOrder::getTypeId).distinct().toArray());
    namesResponse.checkError();
    return namesResponse;
  }

  @Override
  @SendSearchEvents(before = SEARCHING_FOR_ROUTES, after = FINISHED)
  public List<OrderSearchRow> searchForRoutes(Pair<List<ResultOrder>, Map<Integer, String>> searchPair) {
    List<ResultOrder> searchResults = searchPair.getLeft();
    Map<Integer, String> typeNames = searchPair.getRight();

    return searchResults.stream()
        .map(searchResult -> findRoute(searchResult, searchParams.getRouteOption(), typeNames.get(searchResult.getTypeId())))
        .collect(Collectors.toList());
  }

  @Override
  public void consumeResult(List<OrderSearchRow> result) {
    searchParams.consumeResult(result);
  }

  @Override
  public void setSearchParams(SearchParams params) {
    this.searchParams = params;
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
