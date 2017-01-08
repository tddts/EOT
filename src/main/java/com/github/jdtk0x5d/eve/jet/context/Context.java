package com.github.jdtk0x5d.eve.jet.context;

import com.github.jdtk0x5d.eve.jet.config.spring.beans.PropertyHolder;
import com.github.jdtk0x5d.eve.jet.config.spring.beans.ResourceBundleContainer;
import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ResourceBundle;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Context {

  private static final ClassPathXmlApplicationContext SPRING_CONTEXT = new ClassPathXmlApplicationContext("spring-config.xml");

  private static final AutowireCapableBeanFactory BEAN_FACTORY = SPRING_CONTEXT.getAutowireCapableBeanFactory();

  private static final ResourceBundleContainer RESOURCE_BUNDLE_CONTAINER = SPRING_CONTEXT.getBean(ResourceBundleContainer.class);

  public static ClassPathXmlApplicationContext getAppContext(){
    return SPRING_CONTEXT;
  }

  public static AutowireCapableBeanFactory getBeanFactory(){
    return BEAN_FACTORY;
  }

  public static UserBean getUserBean() {
    return SPRING_CONTEXT.getBean(UserBean.class);
  }

  public static ResourceBundle getResourceBundle() {
    return RESOURCE_BUNDLE_CONTAINER.getResourceBundle();
  }

  public static <T> T getBean(Class<T> type){
    return SPRING_CONTEXT.getBean(type);
  }

  public static PropertyHolder getPropertyHolder(){
    return SPRING_CONTEXT.getBean(PropertyHolder.class);
  }

}
