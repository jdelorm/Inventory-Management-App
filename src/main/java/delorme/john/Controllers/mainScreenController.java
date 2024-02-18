package delorme.john.Controllers;

import delorme.john.Models.part;
import delorme.john.Models.product;
import delorme.john.Models.inventory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static delorme.john.Models.inventory.*;

/**
 * @author John DeLorme
 *
 * FUTURE ENHANCEMENT - As this program gets larger and starts to hold
 * many more parts and products, I will have to implement a better search
 * function. One that doesn't just run through all the items in the list in order,
 * or better yet, a database.
 *
 * Class that controls the mainscreen scene
 */

public class mainScreenController implements Initializable {
    public TableView mainPartsTable;
    public TableColumn mainPartIdColumn;
    public TableColumn mainPartNameColumn;
    public TableColumn mainInventoryPartColumn;
    public TableColumn mainPricePartColumn;
    public TableView mainProductsTable;
    public TableColumn mainProductIdColumn;
    public TableColumn mainProductNameColumn;
    public TableColumn mainInventoryProductColumn;
    public TableColumn mainPriceProductColumn;
    public Button mainAddPart;
    public Button mainAddProduct;
    public Button mainExit;
    public Button mainModifyProduct;
    public Button mainDeleteProduct;
    public Button mainModifyPart;
    public Button mainDeletePart;
    public TextField mainPartSearch;
    public TextField mainProductSearch;
    private static part partToModify;
    private static product productToModify;

    /**
     * Method getter for partToModify
     * @return
     */

    public static part getPartToModify() {

        return partToModify;

    }

    /**
     * Method getter for getProductToModify
     * @return
     */

    public static product getProductToModify() {

        return productToModify;

    }

    /**
     * method that launches the addpart scene after pressing the add button
     * @param actionEvent
     * @throws IOException
     */

    public void onMainAddPart(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/addPart.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * method that launches the addproduct scene after the add button is pressed
     * @param actionEvent
     * @throws IOException
     */

    public void onMainAddProduct(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/addProduct.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 601, 487);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * mehtod that exits the program from the mainscreen after exit button is pressed
     * @param actionEvent
     */

    public void onMainExit(ActionEvent actionEvent) {

        System.exit(0);

    }

    /**
     * method that launches the modifyproduct scene after the modify button is pressed
     * and displays various error messages
     * @param actionEvent
     * @throws IOException
     */

    public void onMainModifyProduct(ActionEvent actionEvent) throws IOException {

        productToModify = (product)mainProductsTable.getSelectionModel().getSelectedItem();

        if (productToModify == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No product selected to modify");
            alert.showAndWait();

        } else {

            Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/modifyProduct.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 601, 487);
            stage.setTitle("Modify Product");
            stage.setScene(scene);
            stage.show();

        }
    }

    /**
     * delete method for mainscreen selected product
     * and displays various error messages
     * @param actionEvent
     */

    public void onMainDeleteProduct(ActionEvent actionEvent) {

        delorme.john.Models.product selectedProduct = (product) mainProductsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Select a product to delete");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete product? Action cannot be undone");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<part> assPartsCheck = selectedProduct.getAllAssociatedParts();

                if (assPartsCheck.size() >= 1) {

                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setContentText("Cannot delete product with an associated part");
                    alert.showAndWait();

                } else {

                inventory.deleteProduct(selectedProduct);

                }
            }
        }
    }

    /**
     * method that launches the modifypart scene after the modify button is pressed
     * and displays various error messages
     * @param actionEvent
     * @throws IOException
     */

    public void onMainModifyPart(ActionEvent actionEvent) throws IOException {

        partToModify = (part)mainPartsTable.getSelectionModel().getSelectedItem();

        if (partToModify == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No part selected to modify");
            alert.showAndWait();

        } else {

            Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/modifyPart.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Modify Part");
            stage.setScene(scene);
            stage.show();

        }
    }

    /**
     * delete method for mainscreen selected part
     * and displays various error messages
     * @param actionEvent
     */

    public void onMainDeletePart(ActionEvent actionEvent) {

        delorme.john.Models.part selectedPart = (part) mainPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Select a part to delete");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete part? Action cannot be undone");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                inventory.deletePart(selectedPart);

            }
        }
    }

    /**
     * Initializes the mainscreen controller and populates the mainscreen tables and
     * converts the price double to a string to display the price correctly
     *
     * RUNTIME ERROR - Had major issue whenever trying to compile while building data for tables.
     * Kept getting runtime error 'module java.fx cannot access Models class'
     * Ended up being that I had to add 'opens and exports' info to module-info.java file for model class
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainPartsTable.setItems(inventory.getAllParts());
        mainProductsTable.setItems(inventory.getAllProducts());

        mainPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainInventoryPartColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPricePartColumn.setCellValueFactory(new PropertyValueFactory<>( "price"));

        mainPricePartColumn.setCellFactory(col ->
                new TableCell<product, Number>() {

                    @Override
                    public void updateItem(Number price, boolean empty) {

                        super.updateItem(price, empty);

                        if (empty) {

                            setText(null);

                        } else {

                            setText(String.format("US$%.2f", price.doubleValue()));

                        }
                    }
                });

        mainProductIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainInventoryProductColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPriceProductColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        mainPriceProductColumn.setCellFactory(col ->
                new TableCell<product, Number>() {

                    @Override
                    public void updateItem(Number price, boolean empty) {

                        super.updateItem(price, empty);

                        if (empty) {

                            setText(null);

                        } else {

                            setText(String.format("US$%.2f", price.doubleValue()));

                        }
                    }
                });
    }

    /**
     * Search method for mainscreen parts by name or ID
     * per kinkead webinars
     * @param actionEvent
     */

    public void onMainPartSearch(ActionEvent actionEvent) {

        String q = mainPartSearch.getText();
        ObservableList<part> searchedParts = lookupPart(q);

        if (searchedParts.size() == 0) {

            try {

                int partId = Integer.parseInt(q);
                part i = lookupPart(partId);
                if (i != null)
                    searchedParts.add(i);
            }

            catch (NumberFormatException i) {

            }
        }

        mainPartsTable.setItems(searchedParts);

        if (searchedParts.size() == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No parts found");
            alert.showAndWait();

        }
    }

    /**
     * Search method for mainscreen products by name or ID
     * per kinkead webinars
     * @param actionEvent
     */

    public void onMainProductSearch(ActionEvent actionEvent) {

        String q = mainProductSearch.getText();
        ObservableList<product> searchedProducts = lookupProduct(q);

        if (searchedProducts.size() == 0) {

            try {

                int productId = Integer.parseInt(q);
                product i = lookupProduct(productId);
                if (i != null)
                    searchedProducts.add(i);

            }

            catch (NumberFormatException i) {

            }
        }

        mainProductsTable.setItems(searchedProducts);

        if (searchedProducts.size() == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No products found");
            alert.showAndWait();

        }
    }
}