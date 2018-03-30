/*
 * Copyright 2017 Tigran Dadaiants
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

package com.github.tddts.jet.view.fx.controller;

import com.github.tddts.jet.config.spring.annotations.Message;
import com.github.tddts.jet.consts.AuthorizationType;
import com.github.tddts.jet.consts.RestDataSource;
import com.github.tddts.jet.context.events.AuthorizationEvent;
import com.github.tddts.jet.service.LoginService;
import com.github.tddts.jet.service.UserDataService;
import com.github.tddts.jet.view.fx.annotations.FxController;
import com.github.tddts.jet.view.fx.dialog.DevCredentialsDialog;
import com.github.tddts.jet.view.fx.spring.DialogProvider;
import com.github.tddts.jet.view.fx.tools.message.MessageProvider;
import com.github.tddts.jet.util.ViewUtil;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Optional;


/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@FxController("fxml/header.fxml")
public class LoginController {

  private static final String CSS_HEADER_AUTHORIZED = "header-authorized";
  private static final String CSS_STATUS_AUTHORIZED = "status-authorized";
  private static final String CSS_HEADER_UNAUTHORIZED = "header-unauthorized";
  private static final String CSS_STATUS_UNAUTHORIZED = "status-unauthorized";

  @Message("login.authorized")
  private String messageAuthorized;
  @Message("login.unauthorized")
  private String messageUnauthorized;
  @Message("dialog.auth.expiration")
  private String messageDialogExpiration;

  @FXML
  private HBox headerHbox;
  @FXML
  private ChoiceBox<RestDataSource> dataSourceBox;
  @FXML
  private ChoiceBox<AuthorizationType> loginChoiceBox;
  @FXML
  private Button loginButton;
  @FXML
  private Label loginStatusLabel;

  @Autowired
  private MessageProvider messageProvider;
  @Autowired
  private LoginService loginService;
  @Autowired
  private UserDataService userDataService;
  @Autowired
  private DialogProvider dialogProvider;

  @PostConstruct
  private void init() {
    loginChoiceBox.setConverter(messageProvider.getConverter(AuthorizationType.values()));
    loginChoiceBox.setItems(FXCollections.observableArrayList(AuthorizationType.values()));
    loginChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldval, val) -> processLoginTypeChange(val));
    loginChoiceBox.getSelectionModel().selectFirst();
    loginButton.setOnAction(event -> processLogin());

    dataSourceBox.setConverter(messageProvider.getConverter(RestDataSource.values()));
    dataSourceBox.setItems(FXCollections.observableArrayList(RestDataSource.values()));
    dataSourceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldval, val) -> processDataSourceChange(val));
    dataSourceBox.getSelectionModel().selectFirst();

    loginChoiceBox.managedProperty().bind(loginChoiceBox.visibleProperty());
    loginButton.managedProperty().bind(loginButton.visibleProperty());
  }

  private void processLoginTypeChange(AuthorizationType value) {
    loginService.processLoginTypeChange(value);
  }

  private void processDataSourceChange(RestDataSource restDataSource) {
    loginService.processDataSourceChange(restDataSource);
  }

  private void processLogin() {
    loginService.processLogin(this::getCredentials, ViewUtil::openWebpage);
  }

  private Optional<Pair<String, String>> getCredentials() {
    return dialogProvider.getDialog(DevCredentialsDialog.class).showAndWait();
  }

  @Subscribe
  private void processAuthorizationEvent(AuthorizationEvent authorizationEvent) {
    Platform.runLater(() -> {

      if (authorizationEvent.isAuthorized()) {
        setHeader(messageAuthorized, CSS_HEADER_AUTHORIZED, CSS_STATUS_AUTHORIZED, false);
      }

      if (authorizationEvent.isExpired()) {
        setHeader(messageUnauthorized, CSS_HEADER_UNAUTHORIZED, CSS_STATUS_UNAUTHORIZED, true);
      }
    });
  }

  private void setHeader(String message, String headerClass, String statusTextClass, boolean showLoginOptions) {
    loginStatusLabel.setText(message);

    headerHbox.getStyleClass().clear();
    headerHbox.getStyleClass().add(headerClass);

    loginStatusLabel.getStyleClass().clear();
    loginStatusLabel.getStyleClass().add(statusTextClass);

    loginChoiceBox.setVisible(showLoginOptions);
    loginButton.setVisible(showLoginOptions);
  }

}
