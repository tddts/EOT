package com.github.jdtk0x5d.eve.jet.rest.api.esi;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import com.github.jdtk0x5d.eve.jet.model.api.esi.sso.AccessToken;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface AuthAPI {

  RestResponse<AccessToken> getToken(String authCode);

  RestResponse<AccessToken> refreshToken();
}
