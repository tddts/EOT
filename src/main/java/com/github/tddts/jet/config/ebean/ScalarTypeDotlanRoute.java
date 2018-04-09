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

package com.github.tddts.jet.config.ebean;


import com.github.tddts.jet.model.client.dotlan.DotlanRoute;
import com.google.gson.Gson;
import io.ebeaninternal.server.type.ScalarType;
import io.ebeaninternal.server.type.ScalarTypeBaseVarchar;

/**
 * {@link ScalarType} for {@link DotlanRoute}.
 * Implements saving {@link DotlanRoute} object as a JSON string.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ScalarTypeDotlanRoute extends ScalarTypeBaseVarchar<DotlanRoute> {

  private Gson gson = new Gson();

  public ScalarTypeDotlanRoute() {
    super(DotlanRoute.class);
  }

  @Override
  public String formatValue(DotlanRoute value) {
    return gson.toJson(value);
  }

  @Override
  public DotlanRoute parse(String value) {
    return gson.fromJson(value, DotlanRoute.class);
  }

  @Override
  public DotlanRoute convertFromDbString(String dbValue) {
    return gson.fromJson(dbValue, DotlanRoute.class);
  }

  @Override
  public String convertToDbString(DotlanRoute beanValue) {
    return gson.toJson(beanValue);
  }
}
