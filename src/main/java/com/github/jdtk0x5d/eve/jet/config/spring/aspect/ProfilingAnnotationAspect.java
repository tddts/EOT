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

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Profiling;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Aspect
@Component
public class ProfilingAnnotationAspect {

  private static final Logger logger = LogManager.getLogger(Profiling.class);

  @Around("@annotation(com.github.jdtk0x5d.eve.jet.config.spring.annotations.Profiling)")
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
