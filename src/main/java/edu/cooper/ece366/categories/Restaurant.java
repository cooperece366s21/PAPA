package edu.cooper.ece366.categories;

import edu.cooper.ece366.model.Address;
import edu.cooper.ece366.model.Cuisine;
import edu.cooper.ece366.model.OperatingHours;
import edu.cooper.ece366.model.PhoneNumber;

import java.util.ArrayList;

public class Restaurant {
    private final String id;                // yelp id for the restaurant
    private final String name;              // name of the restaurant
    private String price;                   // price range $, $$ ,$$$, $$$$
    private double rating;                   // rating score out of 5
    private Cuisine cuisine;                // type of cuisine
    private Address address;                // address
    private PhoneNumber phoneNumber;        // phone number
    private OperatingHours operatingHours; // operating hours

    public Restaurant(String id, String name, String price, double rating,
                      Cuisine cuisine, Address address,
                      PhoneNumber phoneNumber, OperatingHours operatingHours){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.cuisine = cuisine;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.operatingHours = operatingHours;
    }

    public String getId(){return id;}
    public  String getName(){return name;}
    public String getPrice(){return price;}
    public double getRating(){return rating;}
    public Cuisine getCuisine(){return cuisine;}
    public Address getAddress(){return address;}
    public PhoneNumber getPhoneNumber(){return phoneNumber;}
    public OperatingHours getOperatingHours(){return operatingHours;}

    public String toString(){
        String returnStr = name + System.lineSeparator() + price + "\t\t";
        ArrayList<String> cuisines = cuisine.getCuisineStr(cuisine.getCuisine());
        for(String s : cuisines){
            returnStr = returnStr + s + ',';
        }
        returnStr = returnStr.substring(0,returnStr.length()-1);
        returnStr += System.lineSeparator();

        returnStr += address.toString() + System.lineSeparator();
        returnStr += phoneNumber.toString() + System.lineSeparator();

        ArrayList<String> opHours = operatingHours.pString();
        for(String str : opHours) {
            returnStr += str + System.lineSeparator();
        }

        return returnStr;
    }




}
