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

package com.github.tddts.jet.dao;

import java.util.Collection;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface GenericDao<T> {

  /**
   * Find object by it's ID
   *
   * @param id object id
   */
  T find(int id);

  /**
   * Save object to database
   *
   * @param object object
   */
  void save(T object);

  /**
   * Save collection of objects to database
   *
   * @param collection collection of objects
   * @return number of saved objects
   */
  int saveAll(Collection<T> collection);

  /**
   * Delete object from database
   *
   * @param object object
   * @return <>true</> if object was successfuully deleted, <b>false</b> otherwise
   */
  boolean delete(T object);

  /**
   * Delete a collection of objects from database
   *
   * @param collection collection of objects
   * @return number of deleted objects
   */
  int deleteAll(Collection<T> collection);

  /**
   * Delete all objects of generic type {@link T} from database
   *
   * @return number of deleted objects
   */
  int deleteAll();

}
