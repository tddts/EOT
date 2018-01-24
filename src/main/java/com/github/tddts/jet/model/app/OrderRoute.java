package com.github.tddts.jet.model.app;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class OrderRoute {

  private List<Integer> waypoints;

  public OrderRoute() {
  }

  public OrderRoute(List<Integer> waypoints) {
    this.waypoints = waypoints;
  }

  public List<Integer> getWaypoints() {
    return waypoints;
  }

  public void setWaypoints(List<Integer> waypoints) {
    this.waypoints = waypoints;
  }

  public int getJumpsCount() {
    return waypoints != null && !waypoints.isEmpty() ? waypoints.size() - 1 : 0;
  }
}
