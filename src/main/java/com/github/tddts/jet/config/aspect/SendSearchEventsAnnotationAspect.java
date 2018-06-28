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

package com.github.tddts.jet.config.aspect;

import com.github.tddts.jet.config.spring.annotations.SendSearchEvents;
import com.github.tddts.jet.context.events.SearchStatusEvent;
import com.github.tddts.jet.util.SpringUtil;
import com.google.common.eventbus.EventBus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Aspect
@Component
public class SendSearchEventsAnnotationAspect {

  @Autowired
  private EventBus eventBus;

  @Around("@annotation(com.github.tddts.jet.config.spring.annotations.SendSearchEvents)")
  public Object annotationPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
    SendSearchEvents annotation = SpringUtil.getMethod(joinPoint).getAnnotation(SendSearchEvents.class);

    checkAndSend(annotation.before());

    Object value = joinPoint.proceed();

    checkCollectionAndSend(value, annotation.onEmpty());
    checkAndSend(annotation.after());

    return value;
  }

  private void checkAndSend(SearchStatusEvent event) {
    if (event != SearchStatusEvent.NONE) eventBus.post(event);
  }

  private void checkCollectionAndSend(Object object, SearchStatusEvent event) {
    if (event == SearchStatusEvent.NONE) return;

    if (object instanceof Collection) {
      Collection<?> collection = (Collection<?>) object;
      if (collection.isEmpty()) eventBus.post(event);
    }
  }
}
