package com.github.jdtk0x5d.eve.jet.rest.api.esi.impl;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import com.github.jdtk0x5d.eve.jet.model.api.esi.location.Location;
import com.github.jdtk0x5d.eve.jet.model.api.esi.location.Ship;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.LocationAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.*;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class LocationAPIImpl implements LocationAPI {

  @Value("${url.character.location}")
  private String addressLocation;

  @Value("${url.character.ship}")
  private String addressShip;

  @Override
  public RestResponse<Location> getLocation(long character_id) {
    return new RestResponse<>(
        restOperations().exchange(
            apiUrl(addressLocation), HttpMethod.GET, authorizedEntity(), Location.class, character_id));
  }

  @Override
  public RestResponse<Ship> getShip(long character_id) {
    return new RestResponse<>(
        restOperations().exchange(
            apiUrl(addressShip), HttpMethod.GET, authorizedEntity(), Ship.class, character_id));
  }

}
