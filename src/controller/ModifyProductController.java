package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;
    private int productIndex;

    private ObservableList<Part> productParts = FXCollections.observableArrayList();

    @FXML
    private TextField IdTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TableView<Part> addPartsTableView;

    @FXML
    private TableColumn<Part, Integer> addPartsIdCol;

    @FXML
    private TableColumn<Part, String> addPartsNameCol;

    @FXML
    private TableColumn<Part, Integer> addPartsInvCol;

    @FXML
    private TableColumn<Part, Double> addPartsPriceCostCol;

    @FXML
    private TableView<Part> deletePartsTableView;

    @FXML
    private TableColumn<Part, Integer> deletePartsIdCol;

    @FXML
    private TableColumn<Part, String> deletePartsNameCol;

    @FXML
    private TableColumn<Part, Integer> deletePartsInvCol;

    @FXML
    private TableColumn<Part, Double> deletePartsPriceCostCol;

    @FXML
    private TextField partsSearchField;

    @FXML
    void onActionAdd(ActionEvent event) {

    }

    public void sendProduct(Product product) {
        // Get the index of the product from the Inventory ObservableList
        productIndex = Inventory.getAllProducts().indexOf(product);

        IdTxt.setText(String.valueOf(product.getId()));
        nameTxt.setText(product.getName());
        invTxt.setText(String.valueOf(product.getStock()));
        priceTxt.setText(String.valueOf(product.getPrice()));
        minTxt.setText(String.valueOf(product.getMin()));
        maxTxt.setText(String.valueOf(product.getMax()));

        // Grab the initial association of parts and add to productParts
        productParts.addAll(product.getAllAssociatedParts());


    }

    private void displayAddPartsInTableView(ObservableList<Part> partList) {
        addPartsTableView.setItems(partList);

        addPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPartsPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void displayDeletePartsInTableView(ObservableList<Part> partList) {
        deletePartsTableView.setItems(partList);

        deletePartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        deletePartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        deletePartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        deletePartsPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionDelete(ActionEvent event) {

    }

    @FXML
    void onActionPartsSearch(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayAddPartsInTableView(Inventory.getAllParts());

        displayDeletePartsInTableView(productParts);
    }

}
