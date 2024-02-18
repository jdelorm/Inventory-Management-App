package delorme.john.Controllers;

import delorme.john.Models.inHouse;
import delorme.john.Models.inventory;
import delorme.john.Models.outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author John Delorme
 * This class controlls the addpart scene
 */

public class addPartController implements Initializable {
    public RadioButton addPartInHouseRadioButton;
    public RadioButton addPartOutsourcedRadioButton;
    public Button addPartSaveButton;
    public Button addPartCancelButton;
    public ToggleGroup partgroup;
    public TextField addPartIdText;
    public TextField addPartNameText;
    public TextField addPartInvText;
    public TextField addPartPriceText;
    public TextField addPartMaxText;
    public TextField addPartMachineIdText;
    public TextField addPartMinText;
    public Label addPartIdLabel;
    public Label addPartNameLabel;
    public Label addPartInvLabel;
    public Label addPartPriceLabel;
    public Label addPartMaxLabel;
    public Label addPartMachineIdLabel;
    public Label addPartMinLabel;

    /**
     * This method sets the cancel button to return to the main scene when clicked
     * @param actionEvent
     * @throws IOException
     */

    public void onAddPartCancel(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/mainScreen.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 635, 450);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Method to set addpart form scene text from company name to machine id
     * when the inhouse radio button is selected
     * @param actionEvent
     */

    public void onAddPartInHouseRadioButton(ActionEvent actionEvent) {

        addPartMachineIdLabel.setText("Machine ID");

    }

    /**
     * Method to set addpart form scene text from machine id to company name
     * when the outsourced radio button is selected
     * @param actionEvent
     */

    public void onAddPartOutsourcedRadioButton(ActionEvent actionEvent) {

        addPartMachineIdLabel.setText("Company Name");

    }

    /**
     * Method that saves the user input data on addpart form and adds it to the main scene table
     * and displays various error messages
     * @param actionEvent
     * @throws IOException
     */

    public void onAddPartSaveButton(ActionEvent actionEvent) throws IOException {

        try {

            int id = inventory.getNewPartID();
            String name = addPartNameText.getText();
            int stock = Integer.parseInt(addPartInvText.getText());
            double price = Double.parseDouble(addPartPriceText.getText());
            int min = Integer.parseInt(addPartMinText.getText());
            int max = Integer.parseInt(addPartMaxText.getText());

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

                if (addPartInHouseRadioButton.isSelected()) {

                    int machineID = Integer.parseInt(addPartMachineIdText.getText());
                    inventory.addPart(new inHouse(id, name, price, stock, min, max, machineID));

                } else if (addPartOutsourcedRadioButton.isSelected()) {

                    String companyName = addPartMachineIdText.getText();
                    inventory.addPart(new outsourced(id, name, price, stock, min, max, companyName));

                }

                Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/mainScreen.fxml"));
                Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
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
     * Initializes the addpart controller
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
