package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.github.jdtk0x5d.eve.jet.dao.CacheDao;
import com.github.jdtk0x5d.eve.jet.model.db.RouteCache;
import org.springframework.stereotype.Component;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class CacheDaoImpl extends AbstractDao implements CacheDao {

  @Override
  public RouteCache findCachedRoute(Long firstPointId, Long secondPointId) {
    return ebeans().find(RouteCache.class).where()
        .in("startPointId", firstPointId, secondPointId)
        .or()
        .in("endPointId", firstPointId, secondPointId).findUnique();
  }


}
