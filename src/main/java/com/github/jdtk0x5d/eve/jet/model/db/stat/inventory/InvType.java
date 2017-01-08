package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invTypes" database table.
 */
@Entity
@Table(name = "\"invTypes\"")
@NamedQuery(name = "InvType.findAll", query = "SELECT i FROM InvType i")
public class InvType implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"basePrice\"")
  private String basePrice;

  @Column(name = "\"capacity\"")
  private Double capacity;

  @Column(name = "\"description\"")
  private String description;

  @Column(name = "\"graphicID\"")
  private Integer graphicID;

  @Column(name = "\"groupID\"")
  private Integer groupID;

  @Column(name = "\"iconID\"")
  private Integer iconID;

  @Column(name = "\"marketGroupID\"")
  private Integer marketGroupID;

  @Column(name = "\"mass\"")
  private Double mass;

  @Column(name = "\"portionSize\"")
  private Integer portionSize;

  @Column(name = "\"published\"")
  private String published;

  @Column(name = "\"raceID\"")
  private Integer raceID;

  @Column(name = "\"soundID\"")
  private Integer soundID;

  @Column(name = "\"typeID\"")
  private Integer typeID;

  @Column(name = "\"typeName\"")
  private String typeName;

  @Column(name = "\"volume\"")
  private Double volume;

  public InvType() {
  }

  public String getBasePrice() {
    return this.basePrice;
  }

  public void setBasePrice(String basePrice) {
    this.basePrice = basePrice;
  }

  public Double getCapacity() {
    return this.capacity;
  }

  public void setCapacity(Double capacity) {
    this.capacity = capacity;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getGraphicID() {
    return this.graphicID;
  }

  public void setGraphicID(Integer graphicID) {
    this.graphicID = graphicID;
  }

  public Integer getGroupID() {
    return this.groupID;
  }

  public void setGroupID(Integer groupID) {
    this.groupID = groupID;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public void setIconID(Integer iconID) {
    this.iconID = iconID;
  }

  public Integer getMarketGroupID() {
    return this.marketGroupID;
  }

  public void setMarketGroupID(Integer marketGroupID) {
    this.marketGroupID = marketGroupID;
  }

  public Double getMass() {
    return this.mass;
  }

  public void setMass(Double mass) {
    this.mass = mass;
  }

  public Integer getPortionSize() {
    return this.portionSize;
  }

  public void setPortionSize(Integer portionSize) {
    this.portionSize = portionSize;
  }

  public String getPublished() {
    return this.published;
  }

  public void setPublished(String published) {
    this.published = published;
  }

  public Integer getRaceID() {
    return this.raceID;
  }

  public void setRaceID(Integer raceID) {
    this.raceID = raceID;
  }

  public Integer getSoundID() {
    return this.soundID;
  }

  public void setSoundID(Integer soundID) {
    this.soundID = soundID;
  }

  public Integer getTypeID() {
    return this.typeID;
  }

  public void setTypeID(Integer typeID) {
    this.typeID = typeID;
  }

  public String getTypeName() {
    return this.typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Double getVolume() {
    return this.volume;
  }

  public void setVolume(Double volume) {
    this.volume = volume;
  }

  @Override
  public String toString() {
    return "InvType{" + "basePrice=[" + basePrice + "], capacity=[" + capacity + "], description=["
        + description + "], graphicID=[" + graphicID + "], groupID=[" + groupID + "], iconID=["
        + iconID + "], marketGroupID=[" + marketGroupID + "], mass=[" + mass + "], portionSize=["
        + portionSize + "], published=[" + published + "], raceID=[" + raceID + "], soundID=["
        + soundID + "], typeID=[" + typeID + "], typeName=[" + typeName + "], volume=[" + volume + "]}";
  }
}