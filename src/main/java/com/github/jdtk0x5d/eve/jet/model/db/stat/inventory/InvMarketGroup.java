package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invMarketGroups" database table.
 */
@Entity
@Table(name = "\"invMarketGroups\"")
@NamedQuery(name = "InvMarketGroup.findAll", query = "SELECT i FROM InvMarketGroup i")
public class InvMarketGroup implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"description\"")
  private String description;

  @Column(name = "\"hasTypes\"")
  private String hasTypes;

  @Column(name = "\"iconID\"")
  private Integer iconID;

  @Column(name = "\"marketGroupID\"")
  private Integer marketGroupID;

  @Column(name = "\"marketGroupName\"")
  private String marketGroupName;

  @Column(name = "\"parentGroupID\"")
  private Integer parentGroupID;

  public InvMarketGroup() {
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHasTypes() {
    return this.hasTypes;
  }

  public void setHasTypes(String hasTypes) {
    this.hasTypes = hasTypes;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public void setIconID(Integer iconID) {
    this.iconID = iconID;
  }

  public Integer getMarketGroupID() {
    return this.marketGroupID;
  }

  public void setMarketGroupID(Integer marketGroupID) {
    this.marketGroupID = marketGroupID;
  }

  public String getMarketGroupName() {
    return this.marketGroupName;
  }

  public void setMarketGroupName(String marketGroupName) {
    this.marketGroupName = marketGroupName;
  }

  public Integer getParentGroupID() {
    return this.parentGroupID;
  }

  public void setParentGroupID(Integer parentGroupID) {
    this.parentGroupID = parentGroupID;
  }

  @Override
  public String toString() {
    return "InvMarketGroup{" + "description=[" + description + "], hasTypes=[" + hasTypes + "], iconID=[" + iconID + "], marketGroupID=[" + marketGroupID + "], marketGroupName=[" + marketGroupName + "], parentGroupID=[" + parentGroupID + "]}";
  }
}