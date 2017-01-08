package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invGroups" database table.
 */
@Entity
@Table(name = "\"invGroups\"")
@NamedQuery(name = "InvGroup.findAll", query = "SELECT i FROM InvGroup i")
public class InvGroup implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"anchorable\"")
  private String anchorable;

  @Column(name = "\"anchored\"")
  private String anchored;

  @Column(name = "\"categoryID\"")
  private Integer categoryID;

  @Column(name = "\"fittableNonSingleton\"")
  private String fittableNonSingleton;

  @Column(name = "\"groupID\"")
  private Integer groupID;

  @Column(name = "\"groupName\"")
  private String groupName;

  @Column(name = "\"iconID\"")
  private Integer iconID;

  @Column(name = "\"published\"")
  private String published;

  @Column(name = "\"useBasePrice\"")
  private String useBasePrice;

  public InvGroup() {
  }

  public String getAnchorable() {
    return this.anchorable;
  }

  public void setAnchorable(String anchorable) {
    this.anchorable = anchorable;
  }

  public String getAnchored() {
    return this.anchored;
  }

  public void setAnchored(String anchored) {
    this.anchored = anchored;
  }

  public Integer getCategoryID() {
    return this.categoryID;
  }

  public void setCategoryID(Integer categoryID) {
    this.categoryID = categoryID;
  }

  public String getFittableNonSingleton() {
    return this.fittableNonSingleton;
  }

  public void setFittableNonSingleton(String fittableNonSingleton) {
    this.fittableNonSingleton = fittableNonSingleton;
  }

  public Integer getGroupID() {
    return this.groupID;
  }

  public void setGroupID(Integer groupID) {
    this.groupID = groupID;
  }

  public String getGroupName() {
    return this.groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public void setIconID(Integer iconID) {
    this.iconID = iconID;
  }

  public String getPublished() {
    return this.published;
  }

  public void setPublished(String published) {
    this.published = published;
  }

  public String getUseBasePrice() {
    return this.useBasePrice;
  }

  public void setUseBasePrice(String useBasePrice) {
    this.useBasePrice = useBasePrice;
  }

  @Override
  public String toString() {
    return "InvGroup{" + "anchorable=[" + anchorable + "], anchored=[" + anchored + "], categoryID=["
        + categoryID + "], fittableNonSingleton=[" + fittableNonSingleton + "], groupID=[" + groupID + "], groupName=["
        + groupName + "], iconID=[" + iconID + "], published=[" + published + "], useBasePrice=[" + useBasePrice + "]}";
  }
}