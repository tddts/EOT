package com.github.jdtk0x5d.eve.jet.rest.client.esi.impl;

import com.github.jdtk0x5d.eve.jet.model.api.esi.location.Location;
import com.github.jdtk0x5d.eve.jet.model.api.esi.location.Ship;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.rest.client.esi.LocationClient;
import com.github.jdtk0x5d.eve.jet.rest.provider.RestClientProvider;
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
