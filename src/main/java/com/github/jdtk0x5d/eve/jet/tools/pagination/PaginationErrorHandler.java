package com.github.jdtk0x5d.eve.jet.tools.pagination;

/**
 * @author Tigran_Dadaiants@epam.com
 */
public interface PaginationErrorHandler {

  void stop();

  void skipPage();

  void retryPage(int maxTries, long timeout, boolean skip);
}
