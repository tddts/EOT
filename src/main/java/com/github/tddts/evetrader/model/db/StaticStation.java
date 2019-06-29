/*
 * Copyright 2018 Tigran Dadaiants
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

package com.github.tddts.evetrader.model.db;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * {@code StaticStation} represents static data about space station in the EVE Universe.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Table
@Entity
public class StaticStation {

  @Id
  @Column(unique = true)
  private int stationId;

  @Column
  private String stationName;

  @Column
  private int solarSystemID;

  @Column
  private String solarSystemName;

  @Column
  private int constellationId;

  @Column
  private int regionId;

  @Column
  private double securityStatus;

  public StaticStation() {
  }

  public int getStationId() {
    return stationId;
  }

  public void setStationId(int stationId) {
    this.stationId = stationId;
  }

  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public int getSolarSystemID() {
    return solarSystemID;
  }

  public void setSolarSystemID(int solarSystemID) {
    this.solarSystemID = solarSystemID;
  }

  public String getSolarSystemName() {
    return solarSystemName;
  }

  public void setSolarSystemName(String solarSystemName) {
    this.solarSystemName = solarSystemName;
  }

  public int getConstellationId() {
    return constellationId;
  }

  public void setConstellationId(int constellationId) {
    this.constellationId = constellationId;
  }

  public int getRegionId() {
    return regionId;
  }

  public void setRegionId(int regionId) {
    this.regionId = regionId;
  }

  public double getSecurityStatus() {
    return securityStatus;
  }

  public void setSecurityStatus(double securityStatus) {
    this.securityStatus = securityStatus;
  }
}
