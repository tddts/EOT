package com.github.jdtk0x5d.eve.jet.api.esi;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface UserInterfaceAPI {

  void setWaypoint(int destinationId, boolean clearOtherWaypoints, boolean addToBeginning);
}
