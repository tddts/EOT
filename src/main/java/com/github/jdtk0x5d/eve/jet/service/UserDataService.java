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

/**
 * {@code UserDataService} represents a service providing access to user data.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface UserDataService {

  /**
   * Get amount of ISK in all user's wallets.
   *
   * @return amount of ISK.
   */
  long getWalletsAmount();

  /**
   * Get amount of ISK in all user's wallets as String.
   *
   * @return amount of ISK as String.
   */
  String getWalletsAmountAsString();

  /**
   * Save given character ID
   *
   * @param idString character ID
   */
  void saveCharacterId(String idString);

  /**
   * Get current character ID.
   *
   * @return character ID.
   */
  String getCharacterId();
}
