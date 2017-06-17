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

package com.github.jdtk0x5d.eve.jet.rest.client.esi.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestClient;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Retry;
import com.github.jdtk0x5d.eve.jet.model.client.esi.universe.UniverseName;
import com.github.jdtk0x5d.eve.jet.model.client.esi.universe.UniverseStation;
import com.github.jdtk0x5d.eve.jet.model.client.esi.universe.UniverseStructure;
import com.github.jdtk0x5d.eve.jet.model.client.esi.universe.UniverseType;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.rest.client.esi.UniverseClient;
import com.github.jdtk0x5d.eve.jet.rest.provider.RestClientProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class UniverseClientImpl implements UniverseClient {

  @Value("${path.universe.names}")
  private String addressNames;

  @Value("${path.universe.station}")
  private String addressStation;

  @Value("${path.universe.structure}")
  private String addressStructure;

  @Value("${path.universe.structures}")
  private String addressStructures;

  @Value("${path.universe.system}")
  private String addressSystem;

  @Value("${path.universe.type}")
  private String addressType;

  @Autowired
  private RestClientProvider client;

  @Retry
  @Override
  public RestResponse<List<UniverseName>> getNames(int... ids) {
    return RestResponse.fromArrayResponse(client.restOperations().exchange(
        client.apiUrl(addressNames), HttpMethod.POST, client.jsonEntity(ids), UniverseName[].class));
  }

  @Override
  public RestResponse<UniverseStation> getStation(int stationId) {
    return new RestResponse<>(client.restOperations().getForEntity(
        client.apiUrl(addressStation), UniverseStation.class, stationId));
  }

  @Override
  public RestResponse<UniverseStructure> getStructure(int structureId) {
    return new RestResponse<>(client.restOperations().getForEntity(
        client.apiUrl(addressStructure), UniverseStructure.class, structureId));
  }

  @Override
  public RestResponse<UniverseType> getType(long typeId) {
    return new RestResponse<>(client.restOperations().getForEntity(
        client.apiUrl(addressType), UniverseType.class, typeId));
  }

  @Override
  public RestResponse<String> getSystemName(int systemId) {
    return new RestResponse<>(client.restOperations().getForEntity(
        client.apiUrl(addressSystem), String.class, systemId));
  }

  @Override
  public RestResponse<List<Long>> getAllStructureIds() {
    return RestResponse.fromArrayResponse(client.restOperations().getForEntity(
        client.apiUrl(addressStructures), Long[].class));
  }
}
