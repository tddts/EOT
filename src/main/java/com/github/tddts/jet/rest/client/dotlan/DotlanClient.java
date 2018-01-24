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

package com.github.tddts.jet.rest.client.dotlan;

import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.model.client.dotlan.DotlanRouteOption;

/**
 * {@code DotlanClient} represents REST client for <a href="http://evemaps.dotlan.net/">evemaps.dotlan.net</a>
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface DotlanClient {

  RestResponse<String> getRoutePage(DotlanRouteOption routeOption, String... waypoints);
}
