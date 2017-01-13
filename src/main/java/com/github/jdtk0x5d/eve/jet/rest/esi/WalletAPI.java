package com.github.jdtk0x5d.eve.jet.rest.esi;

import com.github.jdtk0x5d.eve.jet.model.api.esi.wallet.Wallet;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface WalletAPI {

  List<Wallet> getWallets(long character_id);
}
