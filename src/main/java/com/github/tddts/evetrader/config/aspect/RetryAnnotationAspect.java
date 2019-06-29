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

package com.github.tddts.evetrader.config.aspect;

import com.github.tddts.evetrader.config.spring.annotations.Retry;
import com.github.tddts.evetrader.rest.RestResponse;
import com.github.tddts.evetrader.util.SpringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

  @Pointcut("@within(com.github.tddts.evetrader.config.spring.annotations.RestClient)" +
      " && execution(public com.github.tddts.evetrader.rest.RestResponse *(..))" +
      " && @annotation(com.github.tddts.evetrader.config.spring.annotations.Retry)")
  public void restClientMethodRetryPointcut() {
  }

  @Around("restClientMethodRetryPointcut()")
  public Object restClientRetryHandlingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
    Method method = SpringUtil.getMethod(joinPoint);

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
