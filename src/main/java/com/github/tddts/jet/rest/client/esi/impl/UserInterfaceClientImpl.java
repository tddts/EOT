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

package com.github.tddts.jet.rest.client.esi.impl;

import com.github.tddts.jet.config.spring.annotations.RestClient;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.esi.UserInterfaceClient;
import com.github.tddts.jet.rest.provider.RestClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class UserInterfaceClientImpl implements UserInterfaceClient {

  @Value("${path.ui.waypoint}")
  private String addressWaypoint;

  @Value("${path.ui.market.details}")
  private String addressMarketDetails;

  @Autowired
  private RestClientTemplate client;

  @Override
  public RestResponse<String> setWaypoint(int destinationId, boolean clearOtherWaypoints, boolean addToBeginning) {
    String url = client.apiUriBuilder(addressWaypoint)
        .queryParam("destination_id", destinationId)
        .queryParam("clear_other_waypoints", clearOtherWaypoints)
        .queryParam("add_to_beginning", addToBeginning)
        .build().toString();

    return new RestResponse<>(
        client.restOperations().exchange(url, HttpMethod.POST, client.authorizedEntity(), String.class));
  }

  @Override
  public RestResponse<String> openMarketDetails(int typeId) {
    String url = client.apiUriBuilder(addressMarketDetails)
        .queryParam("type_id", typeId).build().toString();

    return new RestResponse<>(
        client.restOperations().exchange(url, HttpMethod.POST, client.authorizedEntity(), String.class));
  }

}
