package com.github.jdtk0x5d.eve.jet.service;

import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.model.client.dotlan.DotlanRoute;
/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface DotlanService {

  DotlanRoute getRoute(DotlanRouteOption dotlanRouteOption, String... waypoints);

}
