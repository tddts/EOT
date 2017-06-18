/*
 * Copyright 2017 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jdtk0x5d.eve.jet.config.gson;

import com.github.jdtk0x5d.eve.jet.model.client.dotlan.DotlanJump;
import com.github.jdtk0x5d.eve.jet.model.client.dotlan.DotlanRoute;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link JsonDeserializer} for {@link DotlanRoute} objects.
 *
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
      jumps.add(parseJump(jump.getAsString()));
    }

    route.setSystems(systems);
    route.setAlternative(alternatives);
    route.setWaypoints(waypoints);
    route.setJumps(jumps);

    return route;
  }

  private DotlanJump parseJump(String value) {
    String[] jumps = value.split("-");
    return new DotlanJump(Integer.valueOf(jumps[0]), Integer.valueOf(jumps[1]));
  }
}
