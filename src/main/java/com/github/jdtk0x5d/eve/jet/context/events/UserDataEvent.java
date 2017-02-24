package com.github.jdtk0x5d.eve.jet.context.events;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum UserDataEvent {

  CHARACTER_ID_SET;

  public boolean isCharacterIdSet() {
    return this == CHARACTER_ID_SET;
  }
}
