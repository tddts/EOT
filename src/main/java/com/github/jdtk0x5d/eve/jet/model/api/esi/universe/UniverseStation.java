package com.github.jdtk0x5d.eve.jet.model.api.esi.universe;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class UniverseStation {

  private String station_name;
  private long solar_system_id;

  public String getStation_name() {
    return station_name;
  }

  public void setStation_name(String station_name) {
    this.station_name = station_name;
  }

  public long getSolar_system_id() {
    return solar_system_id;
  }

  public void setSolar_system_id(long solar_system_id) {
    this.solar_system_id = solar_system_id;
  }

  @Override
  public String toString() {
    return "UniverseStation{" + "station_name=[" + station_name + "], solar_system_id=[" + solar_system_id + "]}";
  }
}
