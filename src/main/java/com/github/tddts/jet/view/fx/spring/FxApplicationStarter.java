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

package com.github.tddts.jet.view.fx.spring;

import com.github.tddts.jet.view.fx.tools.ApplicationStarter;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@code FxApplicationStarter} is a {@link ApplicationStarter} implementation for JavaFX application.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FxApplicationStarter extends Application implements ApplicationStarter {

  private static FxViewProvider staticFxViewProvider;
  private static DialogProvider staticDialogProvider;

  @Autowired
  private FxViewProvider fxViewProvider;
  @Autowired
  private DialogProvider dialogProvider;

  @Override
  public void startApplication() {
    staticFxViewProvider = fxViewProvider;
    staticDialogProvider = dialogProvider;
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new FxUncaughtExceptionHandler(staticDialogProvider));
    staticFxViewProvider.showView(primaryStage, event -> System.exit(0));
  }
}
