package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.rest.esi.MarketAPI;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Profiling;
import com.github.jdtk0x5d.eve.jet.consts.OrderType;
import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketOrder;
import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchCache;
import com.github.jdtk0x5d.eve.jet.service.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Repository
public class SearchServiceImpl implements SearchService {

  private static final Logger logger = LogManager.getLogger(SearchServiceImpl.class);
  @Autowired
  private CacheDao cacheDao;
  @Autowired
  private MarketAPI marketAPI;

  @Value("${rest.retry.count}")
  private int retryCount;

  @Value("#{${static.regions}}")
  private Map<String, Integer> regionsMap;

  @Override
  @Profiling
  public List<OrderSearchRow> searchForOrders(double isk, double volume, Collection<String> regions) {
    List<OrderSearchRow> result = search(isk, volume, regions);
    return result;
  }

  private List<OrderSearchRow> search(double isk, double volume, Collection<String> regions) {
    load(isk, volume, regions);
    return null;
  }

  private void load(double isk, double volume, Collection<String> regions) {
    Collection<Integer> regionIds = regions == null ?
        // Load all regions
        regionsMap.values() :
        // Load only required regions
        regions.stream().map(region -> regionsMap.get(region)).collect(Collectors.toList());

    // Load every region in in its own stream
    regionIds.parallelStream().forEach(this::loadForRegion);
  }

  private void loadForRegion(Integer regionId) {
    int page = 1;
    int ordersCount = 0;

    do {
      List<MarketOrder> orders = marketAPI.getOrders(OrderType.ALL, regionId, page);

      if (orders != null) {
        cacheDao.saveOrders(orders.stream().map(OrderSearchCache::new).collect(Collectors.toList()));
        ordersCount = orders.size();
        logger.debug("Loaded " + ordersCount + " orders.");
        page++;
      }

    } while (ordersCount > 0);
  }

}
