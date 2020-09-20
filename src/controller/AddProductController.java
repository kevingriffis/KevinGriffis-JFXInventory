package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;

public class AddProductController implements Initializable {

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

    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionDelete(ActionEvent event) {

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
    void onActionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Since this is a new product, all the parts will be listed in the Add table
        displayAddPartsInTableView(Inventory.getAllParts());

        // Show the parts that will be added on save (so they can be deleted)
        // This will be initially blank
        displayDeletePartsInTableView(addedParts);
    }

}
