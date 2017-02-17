package com.github.jdtk0x5d.eve.jet.model.db;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Entity
public class RouteCache {

  @EmbeddedId
  private RouteCachePK routeKey;
  @Column
  private String routeJson;

  public RouteCache() {
  }

  public RouteCache(RouteCachePK routeKey, String routeJson) {
    this.routeKey = routeKey;
    this.routeJson = routeJson;
  }

  public RouteCache(Long firstPoint, Long secondPoint, String routeJson) {
    this.routeKey = new RouteCachePK(firstPoint, secondPoint);
    this.routeJson = routeJson;
  }

  public RouteCachePK getRouteKey() {
    return routeKey;
  }

  public void setRouteKey(RouteCachePK routeKey) {
    this.routeKey = routeKey;
  }

  public String getRouteJson() {
    return routeJson;
  }

  public void setRouteJson(String routeJson) {
    this.routeJson = routeJson;
  }
}
