package com.github.jdtk0x5d.eve.jet.rest.esi.impl;

import com.github.jdtk0x5d.eve.jet.rest.esi.UniverseAPI;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.NullOnException;
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
@NullOnException
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
  public List<UniverseName> getNames(long[] ids) {
    String body = gson.toJson(new IdArray(ids));
    UniverseName[] names = restOperations().exchange(apiUrl(addressNames), HttpMethod.POST, jsonEntity(body), UniverseName[].class).getBody();
    return Arrays.asList(names);
  }

  @Override
  public UniverseStation getStation(int stationId) {
    return restOperations().getForObject(apiUrl(addressStation), UniverseStation.class, stationId);
  }

  @Override
  public UniverseStructure getStructure(int structureId) {
    return restOperations().getForObject(apiUrl(addressStructure), UniverseStructure.class, structureId);
  }

  @Override
  public UniverseType getType(long typeId) {
    return restOperations().getForObject(apiUrl(addressType), UniverseType.class, typeId);
  }

  @Override
  public String getSystemName(int systemId) {
    return restOperations().getForObject(apiUrl(addressSystem), String.class, systemId);
  }

  @Override
  public List<Long> getAllStructureIds() {
    Long[] structures = restOperations().getForObject(apiUrl(addressStructures), Long[].class);
    return Arrays.asList(structures);
  }
}
