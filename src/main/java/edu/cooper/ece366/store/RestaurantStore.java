package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

public interface RestaurantStore {


    Restaurant get(String id);

    List<Restaurant> getListRest(List<String> restStringList);

    String getRestId(Restaurant restaurant);

    int storeToDB(DBconnection con_in, String response, String ID, String alias, String name, String phone,
                  String displayPhone, JSONArray cuisine, double rating, JSONObject location, JSONObject coordinates,
                  String price, JSONArray hour) throws SQLException;
}
