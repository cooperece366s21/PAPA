package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.sql.SQLException;

public interface RestaurantStore {

    int storeToDB(DBconnection con_in, String response, String ID, String alias, String name, String phone,
                  String displayPhone, JSONArray cuisine, double rating, JSONObject location, JSONObject coordinates,
                  String price, JSONArray hour) throws SQLException;

    String getRestaurantUrl(DBconnection con_in, String restaurantID) throws SQLException;
}
