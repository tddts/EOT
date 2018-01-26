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

package com.github.tddts.jet.oauth;

import com.github.tddts.jet.model.client.esi.sso.AccessToken;

import java.util.Map;

/**
 * {@code QueryParser} represents a parser for access tokens and authorization codes.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface QueryParser {

  Map<String, String> parseQuery(String query);
}
