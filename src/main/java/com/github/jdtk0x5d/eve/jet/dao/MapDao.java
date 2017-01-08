package com.github.jdtk0x5d.eve.jet.dao;

import com.github.jdtk0x5d.eve.jet.model.db.stat.map.MapConstellation;
import com.github.jdtk0x5d.eve.jet.model.db.stat.map.MapRegion;
import com.github.jdtk0x5d.eve.jet.model.db.stat.map.MapSolarSystem;

import java.util.Collection;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface MapDao {

  List<Integer> getAllConstellationIds();

  List<MapConstellation> getAllConstellations();

  List<Integer> getAllSystemIds();

  List<MapSolarSystem> getAllSystems();

  List<Integer> getAllRegionIds();

  List<MapRegion> getAllRegions();

  List<Integer> getRegioIdsByNames(Collection<String> names);
}
