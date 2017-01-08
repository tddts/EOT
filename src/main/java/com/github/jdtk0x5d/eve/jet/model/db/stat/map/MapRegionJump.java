package com.github.jdtk0x5d.eve.jet.model.db.stat.map;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "mapRegionJumps" database table.
 */
@Entity
@Table(name = "\"mapRegionJumps\"")
@NamedQuery(name = "MapRegionJump.findAll", query = "SELECT m FROM MapRegionJump m")
public class MapRegionJump implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"fromRegionID\"")
  private Integer fromRegionID;

  @Column(name = "\"toRegionID\"")
  private Integer toRegionID;

  public MapRegionJump() {
  }

  public Integer getFromRegionID() {
    return this.fromRegionID;
  }

  public void setFromRegionID(Integer fromRegionID) {
    this.fromRegionID = fromRegionID;
  }

  public Integer getToRegionID() {
    return this.toRegionID;
  }

  public void setToRegionID(Integer toRegionID) {
    this.toRegionID = toRegionID;
  }

  @Override
  public String toString() {
    return "MapRegionJump{" + "fromRegionID=[" + fromRegionID + "], toRegionID=[" + toRegionID + "]}";
  }
}