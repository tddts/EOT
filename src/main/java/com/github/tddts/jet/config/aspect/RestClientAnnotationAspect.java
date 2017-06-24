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

package com.github.tddts.jet.config.aspect;

import com.github.tddts.jet.config.spring.annotations.RestClient;
import com.github.tddts.jet.rest.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * {@code RestClientAnnotationAspect} is an Aspect definition implementing error-handling logic for REST client classes.
 * Wraps methods in classes marked by {@link RestClient} annotation and return type of {@link RestResponse} in a
 * simple "try-catch" which returns {@link RestResponse} containing error in case of exception.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Aspect
@Order(2)
@Component
public class RestClientAnnotationAspect {

  @Pointcut("@within(com.github.tddts.jet.config.spring.annotations.RestClient)" +
      " && execution(public com.github.tddts.jet.rest.RestResponse *(..))")
  public void restClientMethodPointcut() {
  }

  @Around("restClientMethodPointcut()")
  public Object restClientErrorHandlingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      return joinPoint.proceed();
    } catch (HttpStatusCodeException e) {
      return new RestResponse(e);
    }
  }

}
