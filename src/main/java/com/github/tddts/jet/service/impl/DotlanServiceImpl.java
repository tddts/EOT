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

package com.github.tddts.jet.service.impl;

import com.github.tddts.jet.model.client.dotlan.DotlanRouteOption;
import com.github.tddts.jet.exception.DotlanResponseParsingException;
import com.github.tddts.jet.model.client.dotlan.DotlanRoute;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.dotlan.DotlanClient;
import com.github.tddts.jet.service.DotlanService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class DotlanServiceImpl implements DotlanService {

  @Value("${dotlan.regex}")
  private String dotlanRegex;

  @Autowired
  private Gson gson;

  @Autowired
  private DotlanClient dotlanClient;

  public DotlanRoute getRoute(DotlanRouteOption dotlanRouteOption, String... waypoints) {
    RestResponse<String> dotlanPageResponse = dotlanClient.getRoutePage(dotlanRouteOption, waypoints);
    return gson.fromJson(getPathJson(dotlanPageResponse.getObject()), DotlanRoute.class);
  }

  private String getPathJson(String responseBody) {
    Matcher matcher = Pattern.compile(dotlanRegex).matcher(responseBody);
    if (matcher.find()) return matcher.group(1);
    throw new DotlanResponseParsingException("Could not parse dotlan response, body received:\n" + responseBody);
  }
}
