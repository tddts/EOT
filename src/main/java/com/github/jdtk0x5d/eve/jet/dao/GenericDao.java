package com.github.jdtk0x5d.eve.jet.dao;

import java.util.Collection;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface GenericDao<T> {

  void save(T object);

  int saveAll(Collection<T> collection);

  boolean delete(T object);

  int deleteAll(Collection<T> collection);

  int deleteAll();

}
