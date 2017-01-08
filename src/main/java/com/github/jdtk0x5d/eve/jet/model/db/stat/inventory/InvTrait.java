package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invTraits" database table.
 */
@Entity
@Table(name = "\"invTraits\"")
@NamedQuery(name = "InvTrait.findAll", query = "SELECT i FROM InvTrait i")
public class InvTrait implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"bonus\"")
  private Double bonus;

  @Column(name = "\"bonusText\"")
  private String bonusText;

  @Column(name = "\"skillID\"")
  private Integer skillID;

  @Column(name = "\"traitID\"")
  private Integer traitID;

  @Column(name = "\"typeID\"")
  private Integer typeID;

  @Column(name = "\"unitID\"")
  private Integer unitID;

  public InvTrait() {
  }

  public Double getBonus() {
    return this.bonus;
  }

  public void setBonus(Double bonus) {
    this.bonus = bonus;
  }

  public String getBonusText() {
    return this.bonusText;
  }

  public void setBonusText(String bonusText) {
    this.bonusText = bonusText;
  }

  public Integer getSkillID() {
    return this.skillID;
  }

  public void setSkillID(Integer skillID) {
    this.skillID = skillID;
  }

  public Integer getTraitID() {
    return this.traitID;
  }

  public void setTraitID(Integer traitID) {
    this.traitID = traitID;
  }

  public Integer getTypeID() {
    return this.typeID;
  }

  public void setTypeID(Integer typeID) {
    this.typeID = typeID;
  }

  public Integer getUnitID() {
    return this.unitID;
  }

  public void setUnitID(Integer unitID) {
    this.unitID = unitID;
  }

  @Override
  public String toString() {
    return "InvTrait{" + "bonus=[" + bonus + "], bonusText=[" + bonusText + "], skillID=["
        + skillID + "], traitID=[" + traitID + "], typeID=[" + typeID + "], unitID=[" + unitID + "]}";
  }
}