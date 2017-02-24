package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import com.github.jdtk0x5d.eve.jet.consts.AuthorizationType;
import com.github.jdtk0x5d.eve.jet.oauth.EmbeddedServer;
import com.github.jdtk0x5d.eve.jet.service.AuthService;
import com.github.jdtk0x5d.eve.jet.service.LoginService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class LoginServiceImpl implements LoginService {

  @Autowired
  private UserBean userBean;
  @Autowired
  private AuthService authService;
  @Autowired
  private EmbeddedServer server;


  @Override
  public void processLogin(Supplier<Optional<Pair<String, String>>> credentialsSupplier, Consumer<URI> loginUriConsumer) {
    AuthorizationType authType = userBean.getAuthorizationType();

    server.start();

    if (authType.isImplicit()) {
      loginUriConsumer.accept(authService.getLoginPageURI());
    }

    if (authType.isDev()) {
      Optional<Pair<String, String>> credentialsPair = credentialsSupplier.get();

      if (credentialsPair.isPresent()) {
        Pair<String, String> credentials = credentialsPair.get();
        userBean.setClientId(credentials.getKey());
        userBean.setSercretKey(credentials.getValue());

        loginUriConsumer.accept(authService.getLoginPageURI(userBean.getClientId()));
      }
    }
  }

  @Override
  public void processLoginTypeChange(AuthorizationType value) {
    userBean.setAuthorizationType(value);
  }
}
