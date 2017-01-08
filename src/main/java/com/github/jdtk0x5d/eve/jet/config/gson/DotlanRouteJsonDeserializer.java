package com.github.jdtk0x5d.eve.jet.config.gson;

import com.github.jdtk0x5d.eve.jet.model.api.dotlan.DotlanJump;
import com.github.jdtk0x5d.eve.jet.model.api.dotlan.DotlanRoute;
import com.github.jdtk0x5d.eve.jet.util.Util;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DotlanRouteJsonDeserializer implements JsonDeserializer<DotlanRoute> {

  @Override
  public DotlanRoute deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    DotlanRoute route = new DotlanRoute();

    JsonArray jsonSystems = jsonObject.getAsJsonArray("sys");
    List<Integer> systems = new ArrayList<>(jsonSystems.size());
    for (JsonElement system : jsonSystems) {
      systems.add(system.getAsInt());
    }

    JsonArray jsonAlternatives = jsonObject.getAsJsonArray("alt");
    List<Integer> alternatives = new ArrayList<>(jsonAlternatives.size());
    for (JsonElement alternative : jsonAlternatives) {
      alternatives.add(alternative.getAsInt());
    }

    JsonArray jsonWaypoints = jsonObject.getAsJsonArray("wp");
    List<Integer> waypoints = new ArrayList<>(jsonWaypoints.size());
    for (JsonElement waypoint : jsonWaypoints) {
      waypoints.add(waypoint.getAsInt());
    }

    JsonArray jsonJumps = jsonObject.getAsJsonArray("j");
    List<DotlanJump> jumps = new ArrayList<>(jsonJumps.size());
    for (JsonElement jump : jsonJumps) {
      jumps.add(Util.parseJump(jump.getAsString()));
    }

    route.setSystems(systems);
    route.setAlternative(alternatives);
    route.setWaypoints(waypoints);
    route.setJumps(jumps);

    return route;
  }
}
