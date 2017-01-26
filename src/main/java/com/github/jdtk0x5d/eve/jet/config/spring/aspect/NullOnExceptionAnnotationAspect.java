package com.github.jdtk0x5d.eve.jet.config.spring.aspect;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.NullOnException;
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
public class NullOnExceptionAnnotationAspect {

  private static final Logger logger = LogManager.getLogger(NullOnException.class);

  @Around("@within(com.github.jdtk0x5d.eve.jet.config.spring.annotations.NullOnException)" +
      " || @annotation(com.github.jdtk0x5d.eve.jet.config.spring.annotations.NullOnException)")
  public Object annotationPointcut(ProceedingJoinPoint joinPoint) {
    Object result = null;
    try {
      result = joinPoint.proceed();
    } catch (Throwable e) {
      logger.error("Ignoring exception, retuning null. Exception message:\n" + e.getMessage());
    }
    return result;
  }
}
