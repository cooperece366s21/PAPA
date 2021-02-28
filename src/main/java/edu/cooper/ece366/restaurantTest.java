package edu.cooper.ece366;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.model.Address;
import edu.cooper.ece366.model.Cuisine;
import edu.cooper.ece366.model.OperatingHours;
import edu.cooper.ece366.model.PhoneNumber;

import java.util.ArrayList;

public class restaurantTest {

    public static void main(String[] args) {
        Restaurant restaurant1 = panya_setup();
        Restaurant restaurant2 = yakitoriTaisho_setup();
        Restaurant restaurant3 = boka_setup();
        Restaurant restaurant4 = klong_setup();
        Restaurant restaurant5 = theBao_setup();
    }

    public static ArrayList<Restaurant> generateRestaurants(){
        ArrayList<Restaurant> restaurantList = new ArrayList<>();

        Restaurant restaurant1 = panya_setup();
        Restaurant restaurant2 = yakitoriTaisho_setup();
        Restaurant restaurant3 = boka_setup();
        Restaurant restaurant4 = klong_setup();
        Restaurant restaurant5 = theBao_setup();

        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        restaurantList.add(restaurant4);
        restaurantList.add(restaurant5);

        return restaurantList;
    }

    public static Restaurant panya_setup(){
        Cuisine panya_cuisine = new Cuisine();
        panya_cuisine.add(Cuisine.cuisineType.bakeries);

        Address panya_address = new Address("Panya","8 Stuyvesant St","New York","NY","10003");

        PhoneNumber panyaPhoneNumber = new PhoneNumber(212, 777, 1930);

        OperatingHours panya_operatingHours = new OperatingHours();
        panya_operatingHours.add(0,"0830","1900",false);
        panya_operatingHours.add(1,"0830","1900",false);
        panya_operatingHours.add(2,"0830","1900",false);
        panya_operatingHours.add(3,"0830","1900",false);
        panya_operatingHours.add(4,"0830","1900",false);
        panya_operatingHours.add(5,"0830","1900",false);
        panya_operatingHours.add(6,"0830","1900",false);

        Restaurant panya = new Restaurant("panya-bakery-new-york","Panya Bakery","$",3.5,
                panya_cuisine,panya_address,panyaPhoneNumber,panya_operatingHours);
        return panya;
    }

    public static Restaurant yakitoriTaisho_setup(){
        Cuisine yakitoriTaisho_cuisine = new Cuisine();
        yakitoriTaisho_cuisine.add(Cuisine.cuisineType.japanese);

        Address yakitoriTaisho_address = new Address("Yakitori Taisho","5 St Marks Pl, Ste 1","New York","NY","10003");

        PhoneNumber yakitoriTaisho_phoneNumber = new PhoneNumber(212, 228, 5086);

        OperatingHours yakitoriTaisho_operatingHours = new OperatingHours();
        yakitoriTaisho_operatingHours.add(0,"1800","0200",true);
        yakitoriTaisho_operatingHours.add(1,"1800","0200",true);
        yakitoriTaisho_operatingHours.add(2,"1800","0200",true);
        yakitoriTaisho_operatingHours.add(3,"1800","0200",true);
        yakitoriTaisho_operatingHours.add(4,"1800","0400",true);
        yakitoriTaisho_operatingHours.add(5,"1800","0400",true);
        yakitoriTaisho_operatingHours.add(6,"1800","0200",true);

        return new Restaurant("yakitori-taisho-new-york","Yakitori Taisho","$$",3.5,
                yakitoriTaisho_cuisine,yakitoriTaisho_address,yakitoriTaisho_phoneNumber,yakitoriTaisho_operatingHours);
    }

    public static Restaurant boka_setup(){
        Cuisine boka_cuisine = new Cuisine();
        boka_cuisine.add(Cuisine.cuisineType.korean);

        Address boka_address = new Address("Boka","9 St Marks Pl","New York","NY","10003");

        PhoneNumber boka_phoneNumber = new PhoneNumber(646, 678, 5796);

        OperatingHours boka_operatingHours = new OperatingHours();
        boka_operatingHours.add(0,"1100","0245",true);
        boka_operatingHours.add(1,"1100","0245",true);
        boka_operatingHours.add(2,"1100","0245",true);
        boka_operatingHours.add(3,"1100","0330",true);
        boka_operatingHours.add(4,"1100","0330",true);
        boka_operatingHours.add(5,"1100","0330",true);
        boka_operatingHours.add(6,"1100","0245",true);

        return new Restaurant("boka-new-york","Boka","$$",3.5,
                boka_cuisine,boka_address,boka_phoneNumber,boka_operatingHours);
    }

    public static Restaurant klong_setup(){
        Cuisine klong_cuisine = new Cuisine();
        klong_cuisine.add(Cuisine.cuisineType.thai);

        Address klong_address = new Address("Klong","7 St Marks Pl","New York","NY","10003");

        PhoneNumber klong_phoneNumber = new PhoneNumber(212, 505, 9955);

        OperatingHours klong_operatingHours = new OperatingHours();
        klong_operatingHours.add(0,"1115","2230",false);
        klong_operatingHours.add(1,"1115","2230",false);
        klong_operatingHours.add(2,"1115","2230",false);
        klong_operatingHours.add(3,"1115","2230",false);
        klong_operatingHours.add(4,"1115","2330",false);
        klong_operatingHours.add(5,"1215","2330",false);
        klong_operatingHours.add(6,"1215","2230",false);

        return new Restaurant("klong-new-york","Klong","$$",4,
                klong_cuisine,klong_address,klong_phoneNumber,klong_operatingHours);
    }

    public static Restaurant theBao_setup(){
        Cuisine theBao_cuisine = new Cuisine();
        theBao_cuisine.add(Cuisine.cuisineType.shanghainese);
        theBao_cuisine.add(Cuisine.cuisineType.dim_sum);
        theBao_cuisine.add(Cuisine.cuisineType.cantonese);

        Address theBao_address = new Address("The Bao","13 St Marks Pl","New York","NY","10003");

        PhoneNumber theBao_phoneNumber = new PhoneNumber(212, 388, 9238);

        OperatingHours theBao_operatingHours = new OperatingHours();
        theBao_operatingHours.add(0,"1130","2200",true);
        theBao_operatingHours.add(1,"1130","2200",true);
        theBao_operatingHours.add(2,"1130","2200",true);
        theBao_operatingHours.add(3,"1130","2200",true);
        theBao_operatingHours.add(4,"1130","2200",true);
        theBao_operatingHours.add(5,"1130","2200",true);
        theBao_operatingHours.add(6,"1130","2200",true);

        return new Restaurant("the-bao-new-york","The Bao","$$",4,
                theBao_cuisine,theBao_address,theBao_phoneNumber,theBao_operatingHours);
    }
}
