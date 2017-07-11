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

package com.github.tddts.jet;

import com.github.tddts.jet.config.spring.beans.ApplicationStarterBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

/**
 * Main application class.
 * Launches whole application by initializing Spring context.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 * @see ApplicationStarterBean
 */
public class App {

  public static void main(String[] args) throws Exception {
    CommandLinePropertySource commandLinePropertySource = new SimpleCommandLinePropertySource(args);
    ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext();
    springContext.getEnvironment().getPropertySources().addFirst(commandLinePropertySource);
    springContext.setConfigLocation("spring-config.xml");
    springContext.refresh();
  }
}
