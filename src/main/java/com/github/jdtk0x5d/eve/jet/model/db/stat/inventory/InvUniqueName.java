package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invUniqueNames" database table.
 * 
 */
@Entity
@Table(name="\"invUniqueNames\"")
@NamedQuery(name="InvUniqueName.findAll", query="SELECT i FROM InvUniqueName i")
public class InvUniqueName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"groupID\"")
	private Integer groupID;

	@Column(name="\"itemID\"")
	private Integer itemID;

	@Column(name="\"itemName\"")
	private String itemName;

	public InvUniqueName() {
	}

	public Integer getGroupID() {
		return this.groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
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
		return "InvUniqueName{" + "groupID=[" + groupID + "], itemID=[" + itemID + "], itemName=[" + itemName + "]}";
	}
}