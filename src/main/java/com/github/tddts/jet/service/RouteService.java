package com.github.tddts.jet.service;

import com.github.tddts.jet.model.app.OrderRoute;
import com.github.tddts.jet.model.app.RouteParams;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface RouteService {

  OrderRoute getRoute(RouteParams routeParams);
}
