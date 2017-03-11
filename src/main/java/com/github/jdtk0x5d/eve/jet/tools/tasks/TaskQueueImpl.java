package com.github.jdtk0x5d.eve.jet.tools.tasks;

import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;
import com.github.jdtk0x5d.eve.jet.tools.Action;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class TaskQueueImpl<T> implements TaskQueue<T> {

  private final AtomicBoolean running = new AtomicBoolean(true);
  private final AtomicBoolean finished = new AtomicBoolean(false);

  private final LinkedList<Action> finallyActions = new LinkedList<>();
  private final LinkedList<Task> taskQueue;

  private T object = null;
  private Supplier<T> objectSupplier = () -> null;

  private volatile Task currentTask;


  TaskQueueImpl() {
    taskQueue = new LinkedList<>();
  }

  private TaskQueueImpl(Supplier<T> objectSupplier, LinkedList<Task> taskQueue) {
    this.objectSupplier = objectSupplier;
    this.taskQueue = taskQueue;
  }

  @Override
  public TaskQueue<T> run(Action action) {
    taskQueue.offer(new TaskImpl(action));
    return this;
  }

  @Override
  public TaskQueue<T> consume(Consumer<T> consumer) {
    taskQueue.offer(new TaskImpl(() -> consumer.accept(getObject())));
    return this;
  }

  @Override
  public <R> TaskQueue<R> process(Function<T, R> function) {
    return new TaskQueueImpl<>(() -> function.apply(getObject()), taskQueue);
  }

  @Override
  public <R> TaskQueue<R> supply(Supplier<R> supplier) {
    return new TaskQueueImpl<>(supplier, taskQueue);
  }

  @Override
  public T getResult() {
    return getObject();
  }

  @Override
  public TaskQueue<T> onStop(Action action) {
    taskQueue.getLast().onStop(action);
    return this;
  }

  @Override
  public TaskQueue<T> finallyAction(Action action) {
    finallyActions.add(action);
    return this;
  }

  @Override
  public TaskQueue<T> execute() {
    try {

      while (!taskQueue.isEmpty() && running.get()) {
        getNewTask();
        runNewTask();
      }

    } finally {
      finallyActions.forEach(Action::doAction);
      finished.set(true);
      notifyAwaiting();
    }

    return this;
  }

  private synchronized void getNewTask() {
    currentTask = taskQueue.poll();
  }

  private void runNewTask() {
    if (running.get()) {
      currentTask.run();
    }
  }

  @Override
  public synchronized void stop() {
    stopInternal();
  }

  @Override
  public synchronized void stopAndWait() {
    if (stopInternal()) await();
  }

  private boolean stopInternal() {
    running.set(false);
    if (currentTask != null) {
      currentTask.stop();
      return true;
    }
    return false;
  }

  @Override
  public boolean isFinished() {
    return finished.get();
  }

  private T getObject() {
    if (object == null) object = objectSupplier.get();
    return object;
  }

  private synchronized void notifyAwaiting() {
    notifyAll();
  }

  private void await() {
    try {
      wait();
    } catch (InterruptedException e) {
      throw new ApplicationException(e);
    }
  }
}
