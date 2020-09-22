package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;

    private ObservableList<Part> addedParts = FXCollections.observableArrayList();

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

    private int findNextAvailableProductId () {
        // Same algorithm as findNextAvailablePartId, but applied to products
        // This algorithm will not reuse deleted IDs that are between
        // existing IDs
        ArrayList<Integer> idArrayList = new ArrayList<>();
        int greatest;

        for (Product product :
                Inventory.getAllProducts()){
            idArrayList.add(product.getId());
        }

        Collections.sort(idArrayList);

        greatest = idArrayList.get(idArrayList.size() - 1);
        return ++greatest;
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
    void onActionAdd(ActionEvent event) {
        addedParts.add(addPartsTableView.getSelectionModel().getSelectedItem());

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
        addedParts.remove(deletePartsTableView.getSelectionModel().getSelectedItem());
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
            int id = findNextAvailableProductId();
            String name = nameTxt.getText();
            double price = Double.parseDouble(priceTxt.getText());
            int stock = Integer.parseInt(invTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());

            Product product = new Product(id, name, price, stock, min, max);

            for (Part part : addedParts) {
                product.addAssociatedPart(part);
            }

            Inventory.addProduct(product);

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

        // Show the parts that will be added on save (so they can be deleted)
        // This will be initially blank
        displayDeletePartsInTableView(addedParts);
    }

}
