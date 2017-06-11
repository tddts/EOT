package com.github.jdtk0x5d.eve.jet.rest.client.esi.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestClient;
import com.github.jdtk0x5d.eve.jet.model.api.esi.wallet.Wallet;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.rest.client.esi.WalletClient;
import com.github.jdtk0x5d.eve.jet.rest.provider.RestClientProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class WalletClientImpl implements WalletClient {

  @Value("${path.wallets}")
  private String addressCharacterWallets;

  @Autowired
  private RestClientProvider client;

  @Override
  public RestResponse<List<Wallet>> getWallets(long character_id) {
    return RestResponse.fromArrayResponse(
        client.restOperations().exchange(
            client.apiUrl(addressCharacterWallets), HttpMethod.GET, client.authorizedEntity(), Wallet[].class, character_id));
  }
}
