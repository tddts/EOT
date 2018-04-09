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

package com.github.tddts.jet.service.impl;

import com.github.tddts.jet.config.spring.beans.UserBean;
import com.github.tddts.jet.model.client.esi.sso.CharacterInfo;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.esi.WalletClient;
import com.github.tddts.jet.service.UserDataService;
import com.google.common.eventbus.EventBus;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class UserDataServiceImpl implements UserDataService {

  @Value("${file.user.properties}")
  private String userDataFileName;

  @Autowired
  private UserBean userBean;
  @Autowired
  private WalletClient walletClient;
  @Autowired
  private EventBus eventBus;

  @PostConstruct
  private void init() {
    userDataFileName = FileUtils.getUserDirectoryPath() + File.separator + userDataFileName;
  }

  @Override
  public double getWalletsAmount() {
    CharacterInfo characterInfo = userBean.getCharacterInfo();

    if (characterInfo == null) return 0;

    RestResponse<Double> walletAmount = walletClient.getWalletAmount(characterInfo.getCharacterID());
    if (walletAmount.isSuccessful()) {
      return walletAmount.getObject();
    }

    return 0;
  }

  @Override
  public String getWalletsAmountAsString() {
    double sum = getWalletsAmount();
    return sum == 0 ? "" : Double.toString(sum);
  }

}
