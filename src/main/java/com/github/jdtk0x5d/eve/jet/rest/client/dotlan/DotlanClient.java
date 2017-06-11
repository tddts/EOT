package com.github.jdtk0x5d.eve.jet.rest.client.dotlan;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.model.client.dotlan.DotlanRoute;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface DotlanClient {

  RestResponse<DotlanRoute> getRoute(DotlanRouteOption routeOption, String... waypoints);
}
