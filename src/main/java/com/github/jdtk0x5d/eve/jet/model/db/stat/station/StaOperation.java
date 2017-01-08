package com.github.jdtk0x5d.eve.jet.model.db.stat.station;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "staOperations" database table.
 */
@Entity
@Table(name = "\"staOperations\"")
@NamedQuery(name = "StaOperation.findAll", query = "SELECT s FROM StaOperation s")
public class StaOperation implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"activityID\"")
  private Integer activityID;

  @Column(name = "\"amarrStationTypeID\"")
  private Integer amarrStationTypeID;

  @Column(name = "\"border\"")
  private Integer border;

  @Column(name = "\"caldariStationTypeID\"")
  private Integer caldariStationTypeID;

  @Column(name = "\"corridor\"")
  private Integer corridor;

  @Column(name = "\"description\"")
  private String description;

  @Column(name = "\"fringe\"")
  private Integer fringe;

  @Column(name = "\"gallenteStationTypeID\"")
  private Integer gallenteStationTypeID;

  @Column(name = "\"hub\"")
  private Integer hub;

  @Column(name = "\"joveStationTypeID\"")
  private Integer joveStationTypeID;

  @Column(name = "\"minmatarStationTypeID\"")
  private Integer minmatarStationTypeID;

  @Column(name = "\"operationID\"")
  private Integer operationID;

  @Column(name = "\"operationName\"")
  private String operationName;

  @Column(name = "\"ratio\"")
  private Integer ratio;

  public StaOperation() {
  }

  public Integer getActivityID() {
    return this.activityID;
  }

  public void setActivityID(Integer activityID) {
    this.activityID = activityID;
  }

  public Integer getAmarrStationTypeID() {
    return this.amarrStationTypeID;
  }

  public void setAmarrStationTypeID(Integer amarrStationTypeID) {
    this.amarrStationTypeID = amarrStationTypeID;
  }

  public Integer getBorder() {
    return this.border;
  }

  public void setBorder(Integer border) {
    this.border = border;
  }

  public Integer getCaldariStationTypeID() {
    return this.caldariStationTypeID;
  }

  public void setCaldariStationTypeID(Integer caldariStationTypeID) {
    this.caldariStationTypeID = caldariStationTypeID;
  }

  public Integer getCorridor() {
    return this.corridor;
  }

  public void setCorridor(Integer corridor) {
    this.corridor = corridor;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getFringe() {
    return this.fringe;
  }

  public void setFringe(Integer fringe) {
    this.fringe = fringe;
  }

  public Integer getGallenteStationTypeID() {
    return this.gallenteStationTypeID;
  }

  public void setGallenteStationTypeID(Integer gallenteStationTypeID) {
    this.gallenteStationTypeID = gallenteStationTypeID;
  }

  public Integer getHub() {
    return this.hub;
  }

  public void setHub(Integer hub) {
    this.hub = hub;
  }

  public Integer getJoveStationTypeID() {
    return this.joveStationTypeID;
  }

  public void setJoveStationTypeID(Integer joveStationTypeID) {
    this.joveStationTypeID = joveStationTypeID;
  }

  public Integer getMinmatarStationTypeID() {
    return this.minmatarStationTypeID;
  }

  public void setMinmatarStationTypeID(Integer minmatarStationTypeID) {
    this.minmatarStationTypeID = minmatarStationTypeID;
  }

  public Integer getOperationID() {
    return this.operationID;
  }

  public void setOperationID(Integer operationID) {
    this.operationID = operationID;
  }

  public String getOperationName() {
    return this.operationName;
  }

  public void setOperationName(String operationName) {
    this.operationName = operationName;
  }

  public Integer getRatio() {
    return this.ratio;
  }

  public void setRatio(Integer ratio) {
    this.ratio = ratio;
  }

  @Override
  public String toString() {
    return "StaOperation{" + "activityID=[" + activityID + "], amarrStationTypeID=["
        + amarrStationTypeID + "], border=[" + border + "], caldariStationTypeID=["
        + caldariStationTypeID + "], corridor=[" + corridor + "], description=[" + description + "], fringe=["
        + fringe + "], gallenteStationTypeID=[" + gallenteStationTypeID + "], hub=[" + hub + "], joveStationTypeID=["
        + joveStationTypeID + "], minmatarStationTypeID=[" + minmatarStationTypeID + "], operationID=["
        + operationID + "], operationName=[" + operationName + "], ratio=[" + ratio + "]}";
  }
}