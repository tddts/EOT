package com.github.jdtk0x5d.eve.jet.model.db.stat.map;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "mapConstellations" database table.
 */
@Entity
@Table(name = "\"mapConstellations\"")
@NamedQuery(name = "MapConstellation.findAll", query = "SELECT m FROM MapConstellation m")
public class MapConstellation implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"constellationID\"")
  private Integer constellationID;

  @Column(name = "\"constellationName\"")
  private String constellationName;

  @Column(name = "\"factionID\"")
  private Integer factionID;

  @Column(name = "\"radius\"")
  private Double radius;

  @Column(name = "\"regionID\"")
  private Integer regionID;

  @Column(name = "\"x\"")
  private Double x;

  @Column(name = "\"xMax\"")
  private Double xMax;

  @Column(name = "\"xMin\"")
  private Double xMin;

  @Column(name = "\"y\"")
  private Double y;

  @Column(name = "\"yMax\"")
  private Double yMax;

  @Column(name = "\"yMin\"")
  private Double yMin;

  @Column(name = "\"z\"")
  private Double z;

  @Column(name = "\"zMax\"")
  private Double zMax;

  @Column(name = "\"zMin\"")
  private Double zMin;

  public MapConstellation() {
  }

  public Integer getConstellationID() {
    return this.constellationID;
  }

  public void setConstellationID(Integer constellationID) {
    this.constellationID = constellationID;
  }

  public String getConstellationName() {
    return this.constellationName;
  }

  public void setConstellationName(String constellationName) {
    this.constellationName = constellationName;
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public void setFactionID(Integer factionID) {
    this.factionID = factionID;
  }

  public Double getRadius() {
    return this.radius;
  }

  public void setRadius(Double radius) {
    this.radius = radius;
  }

  public Integer getRegionID() {
    return this.regionID;
  }

  public void setRegionID(Integer regionID) {
    this.regionID = regionID;
  }

  public Double getX() {
    return this.x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getXMax() {
    return this.xMax;
  }

  public void setXMax(Double xMax) {
    this.xMax = xMax;
  }

  public Double getXMin() {
    return this.xMin;
  }

  public void setXMin(Double xMin) {
    this.xMin = xMin;
  }

  public Double getY() {
    return this.y;
  }

  public void setY(Double y) {
    this.y = y;
  }

  public Double getYMax() {
    return this.yMax;
  }

  public void setYMax(Double yMax) {
    this.yMax = yMax;
  }

  public Double getYMin() {
    return this.yMin;
  }

  public void setYMin(Double yMin) {
    this.yMin = yMin;
  }

  public Double getZ() {
    return this.z;
  }

  public void setZ(Double z) {
    this.z = z;
  }

  public Double getZMax() {
    return this.zMax;
  }

  public void setZMax(Double zMax) {
    this.zMax = zMax;
  }

  public Double getZMin() {
    return this.zMin;
  }

  public void setZMin(Double zMin) {
    this.zMin = zMin;
  }

  @Override
  public String toString() {
    return "MapConstellation{" + "constellationID=[" + constellationID + "], constellationName=[" + constellationName + "], factionID=[" + factionID + "], radius=[" + radius + "], regionID=[" + regionID + "], x=[" + x + "], xMax=[" + xMax + "], xMin=[" + xMin + "], y=[" + y + "], yMax=[" + yMax + "], yMin=[" + yMin + "], z=[" + z + "], zMax=[" + zMax + "], zMin=[" + zMin + "]}";
  }
}