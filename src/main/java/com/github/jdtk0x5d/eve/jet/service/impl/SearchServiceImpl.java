package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.api.Pagination;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Profiling;
import com.github.jdtk0x5d.eve.jet.consts.OrderType;
import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketOrder;
import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchCache;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.MarketAPI;
import com.github.jdtk0x5d.eve.jet.service.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Service
public class SearchServiceImpl implements SearchService {

  private static final Logger logger = LogManager.getLogger(SearchServiceImpl.class);

  @Autowired
  private CacheDao cacheDao;
  @Autowired
  private MarketAPI marketAPI;

  @Value("#{${static.regions}}")
  private Map<String, Integer> regionsMap;

  @Override
  @Profiling
  public List<OrderSearchRow> searchForOrders(double isk, double volume, Collection<String> regions) {
    load(regions);
    return null;
  }

  private void load(Collection<String> regions) {
    // Get region IDs by names
    Collection<Integer> regionIds = regions == null ?
        // All regions
        regionsMap.values() : // or
        // Only required regions
        regions.stream().map(region -> regionsMap.get(region)).collect(Collectors.toList());
    // Load orders for regions in multiple threads
    regionIds.parallelStream().forEach(this::loadForRegion);
  }

  private void loadForRegion(Integer regionId) {
    new Pagination<MarketOrder, List<MarketOrder>>()
        // Load market orders for given region and page
        .loadPage(page -> marketAPI.getOrders(OrderType.ALL, regionId, page))
        // Save loaded orders to DB
        .processPage(orders -> cacheDao.saveAll(orders.stream().map(OrderSearchCache::new).collect(Collectors.toList())))
        // Skip page on error
        .onError(Pagination::onErrorSkipPage)
        // Perform pagination
        .perform();
  }

}
