ZApackage controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Part;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> PartIdCol;

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

    }

    @FXML
    void onActionPartsAdd(ActionEvent event) {

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

    }

}
