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

package com.github.tddts.jet.tools.tasks;

import com.github.tddts.jet.tools.Action;

/**
 * {@code Task} represents a simple task that can be executed by {@link TaskQueue}.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface Task {

  /**
   * Run task.
   */
  void run();

  /**
   * Stop task execution.
   */
  void stop();

  /**
   * Set on-stop action.
   *
   * @param action on-stop action.
   */
  void onStop(Action action);
}
