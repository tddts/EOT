/*
 * Copyright 2017 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.jet.model.client.dotlan;

import java.util.List;

/**
 * {@code DotlanRoute} represents a route between two systems in
 * <a href="http://evemaps.dotlan.net/">evemaps.dotlan.net</a>
 *
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
