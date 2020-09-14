package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));

        stage.setScene(new Scene(root, 850, 350));
        stage.show();
    }

    public static void main(String[] args){
        // Sample data for loading into views and testing
        // Comment out if not needed
        InHouse part1 = new InHouse(1, "InHouse Part 1", 1.99, 20, 5, 100, 42 );
        InHouse part2 = new InHouse(2, "InHouse Part 2", 50.00, 3, 1, 12, 30 );
        Outsourced part3 = new Outsourced(3, "Acme Portable Hole", 99.99, 3, 1, 10, "Acme Corporation");

        Product prod1 = new Product(1, "Super Widget", 290.00, 20, 10, 100);
        Product prod2 = new Product(2, "Mini Widget", 49.95, 33, 20, 200);

        prod1.addAssociatedPart(part1);
        prod1.addAssociatedPart(part3);
        prod2.addAssociatedPart(part1);
        prod2.addAssociatedPart(part2);

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addProduct(prod1);
        Inventory.addProduct(prod2);

        launch(args);
    }
}
