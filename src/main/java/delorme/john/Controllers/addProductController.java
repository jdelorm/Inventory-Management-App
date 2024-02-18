package delorme.john.Controllers;

import delorme.john.Models.*;
import javafx.collections.FXCollections;
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

import static delorme.john.Models.inventory.lookupPart;

/**
 * @author John DeLorme
 * This class controls the addproduct scene
 */

public class addProductController implements Initializable {
    public TextField addProductIdText;
    public TextField addProductNameText;
    public TextField addProductInvText;
    public TextField addProductPriceText;
    public TextField addProductMaxText;
    public TextField addProductMinText;
    public TableColumn addProductPartIdColumn;
    public TableColumn addProductNameColumn;
    public TableColumn addProductInventoryColumn;
    public TableColumn addProductPriceColumn;
    public TableColumn addProductRemovePartIdColumn;
    public TableColumn addProductRemoveNameColumn;
    public TableColumn addProductRemoveInventoryColumn;
    public TableColumn addProductRemovePriceColumn;
    public Button addProductAddButton;
    public Button addProductRemove;
    public Button addProductSave;
    public Button addProductCancel;
    public TextField addProductSearch;
    public TableView addProductAssocPartsTable;
    public TableView addProductPartTable;

    /**
     * Method that adds a associated part to a new product the user is adding
     * and displays various error messages
     * @param actionEvent
     */

    public void onAddProductAddButton(ActionEvent actionEvent) {

        part selectedPart = (part) addProductPartTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Select a part to associate with product");
            alert.showAndWait();

        } else {

            assParts.add(selectedPart);
            addProductAssocPartsTable.setItems(assParts);

        }
    }

    /**
     * Method that removes a associated part from a new product that the user is adding
     * and displays various error messages
     * @param actionEvent
     */

    public void onAddProductRemove(ActionEvent actionEvent) {

        part selectedPart = (part) addProductAssocPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Select a associated part to remove");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Remove associated part? Action cannot be undone");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                assParts.remove(selectedPart);
                addProductAssocPartsTable.setItems(assParts);

            }
        }
    }

    /**
     * Method that saves the product and/or associated part that the user inputs
     * and displays various error messages
     * @param actionEvent
     * @throws IOException
     */

    public void onAddProductSave(ActionEvent actionEvent) throws IOException {

        try {

            int id = inventory.getNewProductID();
            String name = addProductNameText.getText();
            int stock = Integer.parseInt(addProductInvText.getText());
            double price = Double.parseDouble(addProductPriceText.getText());
            int min = Integer.parseInt(addProductMinText.getText());
            int max = Integer.parseInt(addProductMaxText.getText());

            if (name.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Name field should not be empty");
                alert.showAndWait();

            } else if (max < min) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Min should be less than Max");
                alert.showAndWait();

            } else if (stock > max || stock < min) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Inv should be between the Min and Max values");
                alert.showAndWait();

            } else {

                product addNewProduct = new product(id, name, price, stock, min, max);

                for (part part : assParts) {

                    addNewProduct.addAssociatedPart(part);

                }

                inventory.addProduct(addNewProduct);

                Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/mainScreen.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 635, 450);
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }
        }

        catch (NumberFormatException i) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("All fields must not be empty and have correct data for field. Example = Price must be a number");
            alert.showAndWait();

        }
    }

    /**
     * Method that cancels any product or associated part the user inputs and returns to the main scene
     * @param actionEvent
     * @throws IOException
     */

    public void onAddProductCancel(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/mainScreen.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 635, 450);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();

    }

    private ObservableList<part> assParts = FXCollections.observableArrayList();

    /**
     * Initializes the addproduct controller and populates the addproduct tables with testdata from the main class
     * Also converts the price double to a string to display the price correctly
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addProductPartTable.setItems(inventory.getAllParts());

        addProductPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProductPriceColumn.setCellFactory(col ->
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

        addProductRemovePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductRemoveNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductRemoveInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductRemovePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProductRemovePriceColumn.setCellFactory(col ->
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
     * method that searches parts by id or name on the addproduct scene
     * @param actionEvent
     */

    public void onAddProductSearch(ActionEvent actionEvent) {

        String q = addProductSearch.getText();
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

        addProductPartTable.setItems(searchedParts);

        if (searchedParts.size() == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No parts found");
            alert.showAndWait();

        }
    }
}
