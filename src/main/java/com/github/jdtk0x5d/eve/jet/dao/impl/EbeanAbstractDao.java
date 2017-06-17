/*
 * Copyright 2017 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
