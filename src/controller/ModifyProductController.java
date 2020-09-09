package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Part;

public class ModifyProductController implements Initializable {

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

    }

}
