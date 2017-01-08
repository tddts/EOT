package com.github.jdtk0x5d.eve.jet.api.dotlan;

import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.model.api.dotlan.DotlanRoute;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface DotlanAPI {

  DotlanRoute getRoute(String[] waypoints, DotlanRouteOption routeOption);
}
