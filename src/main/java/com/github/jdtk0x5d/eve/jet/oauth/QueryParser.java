package com.github.jdtk0x5d.eve.jet.oauth;

import com.github.jdtk0x5d.eve.jet.model.client.esi.sso.AccessToken;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface QueryParser {

  AccessToken parseAccessToken(String query);

  String parseAuthCode(String query);
}
