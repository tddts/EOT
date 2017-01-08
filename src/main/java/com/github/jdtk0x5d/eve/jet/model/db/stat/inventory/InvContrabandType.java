package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invContrabandTypes" database table.
 */
@Entity
@Table(name = "\"invContrabandTypes\"")
@NamedQuery(name = "InvContrabandType.findAll", query = "SELECT i FROM InvContrabandType i")
public class InvContrabandType implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"attackMinSec\"")
  private Double attackMinSec;

  @Column(name = "\"confiscateMinSec\"")
  private Double confiscateMinSec;

  @Column(name = "\"factionID\"")
  private Integer factionID;

  @Column(name = "\"fineByValue\"")
  private Double fineByValue;

  @Column(name = "\"standingLoss\"")
  private Double standingLoss;

  @Column(name = "\"typeID\"")
  private Integer typeID;

  public InvContrabandType() {
  }

  public Double getAttackMinSec() {
    return this.attackMinSec;
  }

  public void setAttackMinSec(Double attackMinSec) {
    this.attackMinSec = attackMinSec;
  }

  public Double getConfiscateMinSec() {
    return this.confiscateMinSec;
  }

  public void setConfiscateMinSec(Double confiscateMinSec) {
    this.confiscateMinSec = confiscateMinSec;
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public void setFactionID(Integer factionID) {
    this.factionID = factionID;
  }

  public Double getFineByValue() {
    return this.fineByValue;
  }

  public void setFineByValue(Double fineByValue) {
    this.fineByValue = fineByValue;
  }

  public Double getStandingLoss() {
    return this.standingLoss;
  }

  public void setStandingLoss(Double standingLoss) {
    this.standingLoss = standingLoss;
  }

  public Integer getTypeID() {
    return this.typeID;
  }

  public void setTypeID(Integer typeID) {
    this.typeID = typeID;
  }

  @Override
  public String toString() {
    return "InvContrabandType{" + "attackMinSec=[" + attackMinSec + "], confiscateMinSec=[" + confiscateMinSec + "], factionID=[" + factionID + "], fineByValue=[" + fineByValue + "], standingLoss=[" + standingLoss + "], typeID=[" + typeID + "]}";
  }
}