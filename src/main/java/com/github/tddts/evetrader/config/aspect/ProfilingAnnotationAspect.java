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

import com.github.tddts.evetrader.config.spring.annotations.Profiling;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * {@code ProfilingAnnotationAspect} is an Aspect definition implementing profiling logic for methods marked with
 * {@link Profiling} annotation.
 * Simply calculates time between before and after method's execution and prints out log with results.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 * @see Profiling
 */
@Aspect
@Component
public class ProfilingAnnotationAspect {

  private static final Logger logger = LogManager.getLogger(Profiling.class);

  @Around("@annotation(com.github.tddts.evetrader.config.spring.annotations.Profiling)")
  public Object annotationPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
    long time = System.currentTimeMillis();
    Object value = joinPoint.proceed();
    long duration = (System.currentTimeMillis() - time) / 1000;
    long minutes = (duration % (60 * 60)) / 60;
    long seconds = duration % 60;
    logger.debug(joinPoint.getSignature().getDeclaringType().getSimpleName()
        + "."
        + joinPoint.getSignature().getName()
        + "() execution time is: "
        + minutes + " m. "
        + seconds + " s.");
    return value;
  }
}
