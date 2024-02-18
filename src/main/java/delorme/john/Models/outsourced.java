package delorme.john.Models;

/**
 * @author John DeLorme
 * Outsourced class for parts setters and getters per UML
 */

public class outsourced extends part {

    /**
     * Had error because companyName was an int when it needed to be a String
     */

    private String companyName;

    public outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {

        /**
         * Inheritance Example
         */

        super(id, name, price, stock, min, max);
        this.companyName = companyName;

    }

    /**
     * Method for companyName setter
     * @param companyName the companyName to set
     */

    public void setCompanyName(String companyName) {

        this.companyName = companyName;

    }

    /**
     * Method for companyName getter
     * @return the companyName
     */

    public String getCompanyName() {

        return companyName;

    }
}