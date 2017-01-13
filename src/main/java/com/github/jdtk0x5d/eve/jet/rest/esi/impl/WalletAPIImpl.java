package com.github.jdtk0x5d.eve.jet.rest.esi.impl;

import com.github.jdtk0x5d.eve.jet.rest.esi.WalletAPI;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.NullOnException;
import com.github.jdtk0x5d.eve.jet.model.api.esi.wallet.Wallet;
import com.github.jdtk0x5d.eve.jet.util.RequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.apiUrl;
import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.restOperations;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@NullOnException
public class WalletAPIImpl implements WalletAPI {

  @Value("${url.wallets}")
  private String addressCharacterWallets;

  @Override
  public List<Wallet> getWallets(long character_id) {
    Wallet[] wallets = restOperations().exchange(apiUrl(addressCharacterWallets),
        HttpMethod.GET, RequestUtil.authorizedEntity(), Wallet[].class, character_id).getBody();
    return Arrays.asList(wallets);
  }
}
