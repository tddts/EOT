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

package com.github.jdtk0x5d.eve.jet.tools.tasks;

import com.github.jdtk0x5d.eve.jet.tools.Action;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class TaskImpl implements Task {

  private static final Action DUMMY_ACTION = () -> {};

  private Action action = DUMMY_ACTION;
  private Action onStop = DUMMY_ACTION;

  TaskImpl(Action action) {
    this.action = action;
  }

  @Override
  public void run() {
    action.doAction();
  }

  @Override
  public void stop() {
    onStop.doAction();
  }

  @Override
  public void onStop(Action action) {
    this.onStop = action;
  }
}
