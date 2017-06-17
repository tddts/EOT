package com.github.jdtk0x5d.eve.jet.util;

import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;
import com.github.jdtk0x5d.eve.jet.exception.BrowserOpeningException;
import com.github.jdtk0x5d.eve.jet.util.SpringUtil;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ViewUtil {

  public static void openWebpage(URI uri) throws BrowserOpeningException {
    Thread thread = new Thread(() -> openPage(uri));
    thread.start();
  }

  private static void openPage(URI uri) {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
      try {
        desktop.browse(uri);
      } catch (Exception e) {
        throw new BrowserOpeningException(e);
      }
    }
  }

}
