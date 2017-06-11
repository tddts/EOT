package com.github.jdtk0x5d.eve.jet.rest.client.esi;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface UserInterfaceClient {

  RestResponse<String> setWaypoint(int destinationId, boolean clearOtherWaypoints, boolean addToBeginning);

  RestResponse<String> openMarketDetails(int typeId);
}
