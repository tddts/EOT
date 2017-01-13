package com.github.jdtk0x5d.eve.jet.rest.api.dotlan.impl;

import com.github.jdtk0x5d.eve.jet.rest.api.dotlan.DotlanAPI;
import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.exception.DotlanResponseParsingException;
import com.github.jdtk0x5d.eve.jet.model.api.dotlan.DotlanRoute;
import com.github.jdtk0x5d.eve.jet.util.RequestUtil;
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
public class DotlanAPIImpl implements DotlanAPI {

  @Value("${url.dotlan.route}")
  private String addressDotlan;

  @Value("${dotlan.regex}")
  private String dotlanRegex;

  @Autowired
  private Gson gson;

  @Override
  public DotlanRoute getRoute(String[] waypoints, DotlanRouteOption dotlanRouteOption) {
    String routeOption = dotlanRouteOption == DotlanRouteOption.FASTEST ? "" : dotlanRouteOption.getValue() + ":";
    String url = addressDotlan + "?&path=" + routeOption + String.join(":", waypoints);

    String responseBody = RequestUtil.restOperations().getForObject(url, String.class);
    return gson.fromJson(getPathJson(responseBody), DotlanRoute.class);
  }

  private String getPathJson(String responseBody) {
    Matcher matcher = Pattern.compile(dotlanRegex).matcher(responseBody);
    if (matcher.find()) return matcher.group(1);
    throw new DotlanResponseParsingException("Could not parse dotlan response, body received:\n" + responseBody);
  }

}
