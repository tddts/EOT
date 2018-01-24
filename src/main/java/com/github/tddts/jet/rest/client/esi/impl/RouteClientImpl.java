package com.github.tddts.jet.rest.client.esi.impl;

import com.github.tddts.jet.config.spring.annotations.RestClient;
import com.github.tddts.jet.consts.RouteOption;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.esi.RouteClient;
import com.github.tddts.jet.rest.provider.RestClientTemplate;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class RouteClientImpl implements RouteClient {

  @Value("${path.routes}")
  private String addressRoutes;

  @Autowired
  private RestClientTemplate client;

  @Override
  public RestResponse<List<Integer>> getRoute(int origin, int destination, int[] avoid, int[] connections, RouteOption routeOption) {
    UriComponentsBuilder builder = client.apiUriBuilder(addressRoutes);

    if (connections.length > 0) builder = builder.queryParam("avoid", StringUtils.join(avoid, ','));
    if (avoid.length > 0) builder = builder.queryParam("connections", StringUtils.join(connections, ','));

    String url = builder.queryParam("flag", routeOption.getValue()).build().toString();

    Map<String, ?> uriVariables = ImmutableMap.of(
        "origin", origin,
        "destination", destination);

    return RestResponse.fromArrayResponse(client.restOperations().getForEntity(url, Integer[].class, uriVariables));
  }
}
