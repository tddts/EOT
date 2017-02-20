package com.github.jdtk0x5d.eve.jet.config.spring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadContent {

  String value() default "";

  boolean property() default true;
}
