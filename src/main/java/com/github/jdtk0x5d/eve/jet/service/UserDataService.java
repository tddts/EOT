package com.github.jdtk0x5d.eve.jet.service;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface UserDataService {

  long getWalletsAmount();

  String getWalletsAmountAsString();

  void saveCharacterId(String idString);

  String getCharacterId();
}
