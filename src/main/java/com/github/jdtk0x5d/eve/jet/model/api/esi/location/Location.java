package com.github.jdtk0x5d.eve.jet.model.api.esi.location;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Location {

  private long station_id;
  private long solar_system_id;

  public long getStation_id() {
    return station_id;
  }

  public void setStation_id(long station_id) {
    this.station_id = station_id;
  }

  public long getSolar_system_id() {
    return solar_system_id;
  }

  public void setSolar_system_id(long solar_system_id) {
    this.solar_system_id = solar_system_id;
  }

  @Override
  public String toString() {
    return "Location{" + "station_id=[" + station_id + "], solar_system_id=[" + solar_system_id + "]}";
  }
}
