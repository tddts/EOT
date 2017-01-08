package com.github.jdtk0x5d.eve.jet.dao;

import com.github.jdtk0x5d.eve.jet.model.db.nonstat.OrderSearchCache;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface CacheDao {

  void saveOrders(List<OrderSearchCache> cacheList);
}
