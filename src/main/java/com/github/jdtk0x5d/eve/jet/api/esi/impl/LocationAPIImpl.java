package com.github.jdtk0x5d.eve.jet.api.esi.impl;

import com.github.jdtk0x5d.eve.jet.api.esi.LocationAPI;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.NullOnException;
import com.github.jdtk0x5d.eve.jet.model.api.esi.location.Location;
import com.github.jdtk0x5d.eve.jet.model.api.esi.location.Ship;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.*;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@NullOnException
public class LocationAPIImpl implements LocationAPI {

  @Value("${url.character.location}")
  private String addressLocation;

  @Value("${url.character.ship}")
  private String addressShip;

  @Override
  public Location getLocation(long character_id) {
    String url = apiUrl(addressLocation);
    return restOperations().exchange(url, HttpMethod.GET, authorizedEntity(), Location.class, character_id).getBody();
  }

  @Override
  public Ship getShip(long character_id) {
    String url = apiUrl(addressShip);
    return restOperations().exchange(url, HttpMethod.GET, authorizedEntity(), Ship.class, character_id).getBody();
  }

}
