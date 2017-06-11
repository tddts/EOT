package com.github.jdtk0x5d.eve.jet.rest.client.esi.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestClient;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.rest.client.esi.UserInterfaceClient;
import com.github.jdtk0x5d.eve.jet.rest.provider.RestClientProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class UserInterfaceClientImpl implements UserInterfaceClient {

  @Value("${path.ui.waypoint}")
  private String addressWaypoint;

  @Value("${path.ui.market.details}")
  private String addressMarketDetails;

  @Autowired
  private RestClientProvider client;

  @Override
  public RestResponse<String> setWaypoint(int destinationId, boolean clearOtherWaypoints, boolean addToBeginning) {
    String url = client.apiUriBuilder(addressWaypoint)
        .queryParam("destination_id", destinationId)
        .queryParam("clear_other_waypoints", clearOtherWaypoints)
        .queryParam("add_to_beginning", addToBeginning)
        .build().toString();

    return new RestResponse<>(
        client.restOperations().exchange(url, HttpMethod.POST, client.authorizedEntity(), String.class));
  }

  @Override
  public RestResponse<String> openMarketDetails(int typeId) {
    String url = client.apiUriBuilder(addressMarketDetails)
        .queryParam("type_id", typeId).build().toString();

    return new RestResponse<>(
        client.restOperations().exchange(url, HttpMethod.POST, client.authorizedEntity(), String.class));
  }

}
