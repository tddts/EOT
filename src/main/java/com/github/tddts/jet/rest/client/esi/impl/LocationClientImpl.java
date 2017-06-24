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

import com.github.tddts.jet.model.client.esi.location.Location;
import com.github.tddts.jet.model.client.esi.location.Ship;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.esi.LocationClient;
import com.github.tddts.jet.rest.provider.RestClientProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class LocationClientImpl implements LocationClient {

  @Value("${path.character.location}")
  private String addressLocation;

  @Value("${path.character.ship}")
  private String addressShip;

  @Autowired
  private RestClientProvider client;

  @Override
  public RestResponse<Location> getLocation(long character_id) {
    return new RestResponse<>(
        client.restOperations().exchange(
            client.apiUrl(addressLocation), HttpMethod.GET, client.authorizedEntity(), Location.class, character_id));
  }

  @Override
  public RestResponse<Ship> getShip(long character_id) {
    return new RestResponse<>(
        client.restOperations().exchange(
            client.apiUrl(addressShip), HttpMethod.GET, client.authorizedEntity(), Ship.class, character_id));
  }

}
