package com.github.jdtk0x5d.eve.jet.model.api.esi.wallet;

import java.math.BigDecimal;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Wallet {

  private int wallet_id;
  private BigDecimal balance;

  public Wallet() {
  }

  public int getWallet_id() {
    return wallet_id;
  }

  public void setWallet_id(int wallet_id) {
    this.wallet_id = wallet_id;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "Wallet{" + "wallet_id=[" + wallet_id + "], balance=[" + balance + "]}";
  }
}
