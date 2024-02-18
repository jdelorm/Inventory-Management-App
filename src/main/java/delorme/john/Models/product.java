package delorme.john.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author John DeLorme
 * Product class for product setters, getters, and adding and deleting associated parts per UML
 */

public class product {

    private ObservableList<part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public product(int id, String name, double price, int stock, int min, int max) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }

    /**
     * Method for id setter
     * @param id the id to set
     */

    public void setId(int id) {

        this.id = id;

    }

    /**
     * Method for name setter
     * @param name the name to set
     */

    public void setName(String name) {

        this.name = name;

    }

    /**
     * Method for price setter
     * @param price the price to set
     */

    public void setPrice(double price) {

        this.price = price;

    }

    /**
     * Method for stock setter
     * @param stock the stock to set
     */

    public void setStock(int stock) {

        this.stock = stock;

    }

    /**
     * Method for min setter
     * @param min the min to set
     */

    public void setMin(int min) {

        this.min = min;

    }

    /**
     * Method for max setter
     * @param max the max to set
     */

    public void setMax(int max) {

        this.max = max;

    }

    /**
     * Method for id getter
     * @return the id
     */

    public int getId() {

        return id;

    }

    /**
     * Method for name getter
     * @return the name
     */

    public String getName() {

        return name;

    }

    /**
     * Method for price getter
     * @return the price
     */

    public double getPrice() {

        return price;

    }

    /**
     * Method for stock getter
     * @return the stock
     */

    public int getStock() {

        return stock;

    }

    /**
     * Method for min getter
     * @return the min
     */

    public int getMin() {

        return min;

    }

    /**
     * Method for max getter
     * @return the max
     */

    public int getMax() {

        return max;

    }

    /**
     * Method to add associated part
     * @param part the part to add
     */

    public void addAssociatedPart(part part) {

        associatedParts.add(part);

    }

    /**
     * Method to delete associated part
     * @param deletePart the part to delete
     * @return the part
     */

    public boolean deleteAssociatedPart(part deletePart) {

        return associatedParts.remove(deletePart);

    }

    /**
     * Method for all associated parts
     * @return the list part
     */

    public ObservableList<part> getAllAssociatedParts() {

        return associatedParts;

    }
}