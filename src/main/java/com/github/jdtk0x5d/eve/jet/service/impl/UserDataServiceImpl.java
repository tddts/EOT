package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import com.github.jdtk0x5d.eve.jet.context.events.UserDataEvent;
import com.github.jdtk0x5d.eve.jet.model.api.esi.wallet.Wallet;
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
