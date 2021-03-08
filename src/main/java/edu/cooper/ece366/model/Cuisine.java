package edu.cooper.ece366.model;

import edu.cooper.ece366.categories.Restaurant;

import java.util.*;

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
        halal("Halal"),
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

    public Cuisine(List<cuisineType> cuisine){ this.cuisine = (ArrayList<cuisineType>) cuisine;}

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

    public static class CuisineBuilder{
        private ArrayList<cuisineType> cuisine;

        public List<cuisineType> cuisine() {
            if (this.cuisine == null) {
                this.cuisine = new ArrayList<cuisineType>();
            }
            return cuisine;
        }

        public CuisineBuilder cuisine(List<? extends cuisineType> cuisine) {
            return cuisine((List<? extends cuisineType>) cuisine);
        }

        public CuisineBuilder cuisine(Collection<? extends cuisineType> cuisine) {
            if (cuisine == null) {
                throw new NullPointerException("cuisine");
            }
            for (cuisineType item : cuisine) {
                if (item == null) {
                    throw new NullPointerException("cuisine: null item");
                }
            }
            this.cuisine = new ArrayList<cuisineType>(cuisine);
            return this;
        }

        public CuisineBuilder cuisine(Iterable<? extends cuisineType> cuisine) {
            if (cuisine == null) {
                throw new NullPointerException("cuisine");
            }
            if (cuisine instanceof Collection) {
                return cuisine((Collection<? extends cuisineType>) cuisine);
            }
            return cuisine((Iterable<? extends cuisineType>) cuisine.iterator());
        }

        public CuisineBuilder cuisine(Iterator<? extends cuisineType> cuisine) {
            if (cuisine == null) {
                throw new NullPointerException("cuisine");
            }
            this.cuisine = new ArrayList<cuisineType>();
            while (cuisine.hasNext()) {
                cuisineType item = cuisine.next();
                if (item == null) {
                    throw new NullPointerException("cuisine: null item");
                }
                this.cuisine.add(item);
            }
            return this;
        }

        @SafeVarargs
        @SuppressWarnings("varargs")
        public final CuisineBuilder cuisine(cuisineType... cuisine) {
            if (cuisine == null) {
                throw new NullPointerException("cuisine");
            }
            return cuisine(Arrays.asList(cuisine));
        }

        public CuisineBuilder addCuisine(cuisineType c) {
            if (c == null) {
                throw new NullPointerException("cuisine");
            }
            if (this.cuisine == null) {
                this.cuisine = new ArrayList<cuisineType>();
            }
            cuisine.add(c);
            return this;
        }
        public Cuisine build(){
            List<cuisineType> _cuisine;
            if(cuisine == null){
                _cuisine = new ArrayList<cuisineType>();
                return new Cuisine(_cuisine);
            } else if(cuisine != null){
                _cuisine = cuisine;
                return new Cuisine(_cuisine);
            }
            //_cuisine = (cuisine != null) ? Collections.unmodifiableList(new ArrayList<cuisineType>(cuisine)) : Collections.<cuisineType>emptyList();
            return new Cuisine(cuisine);
        }
    }

}
