package com.github.jdtk0x5d.eve.jet.config.spring.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@Scope("singleton")
public class PropertyHolder {

  @Resource(name = "applicationProperties")
  private Properties properties;

  public PropertyHolder() {
  }


  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

}
