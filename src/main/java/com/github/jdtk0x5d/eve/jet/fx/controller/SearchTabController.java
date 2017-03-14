package com.github.jdtk0x5d.eve.jet.fx.controller;


import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.context.events.AuthorizationEvent;
import com.github.jdtk0x5d.eve.jet.context.events.UserDataEvent;
import com.github.jdtk0x5d.eve.jet.fx.annotations.FXController;
import com.github.jdtk0x5d.eve.jet.fx.controls.DoubleTextField;
import com.github.jdtk0x5d.eve.jet.fx.controls.ItemListTextField;
import com.github.jdtk0x5d.eve.jet.fx.controls.LongTextField;
import com.github.jdtk0x5d.eve.jet.fx.controls.PercentageTextField;
import com.github.jdtk0x5d.eve.jet.fx.tools.message.MessageStringConverter;
import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;
import com.github.jdtk0x5d.eve.jet.model.app.SearchParams;
import com.github.jdtk0x5d.eve.jet.service.SearchService;
import com.github.jdtk0x5d.eve.jet.service.TaskService;
import com.github.jdtk0x5d.eve.jet.service.UserDataService;
import com.github.jdtk0x5d.eve.jet.service.UserInterfaceService;
import com.google.common.eventbus.Subscribe;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;


/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@FXController(view = "fxml/tab_search.fxml")
public class SearchTabController {

  @FXML
  private TableColumn<OrderSearchRow, String> itemColumn;
  @FXML
  private TableColumn<OrderSearchRow, Long> quantityColumn;
  @FXML
  private TableColumn<OrderSearchRow, String> sellingColumn;
  @FXML
  private TableColumn<OrderSearchRow, String> buyingColumn;
  @FXML
  private TableColumn<OrderSearchRow, Double> volumeColumn;
  @FXML
  private TableColumn<OrderSearchRow, Double> volumeRemainColumn;
  @FXML
  private TableColumn<OrderSearchRow, Double> sellingPriceColumn;
  @FXML
  private TableColumn<OrderSearchRow, Double> buyingPriceColumn;
  @FXML
  private TableColumn<OrderSearchRow, Integer> jumpsColumn;
  @FXML
  private TableColumn<OrderSearchRow, Double> profitColumn;
  @FXML
  private TableColumn<OrderSearchRow, Double> perJumpColumn;

  @FXML
  private TableView<OrderSearchRow> searchTable;

  @FXML
  private LongTextField iskField;
  @FXML
  private DoubleTextField cargoField;
  @FXML
  private PercentageTextField taxField;
  @FXML
  private ItemListTextField regionsField;

  @FXML
  private ChoiceBox<DotlanRouteOption> routeOptionBox;
  @FXML
  private ChoiceBox<String> regionChoiceBox;

  @FXML
  private Button searchButton;
  @FXML
  private Button setWaypointsButton;
  @FXML
  private Button setFirstWaypointButton;
  @FXML
  private Button setLastWaypointButton;
  @FXML
  private Button openMarketButton;
  @FXML
  private Button regionSelectionButton;
  @FXML
  private Button clearRegionsButton;

  @Value("#{${static.regions}.keySet()}")
  private Set<String> regionNames;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private SearchService searchService;
  @Autowired
  private UserInterfaceService userInterfaceService;
  @Autowired
  private UserDataService userDataService;
  @Autowired
  private TaskService taskService;

  @PostConstruct
  public void init() {
    initTable();
    initButtons();
    initChoiceBoxes();
  }

  private void initTable() {
    itemColumn.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
    quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

    sellingColumn.setCellValueFactory(cellData -> cellData.getValue().sellingLocationProperty());
    buyingColumn.setCellValueFactory(cellData -> cellData.getValue().buyingLocationProperty());

    volumeColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty().asObject());
    volumeRemainColumn.setCellValueFactory(cellData -> cellData.getValue().volumeRemainProperty().asObject());

    sellingPriceColumn.setCellValueFactory(cellData -> cellData.getValue().sellPriceProperty().asObject());
    buyingPriceColumn.setCellValueFactory(cellData -> cellData.getValue().buyPriceProperty().asObject());

    jumpsColumn.setCellValueFactory(cellData -> cellData.getValue().jumpsProperty().asObject());

    profitColumn.setCellValueFactory(cellData -> cellData.getValue().profitProperty().asObject());
    perJumpColumn.setCellValueFactory(cellData -> cellData.getValue().perJumpProfitProperty().asObject());

    searchTable.setItems(FXCollections.observableArrayList());
  }

  private void initButtons() {
    searchButton.setOnAction(event -> search());
    regionSelectionButton.setOnAction(event -> addRegion());
    clearRegionsButton.setOnAction(event -> clearRegions());

    setWaypointsButton.setOnAction(event -> onSelectedRow(row -> userInterfaceService.setFullRoute(row)));
    setFirstWaypointButton.setOnAction(event -> onSelectedRow(row -> userInterfaceService.setBuyWaypoint(row)));
    setLastWaypointButton.setOnAction(event -> onSelectedRow(row -> userInterfaceService.setSellWaypoint(row)));
    openMarketButton.setOnAction(event -> onSelectedRow(row -> userInterfaceService.openMarketDetails(row)));
  }

  private void initChoiceBoxes() {
    routeOptionBox.setConverter(new MessageStringConverter<>(messageSource, DotlanRouteOption.values()));
    routeOptionBox.setItems(FXCollections.observableArrayList(DotlanRouteOption.values()));
    routeOptionBox.getSelectionModel().selectFirst();

    regionChoiceBox.setItems(FXCollections.observableArrayList(regionNames));
    regionChoiceBox.getSelectionModel().selectFirst();
  }

  //--------------------------------------------------------------------------------------------------------------------

  private void search() {
    SearchParams searchParams = new SearchParams();
    searchParams
        .setIsk(iskField.getNumber())
        .setCargo(cargoField.getNumber())
        .setTax(taxField.getFraction())
        .setRouteOption(routeOptionBox.getValue())
        .setRegions(getRegions())
        .setResultConsumer(resultList -> {
          searchTable.getItems().clear();
          searchTable.getItems().addAll(resultList);
        });

    taskService.execute(() -> searchService.searchForOrders(searchParams));
  }

  private void addRegion() {
    regionsField.addItem(regionChoiceBox.getSelectionModel().getSelectedItem());
  }

  private void clearRegions() {
    regionsField.clear();
  }

  private List<String> getRegions() {
    return regionsField.getItems();
  }

  private void onSelectedRow(Consumer<OrderSearchRow> rowConsumer) {
    OrderSearchRow currentRow = searchTable.getSelectionModel().getSelectedItem();
    if (currentRow != null) rowConsumer.accept(currentRow);
  }

  @Subscribe
  private void processUserDataEvent(UserDataEvent event) {
    if (event.isCharacterIdSet()) setIskAmount();
  }

  @Subscribe
  private void processAuthorizationEvent(AuthorizationEvent event) {
    if (event.isAuthorized()) setIskAmount();
  }

  private void setIskAmount() {
    iskField.setText(userDataService.getWalletsAmountAsString());
  }
}
