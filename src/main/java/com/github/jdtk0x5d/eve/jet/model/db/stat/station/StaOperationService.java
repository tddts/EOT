package com.github.jdtk0x5d.eve.jet.model.db.stat.station;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "staOperationServices" database table.
 */
@Entity
@Table(name = "\"staOperationServices\"")
@NamedQuery(name = "StaOperationService.findAll", query = "SELECT s FROM StaOperationService s")
public class StaOperationService implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"operationID\"")
  private Integer operationID;

  @Column(name = "\"serviceID\"")
  private Integer serviceID;

  public StaOperationService() {
  }

  public Integer getOperationID() {
    return this.operationID;
  }

  public void setOperationID(Integer operationID) {
    this.operationID = operationID;
  }

  public Integer getServiceID() {
    return this.serviceID;
  }

  public void setServiceID(Integer serviceID) {
    this.serviceID = serviceID;
  }

  @Override
  public String toString() {
    return "StaOperationService{" + "operationID=[" + operationID + "], serviceID=[" + serviceID + "]}";
  }
}