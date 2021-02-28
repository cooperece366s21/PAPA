package edu.cooper.ece366.categories;

import edu.cooper.ece366.model.Address;
import edu.cooper.ece366.model.Cuisine;
import edu.cooper.ece366.model.PhoneNumber;

import javax.xml.stream.Location;

public class Restaurant {
    private final String id;             // yelp id for the restaurant
    private final String name;            // name of the restaurant
    private String price;              // price range $, $$ ,$$$, $$$$
    private float rating;           // rating score out of 5
    private Cuisine cuisine;        // type of cuisine
    private Location location;      // precise location
    private Address address;        // address
    private PhoneNumber phoneNumber;// phone number

    public Restaurant(String id, String name, String price, float rating,
                      Cuisine cuisine, Location location, Address address,
                      PhoneNumber phoneNumber){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.cuisine = cuisine;
        this.location = location;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }




}
