package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invVolumes" database table.
 */
@Entity
@Table(name = "\"invVolumes\"")
@NamedQuery(name = "InvVolume.findAll", query = "SELECT i FROM InvVolume i")
public class InvVolume implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"typeID\"")
  private Integer typeID;

  @Column(name = "\"volume\"")
  private Integer volume;

  public InvVolume() {
  }

  public Integer getTypeID() {
    return this.typeID;
  }

  public void setTypeID(Integer typeID) {
    this.typeID = typeID;
  }

  public Integer getVolume() {
    return this.volume;
  }

  public void setVolume(Integer volume) {
    this.volume = volume;
  }

  @Override
  public String toString() {
    return "InvVolume{" + "typeID=[" + typeID + "], volume=[" + volume + "]}";
  }
}