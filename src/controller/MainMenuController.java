package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;

    private void displayPartsInTableView(ObservableList<Part> partList) {
        partsTableView.setItems(partList);

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void displayProductsInTableView(ObservableList<Product> productList) {
        productsTableView.setItems(productList);

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvLevelCol;

    @FXML
    private TableColumn<Part, Double> partPriceCostCol;

    @FXML
    private TextField partsSearchField;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLevelCol;

    @FXML
    private TableColumn<Product, Double> productPriceCostCol;

    @FXML
    private TextField productSearchField;

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionPartsAdd(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionPartsDelete(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setContentText("Are you sure you want to delete?");

        alert.showAndWait();

        if(alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onActionPartsModify(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
        loader.load();

        ModifyPartController mpcController = loader.getController();
        mpcController.sendPart(partsTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionPartsSearch(ActionEvent event) {
        ObservableList<Part> partsList = FXCollections.observableArrayList();

        if (partsSearchField.getText().equals("")) {
            displayPartsInTableView(Inventory.getAllParts());
        } else {
            for(Part part : Inventory.getAllParts()){
                if (part.getName().contains(partsSearchField.getText())) {
                    partsList.add(part);
                }
            }
            displayPartsInTableView(partsList);
        }
    }

    @FXML
    void onActionProductSearch(ActionEvent event) {
        ObservableList<Product> productsList = FXCollections.observableArrayList();

        if (productSearchField.getText().equals("")) {
            displayProductsInTableView(Inventory.getAllProducts());
        } else {
            for(Product product : Inventory.getAllProducts()){
                if (product.getName().contains(productSearchField.getText())) {
                    productsList.add(product);
                }
            }
            displayProductsInTableView(productsList);
        }

    }

    @FXML
    void onActionProductsAdd(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionProductsDelete(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setContentText("Are you sure you want to delete?");

        alert.showAndWait();

        if(alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            Inventory.deleteProduct(productsTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onActionProductsModify(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
        loader.load();

        ModifyProductController mpcController = loader.getController();
        mpcController.sendProduct(productsTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        // Display parts in TableView
        displayPartsInTableView(Inventory.getAllParts());

        // Display products in TableView
        displayProductsInTableView(Inventory.getAllProducts());

    }

}
