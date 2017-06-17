package com.github.jdtk0x5d.eve.jet.config.spring.beans;

import com.github.jdtk0x5d.eve.jet.view.fx.tools.ApplicationStartProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class ApplicationStarterBean {

  @Autowired
  private ApplicationStartProvider applicationStartProvider;

  private volatile boolean started;

  @EventListener(ContextRefreshedEvent.class)
  public synchronized void start() {
    if (!started) {
      applicationStartProvider.startApplication();
      started = true;
    }
  }
}
