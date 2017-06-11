package com.github.jdtk0x5d.eve.jet.rest.client.esi;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.model.client.esi.location.Location;
import com.github.jdtk0x5d.eve.jet.model.client.esi.location.Ship;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface LocationClient {

  RestResponse<Location> getLocation(long character_id);

  RestResponse<Ship> getShip(long character_id);
}
