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

package com.github.tddts.jet.config.spring.beans;

import com.github.tddts.jet.view.fx.tools.ApplicationStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * {@code ApplicationStarterBean} is a bean that starts application after Spring context have been initialized.
 * Actual application starting logic is implemented inside {@link ApplicationStarter} implementation which
 * allows to separate core logic from framework-specific code.
 * You must create such implementation in order to launch application.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 * @see ApplicationStarter
 */
@Component
public class ApplicationStarterBean {

  @Autowired
  private ApplicationStarter applicationStarter;

  private volatile boolean started;

  @EventListener(ContextRefreshedEvent.class)
  public synchronized void start() {
    if (!started) {
      applicationStarter.startApplication();
      started = true;
    }
  }
}
