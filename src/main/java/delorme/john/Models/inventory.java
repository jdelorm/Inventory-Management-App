package delorme.john.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author John DeLorme
 * Inventory class to add, lookup, update, and delete parts and products per UML
 */

public class inventory {

    private static ObservableList<part> allParts = FXCollections.observableArrayList();
    private static ObservableList<product> allProducts = FXCollections.observableArrayList();

    /**
     * Method to set part ID
     * creates part ID number
     */

    private static int partID = 1;

    /**
     * Method to set product ID
     * creates product ID number
     */

    private static int productID = 1000;

    /**
     * Method to generate new part ID
     * @return generated new part ID
     */

    public static int getNewPartID() {

        return partID++;

    }

    /**
     * Method to generate new product ID
     * @return generated new product ID
     */

    public static int getNewProductID() {

        return productID++;

    }

    /**
     * Method to add new parts
     * @param newPart adds new parts to inventory
     */

    public static void addPart(part newPart) {

        allParts.add(newPart);

    }

    /**
     * Method to add new products
     * @param newProduct adds new products to inventory
     */

    public static void addProduct(product newProduct) {

        allProducts.add(newProduct);

    }

    /**
     * Method to search part by ID(int)
     * Similar for loop as name search but tweaked for int variables
     * @param partID part ID
     * @return part if found otherwise null
     */

    public static part lookupPart(int partID) {

        part partIDSearch = null;

        for (delorme.john.Models.part part : allParts) {

            if (part.getId() == partID) {

                partIDSearch = part;

            }
        }

        return partIDSearch;
    }

    /**
     * Method to search product by ID(int)
     * Similar for loop as name search but tweaked for int variables
     * @param productID product ID
     * @return product if found otherwise null
     */

    public static product lookupProduct(int productID) {

        product productIDSearch = null;

        for (delorme.john.Models.product product : allProducts) {

            if (product.getId() == productID) {
                productIDSearch = product;

            }
        }

        return productIDSearch;
    }

    /**
     * Method to search part by name(String)
     * for loop design per instructor Kinkead webinar series
     * Forgot method ending curly bracket on this method creating errors all the way down the page that
     * took hours to find, thinking I had problems linking methods to the product list/array
     * @param partName part name
     * @return part if found otherwise null
     */

    public static ObservableList<part> lookupPart(String partName) {

        ObservableList<part> partNameSearch = FXCollections.observableArrayList();

        for (delorme.john.Models.part part : allParts) {

            if (part.getName().contains(partName)) {

                partNameSearch.add(part);

            }
        }

        return partNameSearch;

    }

    /**
     * Method to search product by name(String)
     * for loop design per instructor Kinkead webinar series
     * @param productName product name
     * @return product if found otherwise null
     */

    public static ObservableList<product> lookupProduct(String productName) {

        ObservableList<product> productNameSearch = FXCollections.observableArrayList();

        for (delorme.john.Models.product product : allProducts) {

            if (product.getName().contains(productName)) {

                productNameSearch.add(product);

            }
        }

        return productNameSearch;

    }

    /**
     * Method to update part
     * @param index part index
     * @param selectedPart part to update
     */

    public static void updatePart(int index, part selectedPart) {

        allParts.set(index, selectedPart);

    }

    /**
     * Method to update product
     * @param index product index
     * @param selectedProduct product to update
     */

    public static void updateProduct(int index, product selectedProduct) {

        allProducts.set(index, selectedProduct);

    }

    /**
     * Method to delete part
     * @param selectedPart part to delete
     * @return boolean if part removed
     */

    public static boolean deletePart(part selectedPart) {

        if (allParts.contains(selectedPart)) {

            allParts.remove(selectedPart);
            return true;

        } else {

            return false;

        }
    }

    /**
     * Method to delete product
     * @param selectedProduct product to delete
     * @return boolean if product removed
     */

    public static boolean deleteProduct(product selectedProduct) {

        if (allProducts.contains(selectedProduct)) {

            allProducts.remove(selectedProduct);
            return true;

        } else {

            return false;

        }
    }

    /**
     * Method to return all parts
     * @return all parts
     */

    public static ObservableList<part> getAllParts() {

        return allParts;

    }

    /**
     * Method to return all products
     * @return all products
     */

    public static ObservableList<product> getAllProducts() {

        return allProducts;

    }
}