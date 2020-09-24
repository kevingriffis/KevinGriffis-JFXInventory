package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TextField idTxt;

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
        productParts.add(addPartsTableView.getSelectionModel().getSelectedItem());
    }

    public void sendProduct(Product product) {
        // Get the index of the product from the Inventory ObservableList
        productIndex = Inventory.getAllProducts().indexOf(product);

        idTxt.setText(String.valueOf(product.getId()));
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
    void onActionCancel(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDelete(ActionEvent event) {
        productParts.remove(deletePartsTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionPartsSearch(ActionEvent event) {
        ObservableList<Part> partsList = FXCollections.observableArrayList();

        if (partsSearchField.getText().equals("")) {
            displayAddPartsInTableView(Inventory.getAllParts());
        } else {
            for(Part part : Inventory.getAllParts()){
                if (part.getName().contains(partsSearchField.getText())) {
                    partsList.add(part);
                }
            }
            displayAddPartsInTableView(partsList);
        }
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {

        try {
            int id = Integer.parseInt(idTxt.getText());
            String name = nameTxt.getText();
            double price = Double.parseDouble(priceTxt.getText());
            int stock = Integer.parseInt(invTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());

            // Check to see if stock is out of bounds of minimum and maximum inventory
            if(stock > max) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Inventory is greater than the maximum entered.");
                alert.showAndWait();
                return;

            } else if (stock < min) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Inventory is less than the minimum entered.");
                alert.showAndWait();
                return;
            }

            Product product = new Product(id, name, price, stock, min, max);

            for (Part part : productParts) {
                product.addAssociatedPart(part);
            }

            Inventory.updateProduct(productIndex, product);

            // Return to Main Menu
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please enter valid values for each field!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayAddPartsInTableView(Inventory.getAllParts());

        displayDeletePartsInTableView(productParts);
    }

}
