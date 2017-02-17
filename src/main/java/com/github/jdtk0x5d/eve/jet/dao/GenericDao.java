package com.github.jdtk0x5d.eve.jet.dao;

import java.util.Collection;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface GenericDao {

  void save(Object object);

  int saveAll(Collection<?> collection);

  boolean delete(Object object);

  int deleteAll(Collection<?> collection);

  int deleteAll(Class<?> type);

}
