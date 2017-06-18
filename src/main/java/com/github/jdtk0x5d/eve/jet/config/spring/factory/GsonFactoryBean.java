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

package com.github.jdtk0x5d.eve.jet.config.spring.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
 * {@code GsonFactoryBean} is a {@link FactoryBean} for {@link Gson}.
 * Can be provided with custom {@link JsonDeserializer} implementations.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class GsonFactoryBean implements FactoryBean<Gson>, InitializingBean {

  private Gson gson;

  private Map<Class<?>, Class<?>> deserializers;

  public void setDeserializers(Map<Class<?>, Class<?>> deserializers) {
    this.deserializers = deserializers;
  }

  @Override
  public Gson getObject() throws Exception {
    return gson;
  }

  @Override
  public Class<?> getObjectType() {
    return Gson.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    GsonBuilder gsonBuilder = new GsonBuilder();

    if (deserializers != null) {
      for (Class<?> typeClass : deserializers.keySet()) {
        JsonDeserializer<?> deserializer = (JsonDeserializer<?>) deserializers.get(typeClass).newInstance();
        gsonBuilder.registerTypeAdapter(typeClass, deserializer);
      }
    }

    gson = gsonBuilder.create();
  }
}
