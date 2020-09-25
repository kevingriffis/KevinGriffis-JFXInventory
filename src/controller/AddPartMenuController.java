package controller;

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
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;


public class AddPartMenuController implements Initializable {

    Stage stage;
    Parent scene;

    // Set to true since In-House radio button is selected by default
    private boolean isInHouse = true;
    private ActionEvent event;

    private int findNextAvailablePartId () {
        // A bit of a crude algorithm to find an available part ID
        // This algorithm will not reuse deleted IDs that are between
        // existing IDs
        ArrayList<Integer> idArrayList = new ArrayList<>();
        int greatest;

        for (Part part :
             Inventory.getAllParts()){
            idArrayList.add(part.getId());
        }

        Collections.sort(idArrayList);

        greatest = idArrayList.get(idArrayList.size() - 1);
        return ++greatest;
    }

    @FXML
    private RadioButton inHouseRdoBtn;

    @FXML
    private RadioButton outsourcedRdoBtn;

    @FXML
    private Label inOutLabel;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceCostTxt;

    @FXML
    private TextField inOutTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    void onActionInHouseRdoBtn(ActionEvent event) {
        isInHouse = true;
        inOutLabel.setText("Machine ID");
    }

    @FXML
    void onActionOutsourcedRdoBtn(ActionEvent event) {
        isInHouse = false;
        inOutLabel.setText("Company Name");
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setContentText("Are you sure?");

        alert.showAndWait();

        if(alert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {

        try {
            int id = findNextAvailablePartId();
            String name = nameTxt.getText();
            double price = Double.parseDouble(priceCostTxt.getText());
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

            if(isInHouse){
                int machineId = Integer.parseInt(inOutTxt.getText());
                InHouse part = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.addPart(part);

            } else {
                String companyName = inOutTxt.getText();
                Outsourced part = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.addPart(part);

            }

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

    }
}
