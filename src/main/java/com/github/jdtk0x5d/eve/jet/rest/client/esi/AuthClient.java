package com.github.jdtk0x5d.eve.jet.rest.client.esi;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.model.client.esi.sso.AccessToken;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface AuthClient {

  RestResponse<AccessToken> getToken(String authCode);

  RestResponse<AccessToken> refreshToken();
}
