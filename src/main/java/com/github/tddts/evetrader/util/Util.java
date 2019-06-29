/*
 * Copyright 2018 Tigran Dadaiants
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

package com.github.tddts.evetrader.util;

/**
 * Class providing various utility methods.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Util {

  /**
   * Cut a value to given number of decimals.
   *
   * @param val      number
   * @param decimals decimals
   * @return rounded value
   */
  public static double roundDown(double val, int decimals) {
    double pow = Math.pow(10, decimals);
    return Math.floor(val * pow) / pow;
  }

}
