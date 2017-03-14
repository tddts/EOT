package com.github.jdtk0x5d.eve.jet.fx.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Tigran_Dadaiants@epam.com
 */
public class FXControllerAnnotationBeanPostProcessor implements BeanPostProcessor {
/*TODO:
<context:component-scan base-package="org.projectx">
  <context:include-filter type="annotation" expression="com.github.jdtk0x5d.eve.jet.fx.annotations.FXController" />
</context:component-scan>
 */
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return null;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return null;
  }
}
