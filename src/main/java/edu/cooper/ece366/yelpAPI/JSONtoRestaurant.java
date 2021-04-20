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
                .ID(JS.get("id").toString())
                .name(JS.get("name").toString())
                .price(JS.get("price").toString())
                .rating((Double) JS.get("rating"))
                .cuisine("new Cuisine.CuisineBuilder().addCuisine(Cuisine.cuisineType.bakeries).build()")
                .address("z")
                .phoneNumber("")
                .operatingHours("")
                .build();
    }
}
