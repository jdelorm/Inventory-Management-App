package delorme.john.Controllers;

import delorme.john.Models.inventory;
import delorme.john.Models.part;
import delorme.john.Models.product;
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
 * Class that controlls the modifyproduct scene
 */

public class modifyProductController implements Initializable {
    public TextField modifyProductIdText;
    public TextField modifyProductNameText;
    public TextField modifyProductInvText;
    public TextField modifyProductPriceText;
    public TextField modifyProductMaxText;
    public TextField modifyProductMinText;
    public TableColumn modifyProductPartIdColumn;
    public TableColumn modifyProductNameColumn;
    public TableColumn modifyProductInventoryColumn;
    public TableColumn modifyProductPriceColumn;
    public TableColumn modifyProductRemovePartIdColumn;
    public TableColumn modifyProductRemoveNameColumn;
    public TableColumn modifyProductRemoveInventoryColumn;
    public TableColumn modifyProductRemovePriceColumn;
    public Button modifyProductAddButton;
    public Button modifyProductRemoveButton;
    public Button modifyProductSaveButton;
    public Button modifyProductCancelButton;
    public TextField modifyProductSearch;
    public TableView modifyProductPartTable;
    public TableView modifyProductAssocPartTable;

    /**
     * Method that associates a product with a part that the user has selected from the mainscene when clicked
     * and displays various error messages
     * @param actionEvent
     */

    public void onModifyProductButton(ActionEvent actionEvent) {

        part selectedPart = (part) modifyProductPartTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Select a part to associate");
            alert.showAndWait();

        } else {

            assParts.add(selectedPart);
            modifyProductAssocPartTable.setItems(assParts);

        }
    }

    /**
     * Method that removes a associated part from a product that the user has
     * selected from the mainscene and displays various error messages
     * @param actionEvent
     */

    public void onModifyProductRemoveButton(ActionEvent actionEvent) {

        part selectedPart = (part) modifyProductAssocPartTable.getSelectionModel().getSelectedItem();

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
                modifyProductAssocPartTable.setItems(assParts);

            }
        }

    }

    /**
     * Saves the product that is being modified by the user and displays various error messages
     *
     * RUNTIME ERROR - kept getting a runtime error for index out of bounds for length
     * After much trial and error I found it was being caused by my productID being used
     * as my indexID and since my productID was 1000+ there was no product at that index.
     * I solved it by referencing my index - 1000
     *
     * @param actionEvent
     * @throws IOException
     */

    public void onModifyProductSaveButton(ActionEvent actionEvent) throws IOException {

        try {

            int id = Integer.parseInt(modifyProductIdText.getText());
            String name = modifyProductNameText.getText();
            int stock = Integer.parseInt(modifyProductInvText.getText());
            double price = Double.parseDouble(modifyProductPriceText.getText());
            int min = Integer.parseInt(modifyProductMinText.getText());
            int max = Integer.parseInt(modifyProductMaxText.getText());

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

                inventory.updateProduct(id - 1000, addNewProduct);
                inventory.deleteProduct(selectedProduct);

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
     * Method that closes the modifyproduct scene and opens the mainscene when the cancel button is clicked
     * @param actionEvent
     * @throws IOException
     */

    public void onModifyProductCancelButton(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/mainScreen.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 635, 450);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();

    }

    private ObservableList<part> assParts = FXCollections.observableArrayList();
    product selectedProduct;

    /**
     * Initializes the modifyproducts controller and populates the parts and associated parts tables
     * and converts price to string to display price correctly
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedProduct = mainScreenController.getProductToModify();
        assParts = selectedProduct.getAllAssociatedParts();

        modifyProductPartTable.setItems(inventory.getAllParts());
        modifyProductAssocPartTable.setItems(assParts);

        modifyProductPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyProductPriceColumn.setCellFactory(col ->
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

        modifyProductRemovePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductRemoveNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductRemoveInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductRemovePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyProductRemovePriceColumn.setCellFactory(col ->
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

        modifyProductIdText.setText(String.valueOf(selectedProduct.getId()));
        modifyProductNameText.setText(selectedProduct.getName());
        modifyProductInvText.setText(String.valueOf(selectedProduct.getStock()));
        modifyProductPriceText.setText(String.valueOf(selectedProduct.getPrice()));
        modifyProductMaxText.setText(String.valueOf(selectedProduct.getMax()));
        modifyProductMinText.setText(String.valueOf(selectedProduct.getMin()));

    }

    /**
     * Method that searches the parts table by id or name and displays results or error messages
     * @param actionEvent
     */

    public void onModifyProductSearch(ActionEvent actionEvent) {

        String q = modifyProductSearch.getText();
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

        modifyProductPartTable.setItems(searchedParts);

        if (searchedParts.size() == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No parts found");
            alert.showAndWait();

        }
    }
}
