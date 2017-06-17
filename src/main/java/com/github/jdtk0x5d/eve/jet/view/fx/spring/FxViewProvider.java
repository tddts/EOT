package com.github.jdtk0x5d.eve.jet.view.fx.spring;

import com.github.jdtk0x5d.eve.jet.config.spring.beans.ResourceBundleContainer;
import com.github.jdtk0x5d.eve.jet.view.fx.view.View;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FxViewProvider {

  private String file;
  private String title;

  private int width;
  private int height;

  @Autowired
  private FxViewWirer fxViewWirer;
  @Autowired
  private ResourceBundleContainer resourceBundleContainer;


  public void showView(Stage stage, EventHandler<WindowEvent> onCloseEvent) {

    View<?> view = new View<>(file, resourceBundleContainer.getResourceBundle());
    fxViewWirer.wire(view);

    Scene scene = new Scene(view.getRoot(), width, height);
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
