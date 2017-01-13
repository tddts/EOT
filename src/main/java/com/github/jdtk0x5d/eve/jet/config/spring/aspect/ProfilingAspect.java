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
public class ProfilingAspect {

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
    return  value;
  }
}
