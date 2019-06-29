/*
 * Copyright 2018 Tigran Dadaiants
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

package com.github.tddts.evetrader.model.app;

import com.github.tddts.evetrader.consts.RouteOption;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code RouteParams} represents parameters for route search.
 * Default route option is {@link RouteOption#SECURE}.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class RouteParams {

  private int origin;
  private int destination;

  private List<Integer> avoid = new ArrayList<>();
  private RouteOption routeOption = RouteOption.SECURE;

  private RouteParams(int origin, int destination) {
    this.origin = origin;
    this.destination = destination;
  }

  public static RouteParams of(int origin, int destination) {
    return new RouteParams(origin, destination);
  }

  public RouteParams avoid(int system) {
    avoid.add(system);
    return this;
  }

  public RouteParams avoid(int... systems) {
    avoid.addAll(Ints.asList(systems));
    return this;
  }

  public RouteParams with(RouteOption routeOption) {
    this.routeOption = routeOption;
    return this;
  }

  public int getOrigin() {
    return origin;
  }

  public int getDestination() {
    return destination;
  }

  public List<Integer> getAvoidedSystems() {
    return avoid;
  }

  public RouteOption getRouteOption() {
    return routeOption;
  }
}
