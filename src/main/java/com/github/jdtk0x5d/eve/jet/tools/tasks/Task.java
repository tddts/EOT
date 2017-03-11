package com.github.jdtk0x5d.eve.jet.tools.tasks;

import com.github.jdtk0x5d.eve.jet.tools.Action;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface Task {

  void run();

  void stop();

  void onStop(Action action);
}
