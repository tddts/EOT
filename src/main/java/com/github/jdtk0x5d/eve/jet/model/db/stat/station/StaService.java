package com.github.jdtk0x5d.eve.jet.model.db.stat.station;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "staServices" database table.
 */
@Entity
@Table(name = "\"staServices\"")
@NamedQuery(name = "StaService.findAll", query = "SELECT s FROM StaService s")
public class StaService implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"description\"")
  private String description;

  @Column(name = "\"serviceID\"")
  private Integer serviceID;

  @Column(name = "\"serviceName\"")
  private String serviceName;

  public StaService() {
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getServiceID() {
    return this.serviceID;
  }

  public void setServiceID(Integer serviceID) {
    this.serviceID = serviceID;
  }

  public String getServiceName() {
    return this.serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  @Override
  public String toString() {
    return "StaService{" + "description=[" + description + "], serviceID=[" + serviceID + "], serviceName=[" + serviceName + "]}";
  }
}