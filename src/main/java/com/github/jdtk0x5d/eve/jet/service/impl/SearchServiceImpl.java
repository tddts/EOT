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
import com.github.jdtk0x5d.eve.jet.model.app.SearchParams;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@Scope("singleton")
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

  private ExecutorService executorService = Executors.newSingleThreadExecutor();

  private volatile boolean searchRunning = false;
  private volatile boolean cleanUpRunning = false;

  @Override
  @Profiling
  public void searchForOrders(SearchParams searchParams) {

    if (!searchParams.isValid()) {
      logger.warn("Search parameters are invalid. Search not started.");
      return;
    }

    if (searchRunning || cleanUpRunning) {
      return;
    }

    searchRunning = true;

    loadPrices();
    loadOrders(searchParams.getRegions());
    filter(searchParams.getIsk(), searchParams.getCargo());
    List<OrderSearchRow> result = find(searchParams.getRouteOption(), searchParams.getCargo(), searchParams.getTax());

    if (!result.isEmpty()) searchParams.getResultConsumer().accept(result);

    cleanUpAfter();

    searchRunning = false;
  }

  private void cleanUpAfter() {
    executorService.submit(this::cleanUp);
  }

  private void cleanUp() {
    cleanUpRunning = true;
    logger.debug("Clearing cached data...");
    eventBus.post(SearchStatusEvent.CLEARING_CACHE);
    cacheDao.deleteAll(OrderSearchCache.class);
    cacheDao.deleteAll(MarketPriceCache.class);
    logger.debug("Finished cleanup.");
    eventBus.post(SearchStatusEvent.FINISHED);
    cleanUpRunning = false;
  }

  private void loadPrices() {
    logger.debug("Loading prices for items...");
    eventBus.post(SearchStatusEvent.LOADING_PRICES);
    // Load prices
    marketAPI.getAllItemPrices()
        // Convert and save market prices
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
        // Convert and save loaded orders to DB
        .processPage(orders -> cacheDao.saveAll(orders.stream().map(OrderSearchCache::new).collect(Collectors.toList())))
        // Retry page loading on error
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
    // Get type ids of all loaded orders
    int[] typeIds = searchResults.stream().mapToInt(OrderSearchResult::getTypeId).distinct().toArray();
    // Loaded names for given types
    Map<Integer, String> typeNames = RestUtil.safeRequest(() -> universeAPI.getNames(typeIds))
        // Convert loaded names to the map of type ids and names
        .getObject().stream().collect(Collectors.toMap(UniverseName::getId, UniverseName::getName));

    logger.debug("Searching for routes...");
    eventBus.post(SearchStatusEvent.SEARCHING_FOR_ROUTES);

    List<OrderSearchRow> result = searchResults.stream()
        // Find route for each search result
        .map(searchResult -> findRoute(searchResult, routeOption, typeNames.get(searchResult.getTypeId())))
        .collect(Collectors.toList());

    eventBus.post(SearchStatusEvent.FINISHED);
    return result;
  }

  private OrderSearchRow findRoute(OrderSearchResult searchResult, DotlanRouteOption routeOption, String typeName) {
    String sellSystemName = cacheDao.findStationSystemName(searchResult.getSellLocation());
    String buySystemName = cacheDao.findStationSystemName(searchResult.getBuyLocation());

    RestResponse<DotlanRoute> dotlanRouteResponse = RestUtil.safeRequest(
        () -> dotlanAPI.getRoute(routeOption, sellSystemName, buySystemName));

    return new OrderSearchRow(typeName, sellSystemName, buySystemName, searchResult, dotlanRouteResponse.getObject());
  }

}
