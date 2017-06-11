package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.github.jdtk0x5d.eve.jet.dao.GenericDao;
import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public abstract class EbeanAbstractDao<T> implements GenericDao<T> {

  private final Class<T> type;

  @Autowired
  private EbeanServer ebeanServer;

  public EbeanAbstractDao(Class<T> type) {
    this.type = type;
  }

  protected EbeanServer ebeans() {
    return ebeanServer;
  }

  @Override
  public void save(Object object) {
    ebeanServer.save(object);
  }

  @Override
  public int saveAll(Collection<T> collection) {
    return ebeanServer.saveAll(collection);
  }

  @Override
  public boolean delete(T object) {
    return ebeanServer.delete(object);
  }

  @Override
  public int deleteAll(Collection<T> collection) {
    return ebeanServer.deleteAll(collection);
  }

  @Override
  public int deleteAll() {
    return ebeanServer.find(type).delete();
  }
}
