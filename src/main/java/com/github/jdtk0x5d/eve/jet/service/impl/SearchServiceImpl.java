package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.api.esi.MarketAPI;
import com.github.jdtk0x5d.eve.jet.consts.OrderType;
import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.dao.MapDao;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketOrder;
import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;
import com.github.jdtk0x5d.eve.jet.model.db.nonstat.OrderSearchCache;
import com.github.jdtk0x5d.eve.jet.service.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class SearchServiceImpl implements SearchService {

  private static final Logger logger = LogManager.getLogger(SearchServiceImpl.class);

  @Autowired
  private MapDao mapDao;
  @Autowired
  private CacheDao cacheDao;
  @Autowired
  private MarketAPI marketAPI;

  @Override
  public List<OrderSearchRow> searchForOrders(double isk, double volume, Collection<String> systems) {
    logger.debug("Searching for orders with ISK = [" + isk + "], Volume=[" + volume + "], Systems=[" + systems + "]");
    long timeCount = System.currentTimeMillis();
    List<OrderSearchRow> result = search(isk, volume, systems);
    timeCount = System.currentTimeMillis() - timeCount;
    logger.debug("Total loading time: " + timeCount / 1000);
    return result;
  }

  private List<OrderSearchRow> search(double isk, double volume, Collection<String> systems) {
    loadAndSave(isk, volume, systems);
    return null;
  }

  private void loadAndSave(double isk, double volume, Collection<String> systems) {
    List<Integer> regionIds = systems == null ? mapDao.getAllSystemIds() : mapDao.getRegioIdsByNames(systems);
    for (Integer regionId : regionIds) {
      int ordersCount;
      int page = 1;

      do {
        List<MarketOrder> orders = marketAPI.getOrders(OrderType.ALL, regionId, page);
        if(orders != null){

        }
        cacheDao.saveOrders(convert(orders));
        ordersCount = orders.size();
        page++;
      } while (ordersCount > 0);

    }
  }

  private List<OrderSearchCache> convert(List<MarketOrder> orders) {
    List<OrderSearchCache> searchCache = new ArrayList<>(orders.size());
    for (MarketOrder order : orders) {
      searchCache.add(new OrderSearchCache(order));
    }
    return searchCache;
  }
}
