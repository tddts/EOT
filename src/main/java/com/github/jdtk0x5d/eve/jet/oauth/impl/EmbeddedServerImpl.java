package com.github.jdtk0x5d.eve.jet.oauth.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Message;
import com.github.jdtk0x5d.eve.jet.service.AuthService;
import com.github.jdtk0x5d.eve.jet.oauth.EmbeddedServer;
import com.github.jdtk0x5d.eve.jet.oauth.server.AuthHttpHandler;
import com.github.jdtk0x5d.eve.jet.oauth.server.TransientServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class EmbeddedServerImpl implements EmbeddedServer {

  private final Logger logger = LogManager.getLogger(EmbeddedServerImpl.class);

  @Value("${server.resource.location}")
  private String resourceLocation;

  @Value("${server.port}")
  private int port;

  @Value("${server.timeout}")
  private long timeout;

  @Value("${auth.implicit.response.html}")
  private String implicitResponseFileName;

  @Message("auth.success")
  private String successMessage;

  @Autowired
  private AuthService authService;

  private TransientServer server;

  @PostConstruct
  public void init() {
    AuthHttpHandler httpHandler = new AuthHttpHandler(authService, implicitResponseFileName, successMessage);
    server = new TransientServer(resourceLocation, port, timeout, httpHandler);
  }


  @Override
  public void start() {
    server.start();
  }

  @Override
  public void stop() {
    server.stop();
  }

}
