package com.github.jdtk0x5d.eve.jet.oauth.server;

import com.github.jdtk0x5d.eve.jet.service.AuthService;
import com.github.jdtk0x5d.eve.jet.util.Util;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class AuthHttpHandler implements HttpHandler {

  private AuthService authService;
  private String responseFileName;
  private String successMessage;

  public AuthHttpHandler(AuthService authService, String responseFileName, String successMessage) {
    this.authService = authService;
    this.responseFileName = responseFileName;
    this.successMessage = successMessage;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {

    URI requestURI = exchange.getRequestURI();

    if (exchange.getRequestMethod().equals(RequestMethod.GET.name())) {

      String response = successMessage;

      if (requestURI.getQuery() == null) {
        response = Util.loadContent(responseFileName);
        exchange.sendResponseHeaders(HttpStatus.OK.value(), response.length());
      }
      else {
        HttpStatus status = authService.processAuthorization(requestURI.getQuery());
        response = status.is2xxSuccessful() ? response : status.getReasonPhrase();
        exchange.sendResponseHeaders(status.value(), response.length());
      }

      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.flush();
      os.close();
    }
  }
}
