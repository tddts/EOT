package com.github.jdtk0x5d.eve.jet.model.db.stat.map;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "mapConstellationJumps" database table.
 */
@Entity
@Table(name = "\"mapConstellationJumps\"")
@NamedQuery(name = "MapConstellationJump.findAll", query = "SELECT m FROM MapConstellationJump m")
public class MapConstellationJump implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"fromConstellationID\"")
  private Integer fromConstellationID;

  @Column(name = "\"fromRegionID\"")
  private Integer fromRegionID;

  @Column(name = "\"toConstellationID\"")
  private Integer toConstellationID;

  @Column(name = "\"toRegionID\"")
  private Integer toRegionID;

  public MapConstellationJump() {
  }

  public Integer getFromConstellationID() {
    return this.fromConstellationID;
  }

  public void setFromConstellationID(Integer fromConstellationID) {
    this.fromConstellationID = fromConstellationID;
  }

  public Integer getFromRegionID() {
    return this.fromRegionID;
  }

  public void setFromRegionID(Integer fromRegionID) {
    this.fromRegionID = fromRegionID;
  }

  public Integer getToConstellationID() {
    return this.toConstellationID;
  }

  public void setToConstellationID(Integer toConstellationID) {
    this.toConstellationID = toConstellationID;
  }

  public Integer getToRegionID() {
    return this.toRegionID;
  }

  public void setToRegionID(Integer toRegionID) {
    this.toRegionID = toRegionID;
  }

  @Override
  public String toString() {
    return "MapConstellationJump{" + "fromConstellationID=[" + fromConstellationID + "], fromRegionID=[" + fromRegionID + "], toConstellationID=[" + toConstellationID + "], toRegionID=[" + toRegionID + "]}";
  }
}