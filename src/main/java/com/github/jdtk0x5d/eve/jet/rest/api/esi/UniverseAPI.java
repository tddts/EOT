package com.github.jdtk0x5d.eve.jet.rest.api.esi;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseName;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseStation;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseStructure;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseType;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface UniverseAPI {

  RestResponse<List<UniverseName>> getNames(long[] ids);

  RestResponse<UniverseStation> getStation(int stationId);

  RestResponse<UniverseStructure> getStructure(int structureId);

  RestResponse<UniverseType> getType(long typeId);

  RestResponse<String> getSystemName(int systemId);

  RestResponse<List<Long>> getAllStructureIds();
}
