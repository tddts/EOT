package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchCache;
import com.github.jdtk0x5d.eve.jet.model.db.RouteCache;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class CacheDaoImpl extends AbstractDao implements CacheDao {

  private static final String SQL_DELETE_EXPIRED = "DELETE FROM ORDER_SEARCH_CACHE " +
      "WHERE DATEDIFF('MINUTE', CURRENT_TIMESTAMP(), TIMESTAMPADD( 'DAY', DURATION, ISSUED)) < :diff";

  @Override
  public RouteCache findCachedRoute(Long firstPointId, Long secondPointId) {
    return ebeans().find(RouteCache.class).where()
        .in("startPointId", firstPointId, secondPointId)
        .or()
        .in("endPointId", firstPointId, secondPointId).findUnique();
  }

  @Override
  public List<Integer> findTypeIds() {
    return ebeans().find(OrderSearchCache.class).select("typeID").setDistinct(true).findSingleAttributeList();
  }

  @Override
  public int removeSoonExpiredOrders(int time) {
    return ebeans().createSqlUpdate(SQL_DELETE_EXPIRED).setParameter("diff", time).execute();
  }

}
