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

import com.github.tddts.jet.config.spring.beans.ResourceBundleProvider;
import com.github.tddts.jet.util.Util;
import com.github.tddts.jet.view.fx.exception.ViewException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FxViewProvider {

  private String file;
  private String title;

  private int width = 800;
  private int height = 600;

  @Autowired
  private FxBeanWirer fxBeanWirer;
  @Autowired
  private ResourceBundleProvider resourceBundleProvider;


  public void showView(Stage stage, EventHandler<WindowEvent> onCloseEvent) {

    FXMLLoader loader = new FXMLLoader(Util.getClasspathResourceURL(file), resourceBundleProvider.getResourceBundle());

    try {
      loader.load();
    } catch (IOException e) {
      throw new ViewException(e.getMessage(), e);
    }

    fxBeanWirer.wire(loader.getController());
    Scene scene = new Scene(loader.getRoot(), width, height);
    stage.setOnCloseRequest(onCloseEvent);
    stage.setTitle(title);
    stage.setScene(scene);
    stage.show();
  }

  public void setFile(String file) {
    this.file = file;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}
