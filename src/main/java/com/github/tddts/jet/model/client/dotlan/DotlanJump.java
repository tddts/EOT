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

package com.github.tddts.jet.model.client.dotlan;

/**
 * {@code DotlanJump} represents a jump between two systems in
 * <a href="http://evemaps.dotlan.net/">evemaps.dotlan.net</a>
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DotlanJump {

  private Integer from;
  private Integer to;

  public DotlanJump(Integer from, Integer to) {
    this.from = from;
    this.to = to;
  }

  public Integer getFrom() {
    return from;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  public Integer getTo() {
    return to;
  }

  public void setTo(Integer to) {
    this.to = to;
  }

  @Override
  public String toString() {
    return "DotlanJump{" + "from=[" + from + "], to=[" + to + "]}";
  }
}
