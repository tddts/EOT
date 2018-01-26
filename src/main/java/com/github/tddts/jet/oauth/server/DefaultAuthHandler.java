package com.github.tddts.jet.oauth.server;

import com.github.tddts.jet.service.AuthService;
import com.github.tddts.tools.web.oauth.handler.AuthHandler;
import com.github.tddts.tools.web.oauth.handler.AuthHandlerCallback;
import org.apache.http.NameValuePair;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DefaultAuthHandler implements AuthHandler {

  private AuthService authService;
  private String responseText;
  private String successMessage;

  public DefaultAuthHandler(AuthService authService, String responseText, String successMessage) {
    this.authService = authService;
    this.responseText = responseText;
    this.successMessage = successMessage;
  }

  @Override
  public void process(AuthHandlerCallback callback, List<NameValuePair> params) {
    if (params.isEmpty()) {
      callback.returnMessage(responseText);
    }
    else {
      HttpStatus status = authService.processAuthorization(params);
      String message = status.is2xxSuccessful() ? successMessage : status.getReasonPhrase();
      callback.returnMessage(status.value(), message);
    }
  }
}
