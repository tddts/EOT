package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.avaje.ebean.EbeanServer;
import com.github.jdtk0x5d.eve.jet.model.db.stat.map.MapConstellation;
import com.github.jdtk0x5d.eve.jet.model.db.stat.map.MapRegion;
import com.github.jdtk0x5d.eve.jet.model.db.stat.map.MapSolarSystem;
import com.github.jdtk0x5d.eve.jet.dao.MapDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static com.github.jdtk0x5d.eve.jet.util.Util.toIntList;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Repository
public class MapDaoImpl implements MapDao {

  @Autowired
  private EbeanServer ebeanServer;


  @Override
  public List<Integer> getRegioIdsByNames(Collection<String> names) {
    return ebeanServer.find(MapRegion.class).where().in("regionName",names).findIds();
  }

  @Override
  public List<Integer> getAllConstellationIds() {
    return toIntList(ebeanServer.find(MapConstellation.class).findIds());
  }

  @Override
  public List<MapConstellation> getAllConstellations() {
    return ebeanServer.find(MapConstellation.class).findList();
  }

  @Override
  public List<Integer> getAllSystemIds() {
    return toIntList(ebeanServer.find(MapSolarSystem.class).findIds());
  }

  @Override
  public List<MapSolarSystem> getAllSystems() {
    return ebeanServer.find(MapSolarSystem.class).findList();
  }

  @Override
  public List<Integer> getAllRegionIds() {
    return toIntList(ebeanServer.find(MapRegion.class).findIds());
  }

  @Override
  public List<MapRegion> getAllRegions() {
    return ebeanServer.find(MapRegion.class).findList();
  }

}
