package edu.cooper.ece366.model;

/**
 * A contact is a name and address.
 * <p>
 * For the purpose of this example, I have simplified matters
 * a bit by making both of these components simple strings.
 * In practice, we would expect Address, at least, to be a
 * more structured type.
 *
 * @author zeil
 *
 */

// cs.odu.edu/~zeil/cs350/s16/Public/adtTesting/Address.java.html

public class Address implements Cloneable {

    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    /**
     * Create an address with all empty fields.
     *
     */
    public Address ()
    {
        name = "";
        streetAddress = "";
        city = "";
        state = "";
        zipCode = "";
    }

    /**
     * Create an address.
     */
    public Address (String nm, String streetAddr, String city,
                    String state, String zip)
    {
        name = nm;
        streetAddress = streetAddr;
        this.city = city;
        this.state = state;
        zipCode = zip;
    }



    /**
     * @return the theName
     */
    public String getName() {
        return name;
    }

    /**
     * @param theName the theName to set
     */
    public void setName(String theName) {
        this.name = theName;
    }

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * True if the names and addresses are equal
     */
    public boolean equals (Object right)
    {
        Address r = (Address)right;
        return name.equals(r.name)
                && streetAddress.equals(r.streetAddress)
                && city.equals(r.city)
                && state.equals(r.state)
                && zipCode.equals(r.zipCode);
    }

    public int hashCode ()
    {
        return name.hashCode() + 3 * streetAddress.hashCode()
                + 5 * city.hashCode()
                + 7 * state.hashCode()
                + 11 * zipCode.hashCode();
    }

    public String toString()
    {
        return name + ": " + streetAddress + ": "
                + city + ", " + state + " " + zipCode;
    }

    public Object clone()
    {
        return new Address(name, streetAddress, city,
                state, zipCode);
    }

}