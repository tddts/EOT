package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Profiling;
import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.consts.OrderType;
import com.github.jdtk0x5d.eve.jet.context.events.SearchStatusEvent;
import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.model.api.dotlan.DotlanRoute;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketOrder;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseName;
import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;
import com.github.jdtk0x5d.eve.jet.model.db.MarketPriceCache;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchCache;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchResult;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.rest.api.dotlan.DotlanAPI;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.MarketAPI;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.UniverseAPI;
import com.github.jdtk0x5d.eve.jet.service.SearchService;
import com.github.jdtk0x5d.eve.jet.tools.pagination.PaginationBuilder;
import com.github.jdtk0x5d.eve.jet.tools.pagination.PaginationErrorHandler;
import com.github.jdtk0x5d.eve.jet.util.RestUtil;
import com.google.common.eventbus.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class SearchServiceImpl implements SearchService {

  private static final Logger logger = LogManager.getLogger(SearchServiceImpl.class);

  @Autowired
  private CacheDao cacheDao;
  @Autowired
  private MarketAPI marketAPI;
  @Autowired
  private DotlanAPI dotlanAPI;
  @Autowired
  private UniverseAPI universeAPI;

  @Autowired
  private EventBus eventBus;

  @Value("#{${static.regions}}")
  private Map<String, Integer> regionsMap;

  @Value("${service.search.expiration.timeout}")
  private int expirationTimeout;

  @Value("${service.search.rax.percent}")
  private int taxPercent;

  @Override
  @Profiling
  public List<OrderSearchRow> searchForOrders(DotlanRouteOption routeOption, long isk, double volume, double taxRate, Collection<String> regions) {
    clear();
    loadPrices();
    loadOrders(regions);
    filter(isk, volume);
    return find(routeOption, volume, taxRate);
  }

  private void clear() {
    logger.debug("Clearing cached data...");
    eventBus.post(SearchStatusEvent.CLEARING_CACHE);

    cacheDao.deleteAll(OrderSearchCache.class);
    cacheDao.deleteAll(MarketPriceCache.class);
  }

  private void loadPrices() {
    logger.debug("Loading prices for items...");
    eventBus.post(SearchStatusEvent.LOADING_PRICES);

    marketAPI.getAllItemPrices()
        .process(list -> cacheDao.saveAll(list.stream().map(MarketPriceCache::new).collect(Collectors.toList())));
  }

  private void loadOrders(Collection<String> regions) {
    logger.debug("Loading orders for given regions...");
    eventBus.post(SearchStatusEvent.LOADING_ORDERS);

    // Get region IDs by names
    Collection<Integer> regionIds = regions == null || regions.isEmpty() ?
        // All regions
        regionsMap.values() : // or
        // Only required regions
        regions.stream().map(region -> regionsMap.get(region)).collect(Collectors.toList());

    // Load orders for regions in multiple threads
    regionIds.parallelStream().forEach(this::loadForRegion);
  }

  private void loadForRegion(Integer regionId) {
    new PaginationBuilder<MarketOrder, List<MarketOrder>>()
        // Load market orders for given region and page
        .loadPage(page -> marketAPI.getOrders(OrderType.ALL, regionId, page))
        // Save loaded orders to DB
        .processPage(orders -> cacheDao.saveAll(orders.stream().map(OrderSearchCache::new).collect(Collectors.toList())))
        // Retry page on error
        .onError(PaginationErrorHandler::retryPage)
        // Build pagination object
        .build()
        // Perform pagination
        .perform();
  }

  private void filter(long funds, double volume) {
    logger.debug("Filtering loaded data...");
    eventBus.post(SearchStatusEvent.FILTERING_ORDERS);

    cacheDao.removeDuplicateOrders();
    cacheDao.removeSoonExpiredOrders(expirationTimeout);
    cacheDao.removeLargeItemOrders(volume);
    cacheDao.removeTooExpensiveOrders(funds);
  }

  private List<OrderSearchRow> find(DotlanRouteOption routeOption, double volume, double taxRate) {
    logger.debug("Searching for profitable orders...");
    eventBus.post(SearchStatusEvent.SEARCHING_FOR_PROFIT);

    List<OrderSearchResult> searchResults = cacheDao.findProfitableOrders(routeOption.getSecurity(), volume, taxRate);

    if (searchResults.isEmpty()) {
      logger.warn("No profitable orders found!");
      eventBus.post(SearchStatusEvent.NO_ORDERS_FOUND);
      return Collections.emptyList();
    }

    logger.debug("Loading type names...");
    int[] typeIds = searchResults.stream().mapToInt(OrderSearchResult::getTypeId).distinct().toArray();
    Map<Integer, String> typeNames = RestUtil.safeRequest(() -> universeAPI.getNames(typeIds)).getObject().stream()
        .collect(Collectors.toMap(UniverseName::getId, UniverseName::getName));

    logger.debug("Searching for routes...");
    eventBus.post(SearchStatusEvent.SEARCHING_FOR_ROUTES);

    List<OrderSearchRow> result = searchResults.stream().map(
        searchResult -> findRoute(searchResult, routeOption, typeNames.get(searchResult.getTypeId())))
        .collect(Collectors.toList());

    eventBus.post(SearchStatusEvent.FINISHED);
    return result;
  }

  private OrderSearchRow findRoute(OrderSearchResult searchResult, DotlanRouteOption routeOption, String typeName) {
    String sellSystemName = cacheDao.findStationSystemName(searchResult.getSellLocation());
    String buySystemName = cacheDao.findStationSystemName(searchResult.getBuyLocation());
    RestResponse<DotlanRoute> dotlanRouteResponse = RestUtil.safeRequest(
        () -> dotlanAPI.getRoute(routeOption, sellSystemName, buySystemName));
    return createSearchRow(typeName, sellSystemName, buySystemName, searchResult, dotlanRouteResponse.getObject());
  }

  private OrderSearchRow createSearchRow(String typeName, String sellSystem, String buySystem, OrderSearchResult searchResult, DotlanRoute dotlanRoute) {
    OrderSearchRow result = new OrderSearchRow();

    result.setItem(typeName);
    result.setSellingLocation(sellSystem);
    result.setBuyingLocation(buySystem);
    result.setQuantity(searchResult.getSellVolume());
    result.setJumps(dotlanRoute.getJumpsCount());
    result.setSellPrice(searchResult.getSellPrice());
    result.setBuyPrice(searchResult.getBuyPrice());
    result.setProfit(searchResult.getProfit());
    result.setPerJumpProfit(searchResult.getProfit() / dotlanRoute.getJumpsCount());

    result.setDotlanRoute(dotlanRoute);
    result.setSearchResultData(searchResult);

    return result;
  }

}
