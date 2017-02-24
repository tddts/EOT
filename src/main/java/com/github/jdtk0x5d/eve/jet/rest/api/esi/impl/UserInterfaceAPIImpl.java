package com.github.jdtk0x5d.eve.jet.rest.api.esi.impl;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestApi;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.UserInterfaceAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.*;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestApi
public class UserInterfaceAPIImpl implements UserInterfaceAPI {

  @Value("${url.ui.waypoint}")
  private String addressWaypoint;

  @Value("${url.ui.market.details}")
  private String addressMarketDetails;

  @Override
  public RestResponse<String> setWaypoint(int destinationId, boolean clearOtherWaypoints, boolean addToBeginning) {
    String url = apiUriBuilder(addressWaypoint)
        .queryParam("destination_id", destinationId)
        .queryParam("clear_other_waypoints", clearOtherWaypoints)
        .queryParam("add_to_beginning", addToBeginning)
        .build().toString();

    return new RestResponse<>(restOperations().exchange(url, HttpMethod.POST, authorizedEntity(), String.class));
  }

  @Override
  public RestResponse<String> openMarketDetails(int typeId) {
    String url = apiUriBuilder(addressMarketDetails)
        .queryParam("type_id",typeId).build().toString();

    return new RestResponse<>(restOperations().exchange(url, HttpMethod.POST, authorizedEntity(), String.class));
  }

}
