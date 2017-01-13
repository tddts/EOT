package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.avaje.ebean.EbeanServer;
import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Repository
public class CacheDaoImpl implements CacheDao {

  @Autowired
  private EbeanServer ebeanServer;

  @Override
  public void saveOrders(List<OrderSearchCache> cacheList) {
    ebeanServer.saveAll(cacheList);
  }
}
