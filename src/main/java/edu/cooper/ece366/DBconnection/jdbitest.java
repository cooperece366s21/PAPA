package edu.cooper.ece366.DBconnection;

import edu.cooper.ece366.store.RestaurantStoreImpl;
import edu.cooper.ece366.yelpAPI.YelpApi;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class jdbitest {
    public static void main(String[] args) throws SQLException, IOException {
        DBconnection dBconnection = new DBconnection();

        RestaurantStoreImpl restaurantstoreimpl = new RestaurantStoreImpl();
        YelpApi yelpApi = new YelpApi();
        JSONObject response = yelpApi.searchByBusinessId("V7lXZKBDzScDeGB8JmnzSA");
        JSONArray cuisine = (JSONArray) response.get("categories");
        JSONObject location = (JSONObject) response.get("location");
        JSONObject coordinates = (JSONObject) response.get("coordinates");
        JSONArray hour = (JSONArray) response.get("hours");
        restaurantstoreimpl.storeToDB(dBconnection,
                                      response.toString(),
                                      response.getString("id"),
                                      response.getString("alias"),
                                      response.getString("name"),
                                      response.getString("phone"),
                                      response.getString("display_phone"),
                                      cuisine,
                                      response.getDouble("rating"),
                                      location,
                                      coordinates,
                                      response.getString("price"),
                                      hour);
    }
}
