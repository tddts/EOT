package com.github.jdtk0x5d.eve.jet.tools.pagination;

import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class PaginationExecutor {

  private static final int MAX_POOL_SIZE = 7;

  private final Set<Pagination> paginationSet = new HashSet<>();

  public void add(Pagination pagination) {
    paginationSet.add(pagination);
  }

  public void run() {
    ExecutorService executorService = Executors.newFixedThreadPool(getPoolSize());

    if (!executorService.isShutdown()) {

      try {
        executorService.invokeAll(createCallableList());
      } catch (InterruptedException e) {
        throw new ApplicationException(e);
      } finally {
        paginationSet.clear();
        executorService.shutdown();
      }
    }
  }

  private Collection<Callable<Pagination>> createCallableList() {
    return paginationSet.stream().map(this::createCallable).collect(Collectors.toList());
  }

  private Callable<Pagination> createCallable(Pagination pagination) {
    return () -> {
      pagination.perform();
      return pagination;
    };
  }

  private int getPoolSize() {
    int size = paginationSet.size();
    if (size == 0) return 1;
    if (size > MAX_POOL_SIZE) return MAX_POOL_SIZE;
    return size;
  }

  public void stop() {
    paginationSet.forEach(Pagination::stop);
  }
}
