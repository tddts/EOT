package com.github.tddts.jet.model.app;

import com.github.tddts.jet.consts.RouteOption;
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
