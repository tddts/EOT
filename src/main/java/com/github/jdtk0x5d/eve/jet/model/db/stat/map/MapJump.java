package com.github.jdtk0x5d.eve.jet.model.db.stat.map;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "mapJumps" database table.
 */
@Entity
@Table(name = "\"mapJumps\"")
@NamedQuery(name = "MapJump.findAll", query = "SELECT m FROM MapJump m")
public class MapJump implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"destinationID\"")
  private Integer destinationID;

  @Column(name = "\"stargateID\"")
  private Integer stargateID;

  public MapJump() {
  }

  public Integer getDestinationID() {
    return this.destinationID;
  }

  public void setDestinationID(Integer destinationID) {
    this.destinationID = destinationID;
  }

  public Integer getStargateID() {
    return this.stargateID;
  }

  public void setStargateID(Integer stargateID) {
    this.stargateID = stargateID;
  }

  @Override
  public String toString() {
    return "MapJump{" + "destinationID=[" + destinationID + "], stargateID=[" + stargateID + "]}";
  }
}