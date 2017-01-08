package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invMetaTypes" database table.
 */
@Entity
@Table(name = "\"invMetaTypes\"")
@NamedQuery(name = "InvMetaType.findAll", query = "SELECT i FROM InvMetaType i")
public class InvMetaType implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"metaGroupID\"")
  private Integer metaGroupID;

  @Column(name = "\"parentTypeID\"")
  private Integer parentTypeID;

  @Column(name = "\"typeID\"")
  private Integer typeID;

  public InvMetaType() {
  }

  public Integer getMetaGroupID() {
    return this.metaGroupID;
  }

  public void setMetaGroupID(Integer metaGroupID) {
    this.metaGroupID = metaGroupID;
  }

  public Integer getParentTypeID() {
    return this.parentTypeID;
  }

  public void setParentTypeID(Integer parentTypeID) {
    this.parentTypeID = parentTypeID;
  }

  public Integer getTypeID() {
    return this.typeID;
  }

  public void setTypeID(Integer typeID) {
    this.typeID = typeID;
  }

  @Override
  public String toString() {
    return "InvMetaType{" + "metaGroupID=[" + metaGroupID + "], parentTypeID=[" + parentTypeID + "], typeID=[" + typeID + "]}";
  }
}