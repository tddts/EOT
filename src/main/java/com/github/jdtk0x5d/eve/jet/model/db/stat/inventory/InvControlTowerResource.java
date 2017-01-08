package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invControlTowerResources" database table.
 */
@Entity
@Table(name = "\"invControlTowerResources\"")
@NamedQuery(name = "InvControlTowerResource.findAll", query = "SELECT i FROM InvControlTowerResource i")
public class InvControlTowerResource implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"controlTowerTypeID\"")
  private Integer controlTowerTypeID;

  @Column(name = "\"factionID\"")
  private Integer factionID;

  @Column(name = "\"minSecurityLevel\"")
  private Double minSecurityLevel;

  @Column(name = "\"purpose\"")
  private Integer purpose;

  @Column(name = "\"quantity\"")
  private Integer quantity;

  @Column(name = "\"resourceTypeID\"")
  private Integer resourceTypeID;

  public InvControlTowerResource() {
  }

  public Integer getControlTowerTypeID() {
    return this.controlTowerTypeID;
  }

  public void setControlTowerTypeID(Integer controlTowerTypeID) {
    this.controlTowerTypeID = controlTowerTypeID;
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public void setFactionID(Integer factionID) {
    this.factionID = factionID;
  }

  public Double getMinSecurityLevel() {
    return this.minSecurityLevel;
  }

  public void setMinSecurityLevel(Double minSecurityLevel) {
    this.minSecurityLevel = minSecurityLevel;
  }

  public Integer getPurpose() {
    return this.purpose;
  }

  public void setPurpose(Integer purpose) {
    this.purpose = purpose;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getResourceTypeID() {
    return this.resourceTypeID;
  }

  public void setResourceTypeID(Integer resourceTypeID) {
    this.resourceTypeID = resourceTypeID;
  }

  @Override
  public String toString() {
    return "InvControlTowerResource{" + "controlTowerTypeID=[" + controlTowerTypeID + "], factionID=[" + factionID + "], minSecurityLevel=[" + minSecurityLevel + "], purpose=[" + purpose + "], quantity=[" + quantity + "], resourceTypeID=[" + resourceTypeID + "]}";
  }
}