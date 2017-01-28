package com.github.jdtk0x5d.eve.jet.rest.api.esi.impl;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestApi;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.UniverseAPI;
import com.github.jdtk0x5d.eve.jet.model.api.esi.input.IdArray;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseName;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseStation;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseStructure;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseType;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.*;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestApi
public class UniverseAPIImpl implements UniverseAPI {

  @Value("${url.universe.names}")
  private String addressNames;

  @Value("${url.universe.station}")
  private String addressStation;

  @Value("${url.universe.structure}")
  private String addressStructure;

  @Value("${url.universe.structures}")
  private String addressStructures;

  @Value("${url.universe.system}")
  private String addressSystem;

  @Value("${url.universe.type}")
  private String addressType;

  @Autowired
  private Gson gson;

  @Override
  public RestResponse<List<UniverseName>> getNames(long[] ids) {
    String requestBody = gson.toJson(new IdArray(ids));
    return RestResponse.fromArrayResponse(restOperations().exchange(apiUrl(addressNames), HttpMethod.POST, jsonEntity(requestBody), UniverseName[].class));
  }

  @Override
  public RestResponse<UniverseStation> getStation(int stationId) {
    return new RestResponse<>(restOperations().getForEntity(apiUrl(addressStation), UniverseStation.class, stationId));
  }

  @Override
  public RestResponse<UniverseStructure> getStructure(int structureId) {
    return new RestResponse<>(restOperations().getForEntity(apiUrl(addressStructure), UniverseStructure.class, structureId));
  }

  @Override
  public RestResponse<UniverseType> getType(long typeId) {
    return new RestResponse<>(restOperations().getForEntity(apiUrl(addressType), UniverseType.class, typeId));
  }

  @Override
  public RestResponse<String> getSystemName(int systemId) {
    return new RestResponse<>(restOperations().getForEntity(apiUrl(addressSystem), String.class, systemId));
  }

  @Override
  public RestResponse<List<Long>> getAllStructureIds() {
    return RestResponse.fromArrayResponse(restOperations().getForEntity(apiUrl(addressStructures), Long[].class));
  }
}
