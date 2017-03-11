package com.github.jdtk0x5d.eve.jet.tools.tasks;

import com.github.jdtk0x5d.eve.jet.tools.Action;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface TaskQueue<T> {

  static <T> TaskQueue<T> create() {
    return new TaskQueueImpl<>();
  }

  static <T> TaskQueue<T> emptyQueue() {
    return new TaskQueueImpl<T>().execute();
  }

  /**
   * Add new task to queue.
   *
   * @param action task action
   * @return current TaskQueue
   */
  TaskQueue<T> run(Action action);

  /**
   * Add new task that consumes last result of current task queue.
   *
   * @param consumer task consumer
   * @return current TaskQueue
   */
  TaskQueue<T> consume(Consumer<T> consumer);

  /**
   * Add new task that consumes last result of current task queue
   * and provides another result.
   *
   * @param function task function
   * @param <R>      new result type
   * @return TaskQueue of a new type
   */
  <R> TaskQueue<R> process(Function<T, R> function);

  /**
   * Add new task that supplies a new result object
   *
   * @param supplier task supplier
   * @param <R>      result type
   * @return TaskQueue of a new type
   */
  <R> TaskQueue<R> supply(Supplier<R> supplier);

  /**
   * Set on-stop action for last added task.
   *
   * @param action on-stop action
   * @return current TaskQueue
   */
  TaskQueue<T> onStop(Action action);

  /**
   * Set actions that executed at the end no matter if execution has finished successfully or not.
   *
   * @param action action to execute
   */
  TaskQueue<T> finallyAction(Action action);


  /**
   * Start queue execution.
   */
  TaskQueue<T> execute();

  /**
   * Get resulted object.
   *
   * @return result
   */
  T getResult();

  void stop();

  void stopAndWait();

  boolean isFinished();
}
