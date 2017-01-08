package com.github.jdtk0x5d.eve.jet.config.spring.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ResourceBundle;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component("resourceBundleContainer")
@Scope("singleton")
public class ResourceBundleContainer {

  @Value("${resource.bundle.path}")
  private String bundlePath;

  private ResourceBundle resourceBundle;

  @PostConstruct
  public void init(){
    resourceBundle = ResourceBundle.getBundle(bundlePath);
  }

  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }
}
