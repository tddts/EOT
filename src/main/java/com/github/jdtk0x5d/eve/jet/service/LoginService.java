package com.github.jdtk0x5d.eve.jet.service;


import com.github.jdtk0x5d.eve.jet.consts.AuthorizationType;
import com.github.jdtk0x5d.eve.jet.consts.RestDataSource;
import org.apache.commons.lang3.tuple.Pair;

import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface LoginService {

  void processLogin(Supplier<Optional<Pair<String, String>>> credentialsSupplier, Consumer<URI> loginUriConsumer);

  void processLoginTypeChange(AuthorizationType value);

  void processDataSourceChange(RestDataSource restDataSource);
}
