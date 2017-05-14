package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.github.jdtk0x5d.eve.jet.dao.GenericDao;
import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public abstract class EbeanAbstractDao implements GenericDao {

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
  public int saveAll(Collection<?> collection) {
    return ebeanServer.saveAll(collection);
  }

  @Override
  public boolean delete(Object object) {
    return ebeanServer.delete(object);
  }

  @Override
  public int deleteAll(Collection<?> collection) {
    return ebeanServer.deleteAll(collection);
  }

  @Override
  public int deleteAll(Class<?> type) {
    return ebeanServer.find(type).delete();
  }
}
