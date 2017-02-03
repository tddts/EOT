package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.avaje.ebean.EbeanServer;
import com.github.jdtk0x5d.eve.jet.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public abstract class AbstractDao implements GenericDao {

  @Autowired
  private EbeanServer ebeanServer;

  protected EbeanServer ebeans() {
    return ebeanServer;
  }

  @Override
  public void save(Object object) {
    ebeanServer.save(object);
  }

  @Override
  public void saveAll(Collection<?> collection) {
    ebeanServer.saveAll(collection);
  }

  @Override
  public void delete(Object object) {
    ebeanServer.delete(object);
  }

  @Override
  public void deleteAll(Collection<?> collection) {
    ebeanServer.deleteAll(collection);
  }
}
