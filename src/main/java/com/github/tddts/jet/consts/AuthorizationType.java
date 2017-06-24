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

package com.github.tddts.jet.consts;

/**
 * {@code AuthorizationType} represents a enumeration of authorization types available.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum AuthorizationType {

  /**
   * Implicit authorization.
   * Does not require client ID and key.
   * Lasts only 20 minutes.
   * Authorization token received by such method <b>cannot be refreshed</b>.
   */
  IMPLICIT,

  /**
   * Developer authorization.
   * Requires client ID and key.
   * Lasts only 20 minutes.
   * Authorization token received by such method <b>can be refreshed</b>.
   */
  DEV;

  public boolean isImplicit() {
    return this == IMPLICIT;
  }

  public boolean isDev() {
    return this == DEV;
  }
}
