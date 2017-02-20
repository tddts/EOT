package com.github.jdtk0x5d.eve.jet.config.ebean;

import com.github.jdtk0x5d.eve.jet.exception.BeanFactoryInitializationException;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import org.springframework.beans.factory.FactoryBean;

import javax.annotation.PostConstruct;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class EbeanServerFactoryBean implements FactoryBean<EbeanServer> {

  private ServerConfig serverConfig;
  private EbeanServer server;

  @PostConstruct
  public void init() {
    if (serverConfig == null) {
      throw new BeanFactoryInitializationException("Could not create Ebean Server. No ServerConfig set!");
    }
    server = EbeanServerFactory.create(serverConfig);
  }

  @Override
  public EbeanServer getObject() throws Exception {
    return server;
  }

  @Override
  public Class<?> getObjectType() {
    return EbeanServer.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  public ServerConfig getServerConfig() {
    return serverConfig;
  }

  public void setServerConfig(ServerConfig serverConfig) {
    this.serverConfig = serverConfig;
  }
}
