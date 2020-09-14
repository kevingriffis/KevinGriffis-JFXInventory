package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    }

    @FXML
    void onActionPartsModify(ActionEvent event) {

    }

    @FXML
    void onActionPartsSearch(ActionEvent event) {

    }

    @FXML
    void onActionProductSearch(ActionEvent event) {

    }

    @FXML
    void onActionProductsAdd(ActionEvent event) {

    }

    @FXML
    void onActionProductsDelete(ActionEvent event) {

    }

    @FXML
    void onActionProductsModify(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        // Display parts in TableView
        partsTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Display products in TableView
        productsTableView.setItems(Inventory.getAllProducts());

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}
