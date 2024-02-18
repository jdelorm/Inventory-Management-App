package delorme.john.Models;

/**
 * @author John DeLorme
 * InHouse class for parts setters and getters per UML
 */

public class inHouse extends part {
    private int machineID;

    public inHouse(int id, String name, double price, int stock, int min, int max, int machineID) {

        /**
         * Inheritance Example
         */

        super(id, name, price, stock, min, max);
        this.machineID = machineID;

    }

    /**
     * Method for machine ID setter
     * @param machineID the machineID to set
     */

    public void setMachineID(int machineID) {

        this.machineID = machineID;

    }

    /**
     * Method for machineID getter
     * @return the machineID
     */

    public int getMachineID() {

        return machineID;

    }
}