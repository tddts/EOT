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

package com.github.tddts.evetrader.rest.client.esi.impl;

import com.github.tddts.evetrader.config.spring.annotations.RestClient;
import com.github.tddts.evetrader.config.spring.annotations.Retry;
import com.github.tddts.evetrader.consts.RouteOption;
import com.github.tddts.evetrader.rest.RestResponse;
import com.github.tddts.evetrader.rest.client.esi.RouteClient;
import com.github.tddts.evetrader.rest.provider.RestClientTemplate;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class RouteClientImpl implements RouteClient {

  @Value("${path.routes}")
  private String addressRoutes;

  @Autowired
  private RestClientTemplate client;

  @Override
  @Retry
  public RestResponse<List<Integer>> getRoute(int origin, int destination, int[] avoid, int[] connections, RouteOption routeOption) {
    UriComponentsBuilder builder = client.apiUriBuilder(addressRoutes);

    if (connections.length > 0) builder = builder.queryParam("avoid", StringUtils.join(avoid, ','));
    if (avoid.length > 0) builder = builder.queryParam("connections", StringUtils.join(connections, ','));

    String url = builder.queryParam("flag", routeOption.getValue()).build().toString();

    Map<String, ?> uriVariables = ImmutableMap.of(
        "origin", origin,
        "destination", destination);

    return RestResponse.fromArrayResponse(client.restOperations().getForEntity(url, Integer[].class, uriVariables));
  }
}
