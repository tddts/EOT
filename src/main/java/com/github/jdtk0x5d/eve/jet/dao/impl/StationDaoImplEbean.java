package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.LoadContent;
import com.github.jdtk0x5d.eve.jet.dao.StationDao;
import io.ebean.EbeanServer;
import io.ebean.SqlRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Repository
public class StationDaoImplEbean implements StationDao {

  @LoadContent("/sql/find_systems.sql")
  private String sql_find_systems;

  @Autowired
  private EbeanServer ebeanServer;

  @Override
  public String findStationSystemName(long station) {
    SqlRow system = ebeanServer.createSqlQuery(sql_find_systems).setParameter("station", station).findUnique();
    return system.getString("solar_system_name");
  }
}
