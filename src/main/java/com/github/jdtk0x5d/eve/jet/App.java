package com.github.jdtk0x5d.eve.jet;

import com.github.jdtk0x5d.eve.jet.context.Context;
import com.github.jdtk0x5d.eve.jet.view.fx.exception.ApplicationExceptionHandler;
import com.github.jdtk0x5d.eve.jet.view.fx.view.View;
import com.github.jdtk0x5d.eve.jet.view.fx.view.ViewUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App{

  public static void main(String[] args) throws Exception {
    Context.initialize("spring-config.xml");
  }
}
