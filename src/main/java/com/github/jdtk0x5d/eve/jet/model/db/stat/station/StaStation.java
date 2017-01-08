package com.github.jdtk0x5d.eve.jet.model.db.stat.station;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "staStations" database table.
 */
@Entity
@Table(name = "\"staStations\"")
@NamedQuery(name = "StaStation.findAll", query = "SELECT s FROM StaStation s")
public class StaStation implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"constellationID\"")
  private Integer constellationID;

  @Column(name = "\"corporationID\"")
  private Integer corporationID;

  @Column(name = "\"dockingCostPerVolume\"")
  private Double dockingCostPerVolume;

  @Column(name = "\"maxShipVolumeDockable\"")
  private Double maxShipVolumeDockable;

  @Column(name = "\"officeRentalCost\"")
  private Integer officeRentalCost;

  @Column(name = "\"operationID\"")
  private Integer operationID;

  @Column(name = "\"regionID\"")
  private Integer regionID;

  @Column(name = "\"reprocessingEfficiency\"")
  private Double reprocessingEfficiency;

  @Column(name = "\"reprocessingHangarFlag\"")
  private Integer reprocessingHangarFlag;

  @Column(name = "\"reprocessingStationsTake\"")
  private Double reprocessingStationsTake;

  @Column(name = "\"security\"")
  private Integer security;

  @Column(name = "\"solarSystemID\"")
  private Integer solarSystemID;

  @Column(name = "\"stationID\"")
  private String stationID;

  @Column(name = "\"stationName\"")
  private String stationName;

  @Column(name = "\"stationTypeID\"")
  private Integer stationTypeID;

  @Column(name = "\"x\"")
  private Double x;

  @Column(name = "\"y\"")
  private Double y;

  @Column(name = "\"z\"")
  private Double z;

  public StaStation() {
  }

  public Integer getConstellationID() {
    return this.constellationID;
  }

  public void setConstellationID(Integer constellationID) {
    this.constellationID = constellationID;
  }

  public Integer getCorporationID() {
    return this.corporationID;
  }

  public void setCorporationID(Integer corporationID) {
    this.corporationID = corporationID;
  }

  public Double getDockingCostPerVolume() {
    return this.dockingCostPerVolume;
  }

  public void setDockingCostPerVolume(Double dockingCostPerVolume) {
    this.dockingCostPerVolume = dockingCostPerVolume;
  }

  public Double getMaxShipVolumeDockable() {
    return this.maxShipVolumeDockable;
  }

  public void setMaxShipVolumeDockable(Double maxShipVolumeDockable) {
    this.maxShipVolumeDockable = maxShipVolumeDockable;
  }

  public Integer getOfficeRentalCost() {
    return this.officeRentalCost;
  }

  public void setOfficeRentalCost(Integer officeRentalCost) {
    this.officeRentalCost = officeRentalCost;
  }

  public Integer getOperationID() {
    return this.operationID;
  }

  public void setOperationID(Integer operationID) {
    this.operationID = operationID;
  }

  public Integer getRegionID() {
    return this.regionID;
  }

  public void setRegionID(Integer regionID) {
    this.regionID = regionID;
  }

  public Double getReprocessingEfficiency() {
    return this.reprocessingEfficiency;
  }

  public void setReprocessingEfficiency(Double reprocessingEfficiency) {
    this.reprocessingEfficiency = reprocessingEfficiency;
  }

  public Integer getReprocessingHangarFlag() {
    return this.reprocessingHangarFlag;
  }

  public void setReprocessingHangarFlag(Integer reprocessingHangarFlag) {
    this.reprocessingHangarFlag = reprocessingHangarFlag;
  }

  public Double getReprocessingStationsTake() {
    return this.reprocessingStationsTake;
  }

  public void setReprocessingStationsTake(Double reprocessingStationsTake) {
    this.reprocessingStationsTake = reprocessingStationsTake;
  }

  public Integer getSecurity() {
    return this.security;
  }

  public void setSecurity(Integer security) {
    this.security = security;
  }

  public Integer getSolarSystemID() {
    return this.solarSystemID;
  }

  public void setSolarSystemID(Integer solarSystemID) {
    this.solarSystemID = solarSystemID;
  }

  public String getStationID() {
    return this.stationID;
  }

  public void setStationID(String stationID) {
    this.stationID = stationID;
  }

  public String getStationName() {
    return this.stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public Integer getStationTypeID() {
    return this.stationTypeID;
  }

  public void setStationTypeID(Integer stationTypeID) {
    this.stationTypeID = stationTypeID;
  }

  public Double getX() {
    return this.x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getY() {
    return this.y;
  }

  public void setY(Double y) {
    this.y = y;
  }

  public Double getZ() {
    return this.z;
  }

  public void setZ(Double z) {
    this.z = z;
  }

  @Override
  public String toString() {
    return "StaStation{" + "constellationID=[" + constellationID + "], corporationID=["
        + corporationID + "], dockingCostPerVolume=[" + dockingCostPerVolume + "], maxShipVolumeDockable=["
        + maxShipVolumeDockable + "], officeRentalCost=[" + officeRentalCost + "], operationID=["
        + operationID + "], regionID=[" + regionID + "], reprocessingEfficiency=["
        + reprocessingEfficiency + "], reprocessingHangarFlag=["
        + reprocessingHangarFlag + "], reprocessingStationsTake=[" + reprocessingStationsTake + "], security=["
        + security + "], solarSystemID=[" + solarSystemID + "], stationID=[" + stationID + "], stationName=["
        + stationName + "], stationTypeID=[" + stationTypeID + "], x=[" + x + "], y=[" + y + "], z=[" + z + "]}";
  }
}