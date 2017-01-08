package com.github.jdtk0x5d.eve.jet.config.spring.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
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
