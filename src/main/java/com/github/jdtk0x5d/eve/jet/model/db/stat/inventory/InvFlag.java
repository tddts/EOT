package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invFlags" database table.
 */
@Entity
@Table(name = "\"invFlags\"")
@NamedQuery(name = "InvFlag.findAll", query = "SELECT i FROM InvFlag i")
public class InvFlag implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"flagID\"")
  private Integer flagID;

  @Column(name = "\"flagName\"")
  private String flagName;

  @Column(name = "\"flagText\"")
  private String flagText;

  @Column(name = "\"orderID\"")
  private Integer orderID;

  public InvFlag() {
  }

  public Integer getFlagID() {
    return this.flagID;
  }

  public void setFlagID(Integer flagID) {
    this.flagID = flagID;
  }

  public String getFlagName() {
    return this.flagName;
  }

  public void setFlagName(String flagName) {
    this.flagName = flagName;
  }

  public String getFlagText() {
    return this.flagText;
  }

  public void setFlagText(String flagText) {
    this.flagText = flagText;
  }

  public Integer getOrderID() {
    return this.orderID;
  }

  public void setOrderID(Integer orderID) {
    this.orderID = orderID;
  }

  @Override
  public String toString() {
    return "InvFlag{" + "flagID=[" + flagID + "], flagName=[" + flagName + "], flagText=[" + flagText + "], orderID=[" + orderID + "]}";
  }
}