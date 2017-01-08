package com.github.jdtk0x5d.eve.jet.model.api.esi.universe;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class UniverseStructure {

  private String name;
  private long solar_system_id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getSolar_system_id() {
    return solar_system_id;
  }

  public void setSolar_system_id(long solar_system_id) {
    this.solar_system_id = solar_system_id;
  }

  @Override
  public String toString() {
    return "UniverseStructure{" + "name=[" + name + "], solar_system_id=[" + solar_system_id + "]}";
  }
}