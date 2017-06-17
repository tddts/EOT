package com.github.jdtk0x5d.eve.jet.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Context {

  private static ClassPathXmlApplicationContext SPRING_CONTEXT;

  public static synchronized void initialize(String configLocation) {
    if (SPRING_CONTEXT == null) {
      new ClassPathXmlApplicationContext(configLocation);
    }
  }

  public static ClassPathXmlApplicationContext getSpringContext() {
    return SPRING_CONTEXT;
  }
}
