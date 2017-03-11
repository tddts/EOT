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
