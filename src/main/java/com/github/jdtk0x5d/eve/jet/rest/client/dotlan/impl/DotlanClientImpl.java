package com.github.jdtk0x5d.eve.jet.rest.client.dotlan.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestClient;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Retry;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.exception.DotlanResponseParsingException;
import com.github.jdtk0x5d.eve.jet.model.client.dotlan.DotlanRoute;
import com.github.jdtk0x5d.eve.jet.rest.client.dotlan.DotlanClient;
import com.github.jdtk0x5d.eve.jet.rest.provider.RestClientProvider;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class DotlanClientImpl implements DotlanClient {

  @Value("${url.dotlan.route}")
  private String addressDotlan;

  @Value("${dotlan.regex}")
  private String dotlanRegex;

  @Autowired
  private Gson gson;

  @Autowired
  private RestClientProvider client;

  @Retry
  @Override
  public RestResponse<DotlanRoute> getRoute(DotlanRouteOption dotlanRouteOption, String... waypoints) {
    String routeOption = dotlanRouteOption == DotlanRouteOption.FASTEST ? "" : dotlanRouteOption.getValue() + ":";
    String url = addressDotlan + "?&path=" + routeOption + String.join(":", waypoints);

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(new MediaType("image", "svg+xml")));
    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

    ResponseEntity<String> response = client.restOperations().exchange(url, HttpMethod.GET, entity, String.class);

    DotlanRoute route = gson.fromJson(getPathJson(response.getBody()), DotlanRoute.class);
    return new RestResponse<>(route, response.getStatusCode());
  }

  private String getPathJson(String responseBody) {
    Matcher matcher = Pattern.compile(dotlanRegex).matcher(responseBody);
    if (matcher.find()) return matcher.group(1);
    throw new DotlanResponseParsingException("Could not parse dotlan response, body received:\n" + responseBody);
  }

}
