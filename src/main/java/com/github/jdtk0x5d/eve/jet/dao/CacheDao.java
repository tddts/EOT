package com.github.jdtk0x5d.eve.jet.dao;

import com.github.jdtk0x5d.eve.jet.model.db.RouteCache;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface CacheDao extends GenericDao {

  RouteCache findCachedRoute(Long firstPointId, Long secondPointId);


}
