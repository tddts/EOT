package com.github.jdtk0x5d.eve.jet.api.esi.impl;

import com.github.jdtk0x5d.eve.jet.api.esi.UserInterfaceAPI;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.NullOnException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.apiUriBuilder;
import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.authorizedEntity;
import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.restOperations;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@NullOnException
public class UserInterfaceAPIImpl implements UserInterfaceAPI {

  @Value("${url.ui.waypoint}")
  private String addressWaypoint;

  @Override
  public void setWaypoint(int destinationId, boolean clearOtherWaypoints, boolean addToBeginning) {
    String url = apiUriBuilder(addressWaypoint)
        .queryParam("destination_id", destinationId)
        .queryParam("clear_other_waypoints", clearOtherWaypoints)
        .queryParam("add_to_beginning", addToBeginning)
        .build().toString();

    restOperations().exchange(url, HttpMethod.POST, authorizedEntity(), (Class<?>) null);
  }

}
