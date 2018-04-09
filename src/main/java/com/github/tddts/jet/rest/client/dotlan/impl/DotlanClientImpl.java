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

package com.github.tddts.jet.rest.client.dotlan.impl;

import com.github.tddts.jet.config.spring.annotations.RestClient;
import com.github.tddts.jet.config.spring.annotations.Retry;
import com.github.tddts.jet.model.client.dotlan.DotlanRouteOption;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.dotlan.DotlanClient;
import com.github.tddts.jet.rest.provider.RestClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class DotlanClientImpl implements DotlanClient {

  @Value("${url.dotlan.route}")
  private String addressDotlan;

  @Autowired
  private RestClientTemplate client;

  @Retry
  @Override
  public RestResponse<String> getRoutePage(DotlanRouteOption dotlanRouteOption, String... waypoints) {
    String routeOption = dotlanRouteOption == DotlanRouteOption.FASTEST ? "" : dotlanRouteOption.getValue() + ":";
    String url = addressDotlan + "?&path=" + routeOption + String.join(":", waypoints);

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(new MediaType("image", "svg+xml")));
    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    return new RestResponse<>(client.restOperations().exchange(url, HttpMethod.GET, entity, String.class));
  }

}
