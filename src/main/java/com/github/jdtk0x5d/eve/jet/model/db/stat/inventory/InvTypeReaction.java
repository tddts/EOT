package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invTypeReactions" database table.
 */
@Entity
@Table(name = "\"invTypeReactions\"")
@NamedQuery(name = "InvTypeReaction.findAll", query = "SELECT i FROM InvTypeReaction i")
public class InvTypeReaction implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"input\"")
  private String input;

  @Column(name = "\"quantity\"")
  private Integer quantity;

  @Column(name = "\"reactionTypeID\"")
  private Integer reactionTypeID;

  @Column(name = "\"typeID\"")
  private Integer typeID;

  public InvTypeReaction() {
  }

  public String getInput() {
    return this.input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getReactionTypeID() {
    return this.reactionTypeID;
  }

  public void setReactionTypeID(Integer reactionTypeID) {
    this.reactionTypeID = reactionTypeID;
  }

  public Integer getTypeID() {
    return this.typeID;
  }

  public void setTypeID(Integer typeID) {
    this.typeID = typeID;
  }

  @Override
  public String toString() {
    return "InvTypeReaction{" + "input=[" + input + "], quantity=[" + quantity + "], reactionTypeID=[" + reactionTypeID + "], typeID=[" + typeID + "]}";
  }
}