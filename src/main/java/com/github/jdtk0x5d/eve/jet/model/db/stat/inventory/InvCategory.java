package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invCategories" database table.
 */
@Entity
@Table(name = "\"invCategories\"")
@NamedQuery(name = "InvCategory.findAll", query = "SELECT i FROM InvCategory i")
public class InvCategory implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"categoryID\"")
  private Integer categoryID;

  @Column(name = "\"categoryName\"")
  private String categoryName;

  @Column(name = "\"iconID\"")
  private Integer iconID;

  @Column(name = "\"published\"")
  private String published;

  public InvCategory() {
  }

  public Integer getCategoryID() {
    return this.categoryID;
  }

  public void setCategoryID(Integer categoryID) {
    this.categoryID = categoryID;
  }

  public String getCategoryName() {
    return this.categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
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

  @Override
  public String toString() {
    return "InvCategory{" + "categoryID=[" + categoryID + "], categoryName=[" + categoryName + "], iconID=[" + iconID + "], published=[" + published + "]}";
  }
}