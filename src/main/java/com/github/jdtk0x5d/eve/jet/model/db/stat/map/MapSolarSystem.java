package com.github.jdtk0x5d.eve.jet.model.db.stat.map;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "mapSolarSystems" database table.
 */
@Entity
@Table(name = "\"mapSolarSystems\"")
@NamedQuery(name = "MapSolarSystem.findAll", query = "SELECT m FROM MapSolarSystem m")
public class MapSolarSystem implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"border\"")
  private String border;

  @Column(name = "\"constellation\"")
  private String constellation;

  @Column(name = "\"constellationID\"")
  private Integer constellationID;

  @Column(name = "\"corridor\"")
  private String corridor;

  @Column(name = "\"factionID\"")
  private Integer factionID;

  @Column(name = "\"fringe\"")
  private String fringe;

  @Column(name = "\"hub\"")
  private String hub;

  @Column(name = "\"Integerernational\"")
  private String Integerernational;

  @Column(name = "\"luminosity\"")
  private Double luminosity;

  @Column(name = "\"radius\"")
  private Double radius;

  @Column(name = "\"regional\"")
  private String regional;

  @Column(name = "\"regionID\"")
  private Integer regionID;

  @Column(name = "\"security\"")
  private Double security;

  @Column(name = "\"securityClass\"")
  private String securityClass;

  @Id
  @Column(name = "\"solarSystemID\"")
  private Integer solarSystemID;

  @Column(name = "\"solarSystemName\"")
  private String solarSystemName;

  @Column(name = "\"sunTypeID\"")
  private Integer sunTypeID;

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

  public MapSolarSystem() {
  }

  public String getBorder() {
    return this.border;
  }

  public void setBorder(String border) {
    this.border = border;
  }

  public String getConstellation() {
    return this.constellation;
  }

  public void setConstellation(String constellation) {
    this.constellation = constellation;
  }

  public Integer getConstellationID() {
    return this.constellationID;
  }

  public void setConstellationID(Integer constellationID) {
    this.constellationID = constellationID;
  }

  public String getCorridor() {
    return this.corridor;
  }

  public void setCorridor(String corridor) {
    this.corridor = corridor;
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public void setFactionID(Integer factionID) {
    this.factionID = factionID;
  }

  public String getFringe() {
    return this.fringe;
  }

  public void setFringe(String fringe) {
    this.fringe = fringe;
  }

  public String getHub() {
    return this.hub;
  }

  public void setHub(String hub) {
    this.hub = hub;
  }

  public String getInternational() {
    return this.Integerernational;
  }

  public void setInternational(String Integerernational) {
    this.Integerernational = Integerernational;
  }

  public Double getLuminosity() {
    return this.luminosity;
  }

  public void setLuminosity(Double luminosity) {
    this.luminosity = luminosity;
  }

  public Double getRadius() {
    return this.radius;
  }

  public void setRadius(Double radius) {
    this.radius = radius;
  }

  public String getRegional() {
    return this.regional;
  }

  public void setRegional(String regional) {
    this.regional = regional;
  }

  public Integer getRegionID() {
    return this.regionID;
  }

  public void setRegionID(Integer regionID) {
    this.regionID = regionID;
  }

  public Double getSecurity() {
    return this.security;
  }

  public void setSecurity(Double security) {
    this.security = security;
  }

  public String getSecurityClass() {
    return this.securityClass;
  }

  public void setSecurityClass(String securityClass) {
    this.securityClass = securityClass;
  }

  public Integer getSolarSystemID() {
    return this.solarSystemID;
  }

  public void setSolarSystemID(Integer solarSystemID) {
    this.solarSystemID = solarSystemID;
  }

  public String getSolarSystemName() {
    return this.solarSystemName;
  }

  public void setSolarSystemName(String solarSystemName) {
    this.solarSystemName = solarSystemName;
  }

  public Integer getSunTypeID() {
    return this.sunTypeID;
  }

  public void setSunTypeID(Integer sunTypeID) {
    this.sunTypeID = sunTypeID;
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
    return "MapSolarSystem{" + "border=[" + border + "], constellation=[" + constellation + "], constellationID=["
        + constellationID + "], corridor=[" + corridor + "], factionID=[" + factionID + "], fringe=["
        + fringe + "], hub=[" + hub + "], Integerernational=[" + Integerernational + "], luminosity=["
        + luminosity + "], radius=[" + radius + "], regional=[" + regional + "], regionID=["
        + regionID + "], security=[" + security + "], securityClass=[" + securityClass + "], solarSystemID=["
        + solarSystemID + "], solarSystemName=[" + solarSystemName + "], sunTypeID=[" + sunTypeID + "], x=["
        + x + "], xMax=[" + xMax + "], xMin=[" + xMin + "], y=[" + y + "], yMax=[" + yMax + "], yMin=["
        + yMin + "], z=[" + z + "], zMax=[" + zMax + "], zMin=[" + zMin + "]}";
  }
}