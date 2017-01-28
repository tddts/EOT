package com.github.jdtk0x5d.eve.jet.rest.api.esi;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import com.github.jdtk0x5d.eve.jet.model.api.esi.location.Location;
import com.github.jdtk0x5d.eve.jet.model.api.esi.location.Ship;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface LocationAPI {

  RestResponse<Location> getLocation(long character_id);

  RestResponse<Ship> getShip(long character_id);
}
