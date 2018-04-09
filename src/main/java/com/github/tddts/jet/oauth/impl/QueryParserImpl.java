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

package com.github.tddts.jet.oauth.impl;

import com.github.tddts.jet.oauth.QueryParser;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class QueryParserImpl implements QueryParser {

  @Override
  public Map<String, String> parseQuery(String query) {
    return toMap(URLEncodedUtils.parse((query), StandardCharsets.UTF_8));
  }

  @Override
  public Map<String, String> toMap(List<NameValuePair> pairs) {
    return pairs.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
  }
}
