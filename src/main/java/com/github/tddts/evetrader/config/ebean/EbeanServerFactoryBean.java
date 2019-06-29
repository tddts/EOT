/*
 * Copyright 2018 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.evetrader.config.ebean;

import com.github.tddts.evetrader.exception.BeanFactoryInitializationException;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import org.springframework.beans.factory.FactoryBean;

import javax.annotation.PostConstruct;

/**
 * Factory bean for {@link EbeanServer} object.
 * Crates {@link EbeanServer} from given {@link ServerConfig} object.
 *
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
