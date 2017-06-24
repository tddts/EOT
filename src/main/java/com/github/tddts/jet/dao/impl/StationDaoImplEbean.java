/*
 * Copyright 2017 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.jet.dao.impl;

import com.github.tddts.jet.config.spring.annotations.LoadContent;
import com.github.tddts.jet.dao.StationDao;
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
