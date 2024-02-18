package delorme.john.Controllers;

import delorme.john.Models.*;
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
 * @author John DeLorme
 * Class that controls the modifypart scene
 */

public class modifyPartController implements Initializable {

    public RadioButton modifyPartInHouseRadioButton;
    public ToggleGroup partgroup;
    public RadioButton modifyPartOutsourcedRadioButton;
    public Button modifyPartSaveButton;
    public Button modifyPartCancelButton;
    public TextField modifyPartIdText;
    public TextField modifyPartNameText;
    public TextField modifyPartInvText;
    public TextField modifyPartPriceText;
    public TextField modifyPartMaxText;
    public TextField modifyPartMachineIdText;
    public TextField modifyPartMinText;
    public Label modifyPartIdLabel;
    public Label modifyPartNameLabel;
    public Label modifyPartInvLabel;
    public Label modifyPartPriceLabel;
    public Label modifyPartMaxLabel;
    public Label modifyPartMachineIdLabel;
    public Label modifyPartMinLabel;
    private part selectedPart;

    /**
     * Method that sets the text on the modify part scene from company name to machine id
     * when the inhouse radio button is selected
     * @param actionEvent
     */

    public void onModifyPartInHouseRadioButton(ActionEvent actionEvent) {

        modifyPartMachineIdLabel.setText("Machine ID");

    }

    /**
     * Method that sets the text on the modify part scene from machine id to company text
     * when the outsourced radio button is selected
     * @param actionEvent
     */

    public void onModifyPartOutsourcedRadioButton(ActionEvent actionEvent) {

        modifyPartMachineIdLabel.setText("Company Name");

    }

    /**
     * Method that exits the modifypart scene and loads the main scene when the cancel button is clicked
     * @param actionEvent
     * @throws IOException
     */

    public void onModifyPartCancel(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/delorme/john/mainScreen.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 635, 450);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Method that saves the user inputted changes to the selected part
     * and transfers that data back to the main scene tables
     * Also displays various error messages
     * @param actionEvent
     * @throws IOException
     */

    public void onModifyPartSaveButton(ActionEvent actionEvent) throws IOException {

        try {

            int id = Integer.parseInt(modifyPartIdText.getText());
            String name = modifyPartNameText.getText();
            int stock = Integer.parseInt(modifyPartInvText.getText());
            double price = Double.parseDouble(modifyPartPriceText.getText());
            int min = Integer.parseInt(modifyPartMinText.getText());
            int max = Integer.parseInt(modifyPartMaxText.getText());

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

                if (modifyPartInHouseRadioButton.isSelected()) {

                    int machineID = Integer.parseInt(modifyPartMachineIdText.getText());
                    inventory.updatePart(id - 1, new inHouse(id, name, price, stock, min, max, machineID));
                    inventory.deletePart(selectedPart);

                } else if (modifyPartOutsourcedRadioButton.isSelected()) {

                    String companyName = modifyPartMachineIdText.getText();
                    inventory.updatePart(id - 1, new outsourced(id, name, price, stock, min, max, companyName));
                    inventory.deletePart(selectedPart);

                }

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
            alert.setContentText("All fields must not be empty and have correct data for field. Example = Machine ID must be a number");
            alert.showAndWait();

        }
    }

    /**
     * Initializes the modifypart controller and populates the modifypart text fields
     * and changes the modifypart text from machine id or company name depending on which
     * radio button is selected
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedPart = mainScreenController.getPartToModify();

        if (selectedPart instanceof inHouse) {

            modifyPartInHouseRadioButton.setSelected(true);
            modifyPartMachineIdLabel.setText("Machine ID");
            modifyPartMachineIdText.setText(String.valueOf(((inHouse) selectedPart).getMachineID()));

        }

        if (selectedPart instanceof outsourced) {

            modifyPartOutsourcedRadioButton.setSelected(true);
            modifyPartMachineIdLabel.setText("Company Name");
            modifyPartMachineIdText.setText(((outsourced) selectedPart).getCompanyName());

        }

        modifyPartIdText.setText(String.valueOf(selectedPart.getId()));
        modifyPartNameText.setText(selectedPart.getName());
        modifyPartInvText.setText(String.valueOf(selectedPart.getStock()));
        modifyPartPriceText.setText(String.valueOf(selectedPart.getPrice()));
        modifyPartMaxText.setText(String.valueOf(selectedPart.getMax()));
        modifyPartMinText.setText(String.valueOf(selectedPart.getMin()));

    }
}
