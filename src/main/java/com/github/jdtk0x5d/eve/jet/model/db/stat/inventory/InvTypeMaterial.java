package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invTypeMaterials" database table.
 */
@Entity
@Table(name = "\"invTypeMaterials\"")
@NamedQuery(name = "InvTypeMaterial.findAll", query = "SELECT i FROM InvTypeMaterial i")
public class InvTypeMaterial implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"materialTypeID\"")
  private Integer materialTypeID;

  @Column(name = "\"quantity\"")
  private Integer quantity;

  @Column(name = "\"typeID\"")
  private Integer typeID;

  public InvTypeMaterial() {
  }

  public Integer getMaterialTypeID() {
    return this.materialTypeID;
  }

  public void setMaterialTypeID(Integer materialTypeID) {
    this.materialTypeID = materialTypeID;
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
    return "InvTypeMaterial{" + "materialTypeID=[" + materialTypeID + "], quantity=[" + quantity + "], typeID=[" + typeID + "]}";
  }
}