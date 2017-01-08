package com.github.jdtk0x5d.eve.jet.fx.controller.impl;


import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;
import com.github.jdtk0x5d.eve.jet.service.SearchService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class SearchTabController {

  @FXML
  private TableColumn<OrderSearchRow, String> itemColumn;
  @FXML
  private TableColumn<OrderSearchRow, Integer> quantityColumn;
  @FXML
  private TableColumn<OrderSearchRow, String> sellingColumn;
  @FXML
  private TableColumn<OrderSearchRow, String> buyingColumn;
  @FXML
  private TableColumn<OrderSearchRow, Integer> jumpsColumn;
  @FXML
  private TableColumn<OrderSearchRow, Double> profitColumn;
  @FXML
  private TableColumn<OrderSearchRow, Double> perJumpColumn;

  @FXML
  private TextField iskField;
  @FXML
  private TextField cargoField;

  @FXML
  private Button searchButton;

  @Autowired
  private SearchService searchService;

  @PostConstruct
  public void init() {
    initTable();
    initFields();
    searchButton.setOnAction(event -> search());
  }

  private void search() {
    List<String> systems = new ArrayList<>(2);
    systems.add("The Forge");
    systems.add("Domain");
    searchService.searchForOrders(0, 0, systems);
  }

  private void initTable() {
    itemColumn.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
    quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
    sellingColumn.setCellValueFactory(cellData -> cellData.getValue().sellingLocationProperty());
    buyingColumn.setCellValueFactory(cellData -> cellData.getValue().buyingLocationProperty());
    jumpsColumn.setCellValueFactory(cellData -> cellData.getValue().jumpsProperty().asObject());
    profitColumn.setCellValueFactory(cellData -> cellData.getValue().profitProperty().asObject());
    perJumpColumn.setCellValueFactory(cellData -> cellData.getValue().perJumpProfitProperty().asObject());
  }

  private void initFields() {
    iskField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    cargoField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
  }
}
