package com.github.jdtk0x5d.eve.jet.config.spring.aspect;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Retry;
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
public class RetryAnnotationAspect {

  private static final Logger logger = LogManager.getLogger(Retry.class);

  @Around("@within(com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestClient)" +
      " && execution(public com.github.jdtk0x5d.eve.jet.rest.RestResponse *(..))" +
      " && @annotation(com.github.jdtk0x5d.eve.jet.config.spring.annotations.Retry)")
  public Object annotationPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
    logger.debug("Retried:"+joinPoint.getSignature().getDeclaringType().getSimpleName());
    return joinPoint.proceed();
  }
}
