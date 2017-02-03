package com.github.jdtk0x5d.eve.jet.dao;

import java.util.Collection;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface GenericDao {

  void save(Object object);

  void saveAll(Collection<?> collection);

  void delete(Object object);

  void deleteAll(Collection<?> collection);

}
