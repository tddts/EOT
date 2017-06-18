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

package com.github.jdtk0x5d.eve.jet.config.spring.aspect;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Retry;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * {@code RetryAnnotationAspect} as an Aspect definition implementing retry logic for REST client methods.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Aspect
@Order(1)
@Component
public class RetryAnnotationAspect {

  private static final Logger logger = LogManager.getLogger(Retry.class);

  @Pointcut("@within(com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestClient)" +
      " && execution(public com.github.jdtk0x5d.eve.jet.rest.RestResponse *(..))" +
      " && @annotation(com.github.jdtk0x5d.eve.jet.config.spring.annotations.Retry)")
  public void restClientMethodRetryPointcut() {
  }

  @Around("restClientMethodRetryPointcut()")
  public Object restClientRetryHandlingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    if (method.getDeclaringClass().isInterface()) {
      method = joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
    }

    Retry retry = method.getAnnotation(Retry.class);

    int count = retry.retries();
    int timeout = retry.timeout();

    RestResponse response = (RestResponse) joinPoint.proceed();

    while(count > 0 && response.hasError()){
      Thread.sleep(timeout);
      response = (RestResponse) joinPoint.proceed();
      count--;
    }

    return response;
  }
}
