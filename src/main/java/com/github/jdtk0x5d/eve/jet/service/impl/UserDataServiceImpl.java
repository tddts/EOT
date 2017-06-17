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

package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import com.github.jdtk0x5d.eve.jet.context.events.UserDataEvent;
import com.github.jdtk0x5d.eve.jet.model.client.esi.wallet.Wallet;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.rest.client.esi.WalletClient;
import com.github.jdtk0x5d.eve.jet.service.UserDataService;
import com.github.jdtk0x5d.eve.jet.util.Util;
import com.google.common.eventbus.EventBus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class UserDataServiceImpl implements UserDataService {

  private static final String CHARACTER_ID = "character.id";

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
  public long getWalletsAmount() {
    long characterId = userBean.getCharacter_id();

    if (characterId == 0) return 0;

    RestResponse<List<Wallet>> wallets = walletClient.getWallets(characterId);
    if (wallets.hasObject()) {
      long sum = 0;
      List<Wallet> walletList = wallets.getObject();
      for (Wallet wallet : walletList) {
        sum += wallet.getBalance().longValue();
      }
      return sum;
    }

    return 0;
  }

  @Override
  public String getWalletsAmountAsString() {
    long sum = getWalletsAmount();
    return sum == 0 ? "" : Long.toString(sum);
  }

  @Override
  public void saveCharacterId(String idString) {
    if (NumberUtils.isDigits(idString)) {
      long id = NumberUtils.toLong(idString);
      userBean.setCharacter_id(id);
      Util.saveProperty(userDataFileName, CHARACTER_ID, idString);
      eventBus.post(UserDataEvent.CHARACTER_ID_SET);
    }
  }

  @Override
  public String getCharacterId() {
    long id = userBean.getCharacter_id();
    if (id == 0) {
      String idString = Util.loadProperty(userDataFileName, CHARACTER_ID);

      if (idString != null && !idString.isEmpty() && NumberUtils.isDigits(idString)) {
        userBean.setCharacter_id(NumberUtils.toLong(idString));
        return idString;
      }
      return "";
    }
    return Long.toString(id);
  }
}
