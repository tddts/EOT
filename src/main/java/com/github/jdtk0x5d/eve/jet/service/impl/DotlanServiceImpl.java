package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.exception.DotlanResponseParsingException;
import com.github.jdtk0x5d.eve.jet.model.client.dotlan.DotlanRoute;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.rest.client.dotlan.DotlanClient;
import com.github.jdtk0x5d.eve.jet.service.DotlanService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class DotlanServiceImpl implements DotlanService {

  @Value("${dotlan.regex}")
  private String dotlanRegex;

  @Autowired
  private Gson gson;

  @Autowired
  private DotlanClient dotlanClient;

  public DotlanRoute getRoute(DotlanRouteOption dotlanRouteOption, String... waypoints) {
    RestResponse<String> dotlanPageResponse = dotlanClient.getRoutePage(dotlanRouteOption, waypoints);
    return gson.fromJson(getPathJson(dotlanPageResponse.getObject()), DotlanRoute.class);
  }

  private String getPathJson(String responseBody) {
    Matcher matcher = Pattern.compile(dotlanRegex).matcher(responseBody);
    if (matcher.find()) return matcher.group(1);
    throw new DotlanResponseParsingException("Could not parse dotlan response, body received:\n" + responseBody);
  }
}
