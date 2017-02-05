package com.github.jdtk0x5d.eve.jet.api;

import java.util.Collection;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface Pagination {

  void perform();

  void stop();

  void skipPage();

  void retry(int maxTries, long timeout, boolean skip);

}
