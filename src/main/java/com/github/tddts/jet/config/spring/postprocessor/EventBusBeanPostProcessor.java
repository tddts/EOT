/*
 * Copyright 2018 Tigran Dadaiants
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

package com.github.tddts.jet.config.spring.postprocessor;

import com.github.tddts.jet.util.SpringUtil;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

/**
 * {@code EventBusBeanPostProcessor} is {@link BeanPostProcessor} that registers beans to {@link EventBus} if they
 * have methods marked with {@link Subscribe} annotation.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class EventBusBeanPostProcessor implements BeanPostProcessor {

  private final Logger logger = LogManager.getLogger(EventBusBeanPostProcessor.class);

  @Autowired
  private EventBus eventBus;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

    Pair<Class<?>, Object> typeObjectPair = SpringUtil.checkForDynamicProxy(bean);
    Class<?> type = typeObjectPair.getLeft();
    Object target = typeObjectPair.getRight();

    for (Method method : type.getDeclaredMethods()) {

      if (method.isAnnotationPresent(Subscribe.class)) {
        eventBus.register(target);
        logger.debug("Bean registered to EventBus: [" + beanName + "]");
        return bean;
      }
    }
    return bean;
  }

}