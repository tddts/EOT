package com.github.jdtk0x5d.eve.jet.util;

import com.github.jdtk0x5d.eve.jet.context.Context;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class SpringUtil {

  public static Pair<Class<?>, Object> checkForDinamicProxy(Object bean) throws BeanInitializationException{
    try {
      Class<?> type = bean.getClass();
      if (AopUtils.isJdkDynamicProxy(bean)) {
        Advised advised = (Advised) bean;
        type = advised.getTargetClass();
        bean = advised.getTargetSource().getTarget();
      }
      return Pair.of(type, bean);
    } catch (Exception e) {
      throw new BeanInitializationException(e.getMessage(), e);
    }
  }

  public static void initBean(Object object, String name) {
    AutowireCapableBeanFactory beanFactory = Context.getBeanFactory();
    beanFactory.autowireBean(object);
    beanFactory.initializeBean(object, name);
  }

}
