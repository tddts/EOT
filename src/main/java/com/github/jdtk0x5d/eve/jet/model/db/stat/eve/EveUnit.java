package com.github.jdtk0x5d.eve.jet.model.db.stat.eve;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "eveUnits" database table.
 */
@Entity
@Table(name = "\"eveUnits\"")
@NamedQuery(name = "EveUnit.findAll", query = "SELECT e FROM EveUnit e")
public class EveUnit implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"description\"")
  private String description;

  @Column(name = "\"displayName\"")
  private String displayName;

  @Column(name = "\"unitID\"")
  private Integer unitID;

  @Column(name = "\"unitName\"")
  private String unitName;

  public EveUnit() {
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Integer getUnitID() {
    return this.unitID;
  }

  public void setUnitID(Integer unitID) {
    this.unitID = unitID;
  }

  public String getUnitName() {
    return this.unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  @Override
  public String toString() {
    return "EveUnit{" + "description=[" + description + "], displayName=[" + displayName + "], unitID=[" + unitID + "], unitName=[" + unitName + "]}";
  }
}