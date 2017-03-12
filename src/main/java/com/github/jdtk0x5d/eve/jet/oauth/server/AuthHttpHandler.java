package com.github.jdtk0x5d.eve.jet.oauth.server;

import com.github.jdtk0x5d.eve.jet.service.AuthService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class AuthHttpHandler implements HttpHandler {

  private static final Logger logger = LogManager.getLogger(AuthHttpHandler.class);

  private AuthService authService;
  private String responseText;
  private String successMessage;

  public AuthHttpHandler(AuthService authService, String responseText, String successMessage) {
    this.authService = authService;
    this.responseText = responseText;
    this.successMessage = successMessage;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {

    String response = "";

    try {

      URI requestURI = exchange.getRequestURI();

      logger.debug("Request URI:[" + requestURI + "]");
      logger.debug("Query: " + requestURI.getQuery());

      if (exchange.getRequestMethod().equals(RequestMethod.GET.name())) {

        response = successMessage;

        if (requestURI.getQuery() == null) {
          logger.debug("Query is null.");
          response = responseText;
          exchange.sendResponseHeaders(HttpStatus.OK.value(), 0);
        }
        else {
          logger.debug("Query is not null.");
          HttpStatus status = authService.processAuthorization(requestURI.getQuery());
          response = status.is2xxSuccessful() ? response : status.getReasonPhrase();
          exchange.sendResponseHeaders(status.value(), 0);
        }
      }

    } finally {

      logger.debug("Response: \n" + response + "");

      try (OutputStream os = exchange.getResponseBody()) {
        os.write(response.getBytes());
        os.flush();
      }

      logger.debug("Response stream closed.");
    }


  }
}
