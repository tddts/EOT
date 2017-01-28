package com.github.jdtk0x5d.eve.jet.rest.api.dotlan;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.model.api.dotlan.DotlanRoute;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface DotlanAPI {

  RestResponse<DotlanRoute> getRoute(String[] waypoints, DotlanRouteOption routeOption);
}
