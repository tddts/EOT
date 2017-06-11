package com.github.jdtk0x5d.eve.jet.config.spring.aspect;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.lang.reflect.Method;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Aspect
@Order(2)
@Component
public class RestClientAnnotationAspect {

  @Pointcut("@within(com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestClient)" +
      " && execution(public com.github.jdtk0x5d.eve.jet.rest.RestResponse *(..))")
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
