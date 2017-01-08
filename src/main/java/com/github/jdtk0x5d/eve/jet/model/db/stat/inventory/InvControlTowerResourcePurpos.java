package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invControlTowerResourcePurposes" database table.
 */
@Entity
@Table(name = "\"invControlTowerResourcePurposes\"")
@NamedQuery(name = "InvControlTowerResourcePurpos.findAll", query = "SELECT i FROM InvControlTowerResourcePurpos i")
public class InvControlTowerResourcePurpos implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"purpose\"")
  private Integer purpose;

  @Column(name = "\"purposeText\"")
  private String purposeText;

  public InvControlTowerResourcePurpos() {
  }

  public Integer getPurpose() {
    return this.purpose;
  }

  public void setPurpose(Integer purpose) {
    this.purpose = purpose;
  }

  public String getPurposeText() {
    return this.purposeText;
  }

  public void setPurposeText(String purposeText) {
    this.purposeText = purposeText;
  }

  @Override
  public String toString() {
    return "InvControlTowerResourcePurpos{" + "purpose=[" + purpose + "], purposeText=[" + purposeText + "]}";
  }
}