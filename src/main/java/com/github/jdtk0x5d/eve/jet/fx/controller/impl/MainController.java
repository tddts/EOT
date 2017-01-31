package com.github.jdtk0x5d.eve.jet.fx.controller.impl;

import com.github.jdtk0x5d.eve.jet.fx.controller.NestedControllerAware;
import javafx.fxml.FXML;

import javax.annotation.PostConstruct;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class MainController implements NestedControllerAware {

  @FXML
  private LoginController headerController;
  @FXML
  private SearchTabController searchTabController;

  @PostConstruct
  public void init() {

  }

  @Override
  public Object[] getNestedControllers() {
    return new Object[]{headerController, searchTabController};
  }
}
