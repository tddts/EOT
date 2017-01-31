package com.github.jdtk0x5d.eve.jet.rest.api.esi.impl;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestApi;
import com.github.jdtk0x5d.eve.jet.model.api.esi.wallet.Wallet;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.WalletAPI;
import com.github.jdtk0x5d.eve.jet.util.RequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.apiUrl;
import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.restOperations;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestApi
public class WalletAPIImpl implements WalletAPI {

  @Value("${url.wallets}")
  private String addressCharacterWallets;

  @Override
  public RestResponse<List<Wallet>> getWallets(long character_id) {
    return RestResponse.fromArrayResponse(
        restOperations().exchange(
            apiUrl(addressCharacterWallets), HttpMethod.GET, RequestUtil.authorizedEntity(), Wallet[].class, character_id));
  }
}
