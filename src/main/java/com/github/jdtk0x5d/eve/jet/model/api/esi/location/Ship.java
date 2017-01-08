package com.github.jdtk0x5d.eve.jet.model.api.esi.location;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Ship {

  private long ship_type_id;
  private long ship_item_id;
  private String ship_name;

  public long getShip_type_id() {
    return ship_type_id;
  }

  public void setShip_type_id(long ship_type_id) {
    this.ship_type_id = ship_type_id;
  }

  public String getShip_name() {
    return ship_name;
  }

  public void setShip_name(String ship_name) {
    this.ship_name = ship_name;
  }

  public long getShip_item_id() {
    return ship_item_id;
  }

  public void setShip_item_id(long ship_item_id) {
    this.ship_item_id = ship_item_id;
  }

  @Override
  public String toString() {
    return "Ship{" + "ship_type_id=[" + ship_type_id + "], ship_item_id=[" + ship_item_id + "], ship_name=[" + ship_name + "]}";
  }
}
