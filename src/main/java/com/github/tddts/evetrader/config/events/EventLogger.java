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

package com.github.tddts.evetrader.config.events;

import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * {@code EventLogger} represents a logger that tracks all application events.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class EventLogger {

  private static final Logger logger = LogManager.getLogger(EventLogger.class);

  @Subscribe
  public void logEvent(Object event) {
    logger.debug("Event registered: " + event.getClass().getSimpleName() + "." + event);
  }
}
