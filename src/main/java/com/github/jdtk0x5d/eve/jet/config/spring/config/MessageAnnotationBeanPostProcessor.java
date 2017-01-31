package com.github.jdtk0x5d.eve.jet.config.spring.config;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class MessageAnnotationBeanPostProcessor implements BeanPostProcessor {

  private final Logger logger = LogManager.getLogger(MessageAnnotationBeanPostProcessor.class);

  @Autowired
  private MessageSource messageSource;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    Class<?> type = bean.getClass();
    Method[] methods = type.getDeclaredMethods();
    Field[] fields = type.getDeclaredFields();

    for (Method method : methods) {
      if (method.isAnnotationPresent(Message.class) && checkMethod(method, type)) {
        processMethod(bean, method);
      }
    }

    for (Field field : fields) {
      if (field.isAnnotationPresent(Message.class) && checkField(field, type)) {
        processField(bean, field);
      }
    }

    return bean;
  }

  private boolean checkMethod(Method method, Class<?> type) {
    if (method.getParameterCount() == 1 && method.getParameterTypes()[0].equals(String.class)) {
      return true;
    }
    logger.warn("Method [" + type + "." + method.getName() + "] is not populated with message. Method should have a single String parameter.");
    return false;
  }

  private boolean checkField(Field field, Class<?> type) {
    if (field.getType().equals(String.class)) {
      return true;
    }
    logger.warn("Field [" + type + "." + field.getName() + "] is not populated with message. Should be a String field.");
    return false;
  }

  private String preprocess(AccessibleObject accessibleObject) throws NoSuchMessageException {
    Message messageAnnotation = accessibleObject.getAnnotation(Message.class);
    String messageKey = messageAnnotation.value();
    String message = messageSource.getMessage(messageKey, new Object[0], Locale.getDefault());
    accessibleObject.setAccessible(true);
    return message;
  }

  private void processMethod(Object bean, Method method) {
    try {
      String message = preprocess(method);
      method.invoke(bean, message);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new BeanCreationException("Injection of message failed for method [" + bean.getClass() + "." + method.getName() + "]", e);
    } catch (NoSuchMessageException e) {
      throw new BeanCreationException("Failed to find message for method [" + bean.getClass() + "." + method.getName() + "]", e);
    }
  }

  private void processField(Object bean, Field field) {
    try {
      String message = preprocess(field);
      field.set(bean, message);
    } catch (IllegalAccessException e) {
      throw new BeanCreationException("Injection of message failed for field [" + bean.getClass() + "." + field.getName() + "]", e);
    } catch (NoSuchMessageException e) {
      throw new BeanCreationException("Failed to find message for field [" + bean.getClass() + "." + field.getName() + "]", e);
    }
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }
}
