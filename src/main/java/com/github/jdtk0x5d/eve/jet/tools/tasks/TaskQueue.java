package com.github.jdtk0x5d.eve.jet.tools.tasks;

import com.github.jdtk0x5d.eve.jet.tools.Action;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@code TaskQueue} allows to build an "assembly line" using such functional interfaces
 * as {@link Action}, {@link Consumer}, {@link Supplier} and {@link Function}, then perform it and get some result.
 * For example:
 * <pre>
 * ResultObj result = new TaskQueueImpl<>()
 * .perform(() -> doSomething())
 * .perform(() -> doSomethingElse())
 * .onStop(() -> stopSomethingElse())
 * .perform(() -> doAnotherThing())
 * .supply(() -> getTestObject())
 * .process(testObject -> getResultFromObject(testObject))
 * .execute()
 * .getResult();
 * </pre>
 * It is not supposed to be executed in multiple threads, but it is possible to stop execution by calling
 * {@link #stop()} or {@link #stopAndWait()} from another thread.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface TaskQueue<T> {

  /**
   * Add new task to queue.
   *
   * @param action task action
   * @return current TaskQueue
   */
  TaskQueue<T> perform(Action action);

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

  /**
   * Stop queue execution.
   */
  void stop();

  /**
   * Stop queue execution and wait for it to finish.
   */
  void stopAndWait();

  /**
   * @return true if execution is finished, false otherwise.
   */
  boolean isFinished();
}
