package delorme.john;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import delorme.john.Models.inHouse;
import delorme.john.Models.inventory;
import delorme.john.Models.outsourced;
import delorme.john.Models.part;
import delorme.john.Models.product;

/**
 * @author John DeLorme
 * starting point of the program and includes methods for testing by populating scene tables
 */

public class main extends Application {

    /**
     * method to launch mainScreen scene
     * @param stage
     * @throws IOException
     */

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("mainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 635, 450);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * test data method to populate scene tables per kinkead webinar
     */

    private static void addTestData() {

        int partId = inventory.getNewPartID();
        part alternator = new inHouse(partId, "Alternator", 450.00, 3, 1, 10, 101);
        inventory.addPart(alternator);
        partId = inventory.getNewPartID();
        part starter = new inHouse(partId, "Starter", 200.00, 4, 1, 10, 102);
        inventory.addPart(starter);
        partId = inventory.getNewPartID();
        part battery = new inHouse(partId, "Battery", 150.00, 5, 1, 10, 103);
        inventory.addPart(battery);

        partId = inventory.getNewPartID();
        part cylinderHead = new outsourced(partId, "Cylinder Head", 1500.00, 2, 2, 12, "Hot Rod Customs");
        inventory.addPart(cylinderHead);
        partId = inventory.getNewPartID();
        part camshaft = new outsourced(partId, "Camshaft", 800.00, 3, 1, 10, "Lake City Engine");
        inventory.addPart(camshaft);
        partId = inventory.getNewPartID();
        part crankshaft = new outsourced(partId, "Crankshaft", 1200.00, 2, 1, 10, "Lake City Engine");
        inventory.addPart(crankshaft);

        int productId = inventory.getNewProductID();
        product engine1 = new product(productId, "5.0L Crate Engine", 10000.00, 5, 1, 10);
        inventory.addProduct(engine1);

        engine1.addAssociatedPart(alternator);
        engine1.addAssociatedPart(starter);
        engine1.addAssociatedPart(battery);
        engine1.addAssociatedPart(cylinderHead);
        engine1.addAssociatedPart(camshaft);
        engine1.addAssociatedPart(crankshaft);

        productId = inventory.getNewProductID();
        product engine2 = new product(productId, "4.6L Crate Engine", 8000.00, 5, 1, 10);
        inventory.addProduct(engine2);
        productId = inventory.getNewProductID();
        product engine3 = new product(productId, "7.5L Crate Engine", 12000.00, 3, 1, 10);
        inventory.addProduct(engine3);
        productId = inventory.getNewProductID();
        product engine4 = new product(productId, "3.5L Crate Engine", 7000.00, 3, 1, 10);
        inventory.addProduct(engine4);
        productId = inventory.getNewProductID();
        product engine5 = new product(productId, "4.0L Crate Engine", 7500.00, 4, 1, 10);
        inventory.addProduct(engine5);
        productId = inventory.getNewProductID();
        product engine6 = new product(productId, "2.0L Crate Engine", 5000, 3, 1, 10);
        inventory.addProduct(engine6);

    }

    /**
     * Program main starting point
     * @param args
     */

    public static void main(String[] args) {

        addTestData();

        launch();

    }
}