package edu.cooper.ece366.model;

import java.util.ArrayList;
import java.util.List;

public class Cuisine{

    public enum cuisineType{
        chinese("Chinese"),
        newamerican("American (New)"),
        french("French"),
        wine_bars("Wine Bars"),
        japanese("Japanese"),
        korean("Korean"),
        thai("Thai"),
        shanghainese("Shanghainese"),
        dim_sum("Dim Sum"),
        cantonese("Cantonese"),
        bakeries("Bakeries");
        private final String cuisineT;

        private cuisineType(String s){
            cuisineT = s;
        }

        public String toString(){
            return this.cuisineT;
        }
    }

    private ArrayList<cuisineType> cuisine;
    private ArrayList<String> cuisineStr;

    public Cuisine() { this.cuisine = new ArrayList<>();}

    public void add(cuisineType cuisineIn){
        this.cuisine.add(cuisineIn);
    }

    public ArrayList<String> getCuisineStr(List<cuisineType> cuisine){
        ArrayList<String> cuisineString = new ArrayList<String>();
        for(cuisineType c : cuisine){
            cuisineString.add(c.toString());
        }
        return  cuisineString;
    }

    public ArrayList<cuisineType> getCuisine() {
        return cuisine;
    }

}
