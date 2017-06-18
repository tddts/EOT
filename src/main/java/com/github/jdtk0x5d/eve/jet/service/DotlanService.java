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

package com.github.jdtk0x5d.eve.jet.service;

import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.model.client.dotlan.DotlanRoute;

/**
 * {@code DotlanService} represents a service providing access to navigation at
 * <a href="http://evemaps.dotlan.net/">evemaps.dotlan.net</a>
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface DotlanService {

  /**
   * Get route for given waypoints and options.
   *
   * @param dotlanRouteOption route options
   * @param waypoints         array of waypoints
   * @return route
   */
  DotlanRoute getRoute(DotlanRouteOption dotlanRouteOption, String... waypoints);

}
