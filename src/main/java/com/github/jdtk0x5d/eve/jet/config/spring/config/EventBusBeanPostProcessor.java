package com.github.jdtk0x5d.eve.jet.config.spring.config;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class EventBusBeanPostProcessor implements BeanPostProcessor {

  private final Logger logger = LogManager.getLogger(EventBusBeanPostProcessor.class);

  @Autowired
  private EventBus eventBus;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    for (Method method : bean.getClass().getDeclaredMethods()) {

        if (method.isAnnotationPresent(Subscribe.class)) {
          eventBus.register(bean);
          logger.debug("Bean registered to EventBus: [" + beanName + "]");
          return bean;
        }
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

}