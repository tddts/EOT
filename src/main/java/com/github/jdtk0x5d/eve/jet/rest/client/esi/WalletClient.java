package com.github.jdtk0x5d.eve.jet.rest.client.esi;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.model.client.esi.wallet.Wallet;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface WalletClient {

  RestResponse<List<Wallet>> getWallets(long character_id);
}
