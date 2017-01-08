package com.github.jdtk0x5d.eve.jet.model.db.stat.map;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "mapSolarSystemJumps" database table.
 */
@Entity
@Table(name = "\"mapSolarSystemJumps\"")
@NamedQuery(name = "MapSolarSystemJump.findAll", query = "SELECT m FROM MapSolarSystemJump m")
public class MapSolarSystemJump implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"fromConstellationID\"")
  private Integer fromConstellationID;

  @Column(name = "\"fromRegionID\"")
  private Integer fromRegionID;

  @Column(name = "\"fromSolarSystemID\"")
  private Integer fromSolarSystemID;

  @Column(name = "\"toConstellationID\"")
  private Integer toConstellationID;

  @Column(name = "\"toRegionID\"")
  private Integer toRegionID;

  @Column(name = "\"toSolarSystemID\"")
  private Integer toSolarSystemID;

  public MapSolarSystemJump() {
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

  public Integer getFromSolarSystemID() {
    return this.fromSolarSystemID;
  }

  public void setFromSolarSystemID(Integer fromSolarSystemID) {
    this.fromSolarSystemID = fromSolarSystemID;
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

  public Integer getToSolarSystemID() {
    return this.toSolarSystemID;
  }

  public void setToSolarSystemID(Integer toSolarSystemID) {
    this.toSolarSystemID = toSolarSystemID;
  }

  @Override
  public String toString() {
    return "MapSolarSystemJump{" + "fromConstellationID=[" + fromConstellationID + "], fromRegionID=[" + fromRegionID + "], fromSolarSystemID=[" + fromSolarSystemID + "], toConstellationID=[" + toConstellationID + "], toRegionID=[" + toRegionID + "], toSolarSystemID=[" + toSolarSystemID + "]}";
  }
}