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

package com.github.tddts.jet.config.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.*;

/**
 * {@link JsonDeserializer} for {@link LocalDateTime} objects.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class LocalDateTimeJsonDeserializer implements JsonDeserializer<LocalDateTime> {

  @Override
  public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    Instant timeInstant = Instant.parse(jsonElement.getAsJsonPrimitive().getAsString());
    // Convert Zulu time to system time zone
    return ZonedDateTime.ofInstant(timeInstant, ZoneId.systemDefault()).toLocalDateTime();
  }
}
