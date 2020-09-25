package controller;

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
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;


public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;
    private boolean isInHouse;
    private int partIndex;

    @FXML
    private RadioButton inHouseRdoBtn;

    @FXML
    private RadioButton outsourcedRdoBtn;

    @FXML
    private Label inOutLabel;

    @FXML
    private TextField IdTxt;

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

    public void sendPart(Part part) {

        if(part instanceof InHouse) {
            isInHouse = true;
            inHouseRdoBtn.setSelected(true);
            inOutLabel.setText("Machine ID");
            inOutTxt.setText(String.valueOf(((InHouse)part).getMachineId()));
        } else {
            isInHouse = false;
            outsourcedRdoBtn.setSelected(true);
            inOutLabel.setText("Company Name");
            inOutTxt.setText(((Outsourced)part).getCompanyName());
        }

        // Grab the index from the Inventory ObservableList
        partIndex = Inventory.getAllParts().indexOf(part);

        IdTxt.setText(String.valueOf(part.getId()));
        nameTxt.setText(part.getName());
        invTxt.setText(String.valueOf(part.getStock()));
        priceCostTxt.setText(String.valueOf(part.getPrice()));
        minTxt.setText(String.valueOf(part.getMin()));
        maxTxt.setText(String.valueOf(part.getMax()));

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
            int id = Integer.parseInt(IdTxt.getText());
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
                Inventory.updatePart(partIndex, part);

            } else {
                String companyName = inOutTxt.getText();
                Outsourced part = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.updatePart(partIndex, part);

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
        // No code here (as of yet)
    }

}
