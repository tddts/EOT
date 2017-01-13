package com.github.jdtk0x5d.eve.jet.rest.api.esi;

import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseName;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseStation;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseStructure;
import com.github.jdtk0x5d.eve.jet.model.api.esi.universe.UniverseType;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface UniverseAPI {

  List<UniverseName> getNames(long[] ids);

  UniverseStation getStation(int stationId);

  UniverseStructure getStructure(int structureId);

  UniverseType getType(long typeId);

  String getSystemName(int systemId);

  List<Long> getAllStructureIds();
}
