package com.github.jdtk0x5d.eve.jet.tools.tasks;

import com.github.jdtk0x5d.eve.jet.tools.Action;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class TaskImpl implements Task {

  private Action action = () -> {
  };
  private Action onStop = () -> {
  };

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
