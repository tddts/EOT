package com.github.jdtk0x5d.eve.jet.model.db;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Entity
public class RouteCache {

  @Column
  private Long startPointId;
  @Column
  private Long endPointId;
  @Column
  private String routeJson;

  public RouteCache() {
  }

  public RouteCache(Long startPointId, Long endPointId, String routeJson) {
    this.startPointId = startPointId;
    this.endPointId = endPointId;
    this.routeJson = routeJson;
  }

  public Long getStartPointId() {
    return startPointId;
  }

  public void setStartPointId(Long startPointId) {
    this.startPointId = startPointId;
  }

  public Long getEndPointId() {
    return endPointId;
  }

  public void setEndPointId(Long endPointId) {
    this.endPointId = endPointId;
  }

  public String getRouteJson() {
    return routeJson;
  }

  public void setRouteJson(String routeJson) {
    this.routeJson = routeJson;
  }
}
