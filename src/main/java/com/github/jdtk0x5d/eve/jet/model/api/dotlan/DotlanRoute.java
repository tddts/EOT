package com.github.jdtk0x5d.eve.jet.model.api.dotlan;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DotlanRoute {

  private List<Integer> systems;
  private List<Integer> alternative;
  private List<Integer> waypoints;
  private List<DotlanJump> jumps;

  public List<Integer> getSystems() {
    return systems;
  }

  public void setSystems(List<Integer> systems) {
    this.systems = systems;
  }

  public List<Integer> getAlternative() {
    return alternative;
  }

  public void setAlternative(List<Integer> alternative) {
    this.alternative = alternative;
  }

  public List<Integer> getWaypoints() {
    return waypoints;
  }

  public void setWaypoints(List<Integer> waypoints) {
    this.waypoints = waypoints;
  }

  public List<DotlanJump> getJumps() {
    return jumps;
  }

  public void setJumps(List<DotlanJump> jumps) {
    this.jumps = jumps;
  }

  public int getJumpsCount() {
    return jumps.size();
  }

  @Override
  public String toString() {
    return "DotlanRoute{" + "systems=[" + systems + "], alternative=[" + alternative + "], waypoints=["
        + waypoints + "], jumps=[" + jumps + "]}";
  }
}
