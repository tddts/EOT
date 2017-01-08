package com.github.jdtk0x5d.eve.jet.model.db.stat.map;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "mapUniverse" database table.
 */
@Entity
@Table(name = "\"mapUniverse\"")
@NamedQuery(name = "MapUniverse.findAll", query = "SELECT m FROM MapUniverse m")
public class MapUniverse implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"radius\"")
  private Double radius;

  @Column(name = "\"universeID\"")
  private Integer universeID;

  @Column(name = "\"universeName\"")
  private String universeName;

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

  public MapUniverse() {
  }

  public Double getRadius() {
    return this.radius;
  }

  public void setRadius(Double radius) {
    this.radius = radius;
  }

  public Integer getUniverseID() {
    return this.universeID;
  }

  public void setUniverseID(Integer universeID) {
    this.universeID = universeID;
  }

  public String getUniverseName() {
    return this.universeName;
  }

  public void setUniverseName(String universeName) {
    this.universeName = universeName;
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
    return "MapUniverse{" + "radius=[" + radius + "], universeID=[" + universeID + "], universeName=["
        + universeName + "], x=[" + x + "], xMax=[" + xMax + "], xMin=[" + xMin + "], y=[" + y + "], yMax=["
        + yMax + "], yMin=[" + yMin + "], z=[" + z + "], zMax=[" + zMax + "], zMin=[" + zMin + "]}";
  }
}