package com.github.jdtk0x5d.eve.jet.oauth.server;

import com.github.jdtk0x5d.eve.jet.exception.ServerCreationException;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class TransientServer {

  private final Logger logger = LogManager.getLogger(TransientServer.class);

  private HttpServer server;
  private int stopDelay;
  private long timeout;
  private boolean started;

  private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
  private Future<?> serverTimeoutTaskFuture;


  public TransientServer(String resourceLocation, int port, long timeout, HttpHandler httpHandler) {
    this.timeout = timeout;
    try {

      server = HttpServer.create(new InetSocketAddress(port), 0);
      server.createContext(resourceLocation, httpHandler);
      server.setExecutor(Executors.newSingleThreadExecutor());

    } catch (IOException e) {
      throw new ServerCreationException(e);
    }
  }

  public void start() {
    if (started) {
      serverTimeoutTaskFuture.cancel(false);
      scheduleServerTimeoutStop();
      logger.debug("Server timeout refreshed.");
    }
    else {
      server.start();
      scheduleServerTimeoutStop();
      started = true;
      logger.debug("Server started.");
    }
  }

  public void stop() {
    server.stop(stopDelay);
    started = false;
    logger.debug("Server stopped.");
  }

  public void setStopDelay(int stopDelay) {
    this.stopDelay = stopDelay;
  }

  private void scheduleServerTimeoutStop() {
    serverTimeoutTaskFuture = executor.schedule(this::stopByTimeout, timeout, TimeUnit.SECONDS);
  }

  private void stopByTimeout() {
    logger.debug("Timeout expired.");
    stop();
  }
}
