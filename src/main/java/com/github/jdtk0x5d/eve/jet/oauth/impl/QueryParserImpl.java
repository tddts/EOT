package com.github.jdtk0x5d.eve.jet.oauth.impl;

import com.github.jdtk0x5d.eve.jet.model.client.esi.sso.AccessToken;
import com.github.jdtk0x5d.eve.jet.oauth.QueryParser;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class QueryParserImpl implements QueryParser {

  @Override
  public AccessToken parseAccessToken(String query) {
    AccessToken accessToken = new AccessToken();

    Map<String, String> params = getParams(query.substring(query.indexOf('#') + 1));

    accessToken.setAccess_token(params.get("access_token"));
    accessToken.setToken_type(params.get("token_type"));
    accessToken.setExpires_in(Long.parseLong(params.get("expires_in")));
    return accessToken;
  }

  @Override
  public String parseAuthCode(String query) {
    Map<String, String> params = getParams(query);
    return params.get("code");
  }

  private Map<String, String> getParams(String query) {
    List<NameValuePair> paramList = URLEncodedUtils.parse((query), Charset.forName("UTF-8"));
    return paramList.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
  }

}
