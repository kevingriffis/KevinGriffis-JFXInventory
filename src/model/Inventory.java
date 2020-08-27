package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId){
        for (Part part : Inventory.getAllParts()){
            if (part.getId() == partId){
                return part;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName){
        for (Part part : Inventory.getAllParts()){
            if (part.getName() == partName){
                return part;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productId){
        for (Product product : Inventory.getAllProducts()){
            if (product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        for (Product product : Inventory.getAllProducts()){
            if (product.getName() == productName){
                return product;
            }
        }
        return null;
    }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }

    public static boolean deletePart(Part selectedPart){
        for (Part part : Inventory.getAllParts()){
            if (part.getId() == selectedPart.getId()){
                return getAllParts().remove(selectedPart);
            }
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct){
        for (Product product : Inventory.getAllProducts()){
            if (product.getId() == selectedProduct.getId()){
                return getAllProducts().remove(selectedProduct);
            }
        }
        return false;
    }


}
