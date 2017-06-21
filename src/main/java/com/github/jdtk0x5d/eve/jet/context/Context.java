/*
 * Copyright 2017 Tigran Dadaiants
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

package com.github.jdtk0x5d.eve.jet.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@code Context} represents an application context.
 * Contains Spring's {@link ClassPathXmlApplicationContext}.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Context {

  private static Context CONTEXT;

  private ClassPathXmlApplicationContext springContext;

  private Context(String configLocation) {
    springContext = new ClassPathXmlApplicationContext(configLocation);
  }

  /**
   * Initializes application context by loading Spring context from given file.
   *
   * @param configLocation spring configuration file location
   */
  public static synchronized void initialize(String configLocation) {
    if (CONTEXT == null) {
      CONTEXT = new Context(configLocation);
    }
  }

  /**
   * Get application context singleton
   *
   * @return application context
   */
  public static Context getContext() {
    return CONTEXT;
  }

  public ClassPathXmlApplicationContext getSpringContext() {
    return springContext;
  }
}
