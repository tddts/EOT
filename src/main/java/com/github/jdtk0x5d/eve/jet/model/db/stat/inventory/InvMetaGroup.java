package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invMetaGroups" database table.
 */
@Entity
@Table(name = "\"invMetaGroups\"")
@NamedQuery(name = "InvMetaGroup.findAll", query = "SELECT i FROM InvMetaGroup i")
public class InvMetaGroup implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"description\"")
  private String description;

  @Column(name = "\"iconID\"")
  private Integer iconID;

  @Column(name = "\"metaGroupID\"")
  private Integer metaGroupID;

  @Column(name = "\"metaGroupName\"")
  private String metaGroupName;

  public InvMetaGroup() {
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public void setIconID(Integer iconID) {
    this.iconID = iconID;
  }

  public Integer getMetaGroupID() {
    return this.metaGroupID;
  }

  public void setMetaGroupID(Integer metaGroupID) {
    this.metaGroupID = metaGroupID;
  }

  public String getMetaGroupName() {
    return this.metaGroupName;
  }

  public void setMetaGroupName(String metaGroupName) {
    this.metaGroupName = metaGroupName;
  }

  @Override
  public String toString() {
    return "InvMetaGroup{" + "description=[" + description + "], iconID=[" + iconID + "], metaGroupID=[" + metaGroupID + "], metaGroupName=[" + metaGroupName + "]}";
  }
}