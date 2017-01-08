package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invItems" database table.
 */
@Entity
@Table(name = "\"invItems\"")
@NamedQuery(name = "InvItem.findAll", query = "SELECT i FROM InvItem i")
public class InvItem implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"flagID\"")
  private Integer flagID;

  @Column(name = "\"itemID\"")
  private Integer itemID;

  @Column(name = "\"locationID\"")
  private Integer locationID;

  @Column(name = "\"ownerID\"")
  private Integer ownerID;

  @Column(name = "\"quantity\"")
  private Integer quantity;

  @Column(name = "\"typeID\"")
  private Integer typeID;

  public InvItem() {
  }

  public Integer getFlagID() {
    return this.flagID;
  }

  public void setFlagID(Integer flagID) {
    this.flagID = flagID;
  }

  public Integer getItemID() {
    return this.itemID;
  }

  public void setItemID(Integer itemID) {
    this.itemID = itemID;
  }

  public Integer getLocationID() {
    return this.locationID;
  }

  public void setLocationID(Integer locationID) {
    this.locationID = locationID;
  }

  public Integer getOwnerID() {
    return this.ownerID;
  }

  public void setOwnerID(Integer ownerID) {
    this.ownerID = ownerID;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getTypeID() {
    return this.typeID;
  }

  public void setTypeID(Integer typeID) {
    this.typeID = typeID;
  }

  @Override
  public String toString() {
    return "InvItem{" + "flagID=[" + flagID + "], itemID=[" + itemID + "], locationID=[" + locationID + "], ownerID=[" + ownerID + "], quantity=[" + quantity + "], typeID=[" + typeID + "]}";
  }
}