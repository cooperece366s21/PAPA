package edu.cooper.ece366.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cuisine{

    public enum cuisineType{
        CHINESE("Chinese");
        private final String cuisineT;

        private cuisineType(String s){
            cuisineT = s;
        }

        public String toString(){
            return this.cuisineT;
        }
    }

    private ArrayList<cuisineType> cuisine;


    public Cuisine() {}

    public Cuisine(cuisineType[] cuisineIn){
        this.cuisine.addAll(Arrays.asList(cuisineIn));
    }

    private ArrayList<String> toString(List<cuisineType> cuisine){
        ArrayList<String> cuisineString = new ArrayList<String>();
        for(cuisineType c : cuisine){
            cuisineString.add(c.toString());
        }
        return  cuisineString;
    }

    public ArrayList<String> getCuisine() {
        return toString(cuisine);
    }
}
