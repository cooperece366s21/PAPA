package edu.cooper.ece366.yelpAPI;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.categories.RestaurantBuilder;
import edu.cooper.ece366.model.Address;
import edu.cooper.ece366.model.Cuisine;
import edu.cooper.ece366.model.OperatingHours;
import edu.cooper.ece366.model.PhoneNumber;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.io.IOException;

public class JSONtoRestaurant {
    public static Restaurant JSONtoRestaurant(JSONObject JS) throws IOException {
        YelpApi yelpApi = new YelpApi();
        JSONObject resObj = yelpApi.searchByBusinessId(JS.get("id").toString());
        JSONObject addressObj = (JSONObject) resObj.get("location");
        JSONArray cuisineObj = (JSONArray) resObj.get("categories");

        return new RestaurantBuilder()
                .id(JS.get("id").toString())
                .name(JS.get("name").toString())
                .price(JS.get("price").toString())
                .rating((Double) JS.get("rating"))
                .cuisine(new Cuisine.CuisineBuilder().addCuisine(Cuisine.cuisineType.valueOf(cuisineObj.getString(0))).build())
                .address(new Address.AddressBuilder()
                        .name(JS.get("name").toString())
                        .streetAddress(addressObj.get("address1").toString() +
                                        addressObj.get("address2").toString() +
                                        addressObj.get("address3").toString())
                        .city(addressObj.get("city").toString())
                        .state(addressObj.get("state").toString())
                        .zipCode(addressObj.get("zip_code").toString())
                        .build())
                .phoneNumber(new PhoneNumber.PhoneNumberBuilder()
                        .area(212).exch(777).ext(1930)
                        .build())
                .operatingHours(new OperatingHours.OperatingHoursBuilder()
                        .putOperatingHour(0,"0830","1900",false)
                        .putOperatingHour(1,"0830","1900",false)
                        .putOperatingHour(2,"0830","1900",false)
                        .putOperatingHour(3,"0830","1900",false)
                        .putOperatingHour(4,"0830","1900",false)
                        .putOperatingHour(5,"0830","1900",false)
                        .putOperatingHour(6,"0830","1900",false)
                        .build())
                .build();
    }
}
