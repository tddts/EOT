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
