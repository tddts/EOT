package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invNames" database table.
 */
@Entity
@Table(name = "\"invNames\"")
@NamedQuery(name = "InvName.findAll", query = "SELECT i FROM InvName i")
public class InvName implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"itemID\"")
  private Integer itemID;

  @Column(name = "\"itemName\"")
  private String itemName;

  public InvName() {
  }

  public Integer getItemID() {
    return this.itemID;
  }

  public void setItemID(Integer itemID) {
    this.itemID = itemID;
  }

  public String getItemName() {
    return this.itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public String toString() {
    return "InvName{" + "itemID=[" + itemID + "], itemName=[" + itemName + "]}";
  }
}