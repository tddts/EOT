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

package com.github.jdtk0x5d.eve.jet.service;


import com.github.jdtk0x5d.eve.jet.consts.AuthorizationType;
import com.github.jdtk0x5d.eve.jet.consts.RestDataSource;
import org.apache.commons.lang3.tuple.Pair;

import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * {@code LoginService} represents a service processing user login and it's parameters.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface LoginService {

  /**
   * Generate full login URI using given credentials an supplies them to given consumer.
   *
   * @param credentialsSupplier credentials supplier
   * @param loginUriConsumer    login URI consumer
   */
  void processLogin(Supplier<Optional<Pair<String, String>>> credentialsSupplier, Consumer<URI> loginUriConsumer);

  /**
   * Sets new login type.
   *
   * @param value authorization type.
   */
  void processLoginTypeChange(AuthorizationType value);

  /**
   * Sets new data source.
   *
   * @param restDataSource REST data source
   */
  void processDataSourceChange(RestDataSource restDataSource);
}
