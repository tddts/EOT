package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchCache;
import com.github.jdtk0x5d.eve.jet.model.db.RouteCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class CacheDaoImpl extends AbstractDao implements CacheDao {

  @Value("${sql.delete.expired}")
  private String SQL_deleteExpired;

  @Value("${sql.delete.duplicate}")
  private String SQL_deleteDuplicate;

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
    return ebeans().createSqlUpdate(SQL_deleteExpired).setParameter("diff", time).execute();
  }

  @Override
  public int removeDuplicateOrders() {
    return ebeans().createSqlUpdate(SQL_deleteDuplicate).execute();
  }

}
