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

package com.github.tddts.jet.config.spring.postprocessor;

import com.github.tddts.jet.config.spring.annotations.LoadContent;
import com.github.tddts.jet.util.SpringUtil;
import com.github.tddts.jet.util.Util;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * {@code LoadContentAnnotationBeanPostProcessor} is a {@link BeanPostProcessor} that inserts content of files into
 * fields marked by {@link LoadContent} annotation presenting in processed beans.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class LoadContentAnnotationBeanPostProcessor implements BeanPostProcessor {

  @Resource
  private Properties applicationProperties;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    Pair<Class<?>, Object> typeObjectPair = SpringUtil.checkForDinamicProxy(bean);
    Class<?> type = typeObjectPair.getLeft();
    Object target = typeObjectPair.getRight();

    try {

      for (Field field : type.getDeclaredFields()) {
        if (field.isAnnotationPresent(LoadContent.class) && String.class.equals(field.getType())) {

          field.setAccessible(true);
          String fileName = getFileName(target, field);

          if (!StringUtils.isEmpty(fileName)) {
            field.set(target, Util.loadContent(fileName));
          }
        }
      }
    } catch (Exception e) {
      throw new BeanInitializationException(e.getMessage(), e);
    }

    return bean;
  }

  private String getFileName(Object target, Field field) throws IllegalAccessException {
    LoadContent annotation = field.getAnnotation(LoadContent.class);

    if (annotation.value().isEmpty()) {
      return (String) field.get(target);
    }

    if (annotation.property()) {
      return applicationProperties.getProperty(annotation.value());
    } else {
      return annotation.value();
    }
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }
}
