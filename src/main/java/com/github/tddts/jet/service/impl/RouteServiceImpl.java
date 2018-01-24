package com.github.tddts.jet.service.impl;

import com.github.tddts.jet.model.app.OrderRoute;
import com.github.tddts.jet.model.app.RouteParams;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.esi.RouteClient;
import com.github.tddts.jet.service.RouteService;
import com.google.common.primitives.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class RouteServiceImpl implements RouteService {

  @Autowired
  private RouteClient routeClient;

  @Override
  public OrderRoute getRoute(RouteParams routeParams) {
    RestResponse<List<Integer>> routeRestResponse = routeClient.getRoute(
        routeParams.getOrigin(),
        routeParams.getDestination(),
        Ints.toArray(routeParams.getAvoidedSystems()), new int[]{},
        routeParams.getRouteOption());

    return routeRestResponse.isSuccessful()
        ? new OrderRoute(routeRestResponse.getObject())
        : new OrderRoute();
  }
}
