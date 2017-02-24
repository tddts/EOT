package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.service.TaskService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@Scope("singleton")
public class TaskServiceImpl implements TaskService {

  private final ExecutorService executorService = Executors.newCachedThreadPool();

  @Override
  public void execute(Runnable runnable) {
    executorService.execute(runnable);
  }
}
