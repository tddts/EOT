package com.github.jdtk0x5d.eve.jet.config.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.*;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class LocalDateTimeJsonDeserializer implements JsonDeserializer<LocalDateTime> {

  @Override
  public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    Instant timeInstant = Instant.parse(jsonElement.getAsJsonPrimitive().getAsString());
    // Convert Zulu time to system time zone
    //TODO: Convert time somwhere else
    return ZonedDateTime.ofInstant(timeInstant, ZoneId.systemDefault()).toLocalDateTime();
  }
}
