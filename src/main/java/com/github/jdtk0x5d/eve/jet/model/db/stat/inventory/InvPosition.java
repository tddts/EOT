package com.github.jdtk0x5d.eve.jet.model.db.stat.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "invPositions" database table.
 * 
 */
@Entity
@Table(name="\"invPositions\"")
@NamedQuery(name="InvPosition.findAll", query="SELECT i FROM InvPosition i")
public class InvPosition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"itemID\"")
	private Integer itemID;

	@Column(name="\"pitch\"")
	private Double pitch;

	@Column(name="\"roll\"")
	private Double roll;

	@Column(name="\"x\"")
	private Double x;

	@Column(name="\"y\"")
	private Double y;

	@Column(name="\"yaw\"")
	private Double yaw;

	@Column(name="\"z\"")
	private Double z;

	public InvPosition() {
	}

	public Integer getItemID() {
		return this.itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Double getPitch() {
		return this.pitch;
	}

	public void setPitch(Double pitch) {
		this.pitch = pitch;
	}

	public Double getRoll() {
		return this.roll;
	}

	public void setRoll(Double roll) {
		this.roll = roll;
	}

	public Double getX() {
		return this.x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return this.y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getYaw() {
		return this.yaw;
	}

	public void setYaw(Double yaw) {
		this.yaw = yaw;
	}

	public Double getZ() {
		return this.z;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "InvPosition{" + "itemID=[" + itemID + "], pitch=[" + pitch + "], roll=[" + roll + "], x=["
				+ x + "], y=[" + y + "], yaw=[" + yaw + "], z=[" + z + "]}";
	}
}