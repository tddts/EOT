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

package com.github.tddts.jet.model.app;

import com.github.tddts.jet.consts.RouteOption;
import com.github.tddts.jet.consts.SecurityLevel;

import java.util.List;
import java.util.function.Consumer;

/**
 * {@code SearchParams} is a DTO with parameters for order search.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class SearchParams {

  private double isk;
  private double cargo;
  private double tax;

  private List<String> regions;
  private RouteOption routeOption;
  private SecurityLevel securityLevel;
  private Consumer<List<OrderSearchRow>> resultConsumer;

  public double getIsk() {
    return isk;
  }

  public SearchParams setIsk(double isk) {
    this.isk = isk;
    return this;
  }

  public double getCargo() {
    return cargo;
  }

  public SearchParams setCargo(double cargo) {
    this.cargo = cargo;
    return this;
  }

  public double getTax() {
    return tax;
  }

  public SearchParams setTax(double tax) {
    this.tax = tax;
    return this;
  }

  public RouteOption getRouteOption() {
    return routeOption;
  }

  public SearchParams setRouteOption(RouteOption routeOption) {
    this.routeOption = routeOption;
    return this;
  }

  public SecurityLevel getSecurityLevel() {
    return securityLevel;
  }

  public SearchParams setSecurityLevel(SecurityLevel securityLevel) {
    this.securityLevel = securityLevel;
    return this;
  }

  public List<String> getRegions() {
    return regions;
  }

  public SearchParams setRegions(List<String> regions) {
    this.regions = regions;
    return this;
  }

  public Consumer<List<OrderSearchRow>> getResultConsumer() {
    return resultConsumer;
  }

  public SearchParams setResultConsumer(Consumer<List<OrderSearchRow>> resultConsumer) {
    this.resultConsumer = resultConsumer;
    return this;
  }

  public void consumeResult(List<OrderSearchRow> result) {
    resultConsumer.accept(result);
  }

  public boolean isValid() {
    return regions != null && resultConsumer != null && routeOption != null;
  }
}