package edu.cooper.ece366.store;

import com.google.gson.JsonNull;
import edu.cooper.ece366.categories.Restaurant;
import javax.sql.DataSource;
import edu.cooper.ece366.model.Address;
import edu.cooper.ece366.model.OperatingHours;
import edu.cooper.ece366.model.PhoneNumber;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cooper.ece366.DBconnection.DBconnection;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

public class RestaurantStoreImpl implements RestaurantStore{

    DataSource dbcp;
    Connection conn = null;

    @Override
    public Restaurant get(final String id) {
        return null;
    }

    @Override
    public String getRestId(Restaurant restaurant){

        return null;
    }

    @Override
    public int storeToDB(DBconnection con_in, String response, String ID, String alias, String name, String phone,
                         String displayPhone, JSONArray cuisine, double rating, JSONObject location,
                         JSONObject coordinates, String price, JSONArray hour) throws SQLException {
        this.dbcp = DBconnection.getDataSource();
        conn = dbcp.getConnection();

        JSONObject jsOpen = hour.getJSONObject(0);

        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO restaurants (ID, alias, name, isOpenNow, phone, displayPhone, rating, " +
                            "price, yelpInfo) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                            "ON DUPLICATE KEY UPDATE ID = ?, " +
                            "alias = ?, name = ?, isOpenNow = ?, phone = ?, displayPhone = ?, rating = ?, " +
                            "price = ?, yelpInfo = ?");
            stmt.setString(1, ID);
            stmt.setString(2, alias);
            stmt.setString(3, name);
            stmt.setBoolean(4, jsOpen.getBoolean("is_open_now"));
            stmt.setString(5, phone);
            stmt.setString(6, displayPhone);
            stmt.setDouble(7, rating);
            stmt.setString(8, price);
            stmt.setString(9, response);
            stmt.setString(10, ID);
            stmt.setString(11, alias);
            stmt.setString(12, name);
            stmt.setBoolean(13, jsOpen.getBoolean("is_open_now"));
            stmt.setString(14, phone);
            stmt.setString(15, displayPhone);
            stmt.setDouble(16, rating);
            stmt.setString(17, price);
            stmt.setString(18, response);
            try{
                stmt.executeUpdate();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }

        } catch (SQLException err) {
            System.err.println("Error when connecting to database.");
            err.printStackTrace();
            return -1;
        }

        // insert cuisine
        ArrayList<JSONObject> cuisineList = new ArrayList<>();
        for(int i = 0; i < cuisine.length(); i++){
            cuisineList.add((JSONObject) cuisine.get(i));
        }
        for (JSONObject jsonObject : cuisineList) {
            try {
                PreparedStatement insertCuisine = conn.prepareStatement(
                        "INSERT INTO cuisine (restaurantID, cuisineAlias, cuisineTitle) " +
                                "VALUES (?, ?, ?)" +
                                "ON DUPLICATE KEY UPDATE restaurantID = ?, " +
                                "cuisineAlias = ?, cuisineTitle = ?");
                insertCuisine.setString(1, ID);
                insertCuisine.setString(2, jsonObject.get("alias").toString());
                insertCuisine.setString(3, jsonObject.get("title").toString());
                insertCuisine.setString(4, ID);
                insertCuisine.setString(5, jsonObject.get("alias").toString());
                insertCuisine.setString(6, jsonObject.get("title").toString());
                try{
                    insertCuisine.executeUpdate();
                } catch (SQLException throwables) {
                    System.err.println("Error when executing SQL command.");
                    throwables.printStackTrace();
                }
            } catch (SQLException | JSONException throwables) {
                throwables.printStackTrace();
            }
        }

        // get cuisineIDs
        ArrayList<Integer> cuisineIDs = new ArrayList<>();
        try{
            PreparedStatement getCuisineID = conn.prepareStatement(
                    "SELECT cuisineID FROM cuisine WHERE restaurantID=?");
            getCuisineID.setString(1, ID);
            ResultSet rs = getCuisineID.executeQuery();
            while(rs.next()){
                cuisineIDs.add(rs.getInt("cuisineID"));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        System.out.println(cuisineIDs);

        String cuisineID = cuisineIDs.get(0).toString();
        for (int k = 1; k < cuisineIDs.size(); k++) {
            cuisineID = cuisineID.concat(", " + cuisineIDs.get(k).toString());
        }

        // insert location
        try{
            PreparedStatement insertLocation = conn.prepareStatement(
                    "INSERT INTO location (restaurantID, address1, address2, address3, city, zip, country, " +
                            "state, displayAddress, latitude, longitude) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                            "ON DUPLICATE KEY UPDATE restaurantID = ?, address1 = ?, address2 = ?, " +
                            "address3 = ?, city = ?, zip = ?, country = ?, state = ?, displayAddress = ?," +
                            "latitude = ?, longitude = ?");
            insertLocation.setString(1, ID);
            insertLocation.setString(2, location.get("address1").toString());
            if(location.get("address2") == null){
                insertLocation.setString(3, "");
            } else {
                insertLocation.setString(3, location.getString("address2"));
            }
            insertLocation.setString(4, location.get("address3").toString());
            insertLocation.setString(5, location.get("city").toString());
            insertLocation.setString(6, location.get("zip_code").toString());
            insertLocation.setString(7, location.get("country").toString());
            insertLocation.setString(8, location.get("state").toString());
            insertLocation.setString(9, location.get("display_address").toString());
            insertLocation.setDouble(10, coordinates.getDouble("latitude"));
            insertLocation.setDouble(11, coordinates.getDouble("longitude"));
            insertLocation.setString(12, ID);
            insertLocation.setString(13, location.get("address1").toString());
            if(location.get("address2") == null){
                insertLocation.setString(14, "");
            } else {
                insertLocation.setString(14, location.getString("address2"));
            }
            //insertLocation.setString(14, location.get("address2").toString());
            insertLocation.setString(15, location.get("address3").toString());
            insertLocation.setString(16, location.get("city").toString());
            insertLocation.setString(17, location.get("zip_code").toString());
            insertLocation.setString(18, location.get("country").toString());
            insertLocation.setString(19, location.get("state").toString());
            insertLocation.setString(20, location.get("display_address").toString());
            insertLocation.setDouble(21, coordinates.getDouble("latitude"));
            insertLocation.setDouble(22, coordinates.getDouble("longitude"));
            try{
                insertLocation.executeUpdate();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException | JSONException throwables) {
            throwables.printStackTrace();
        }

        // get locationID
        int locationID = 0;
        try {
            PreparedStatement getLocationID = conn.prepareStatement(
                "Select locationID from location WHERE restaurantID=?");
            getLocationID.setString(1, ID);
            ResultSet rs = getLocationID.executeQuery();
            while(rs.next()){
                locationID = rs.getInt("locationID");
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        // insert hours
        ArrayList<JSONObject> hoursList = new ArrayList<>();
        JSONObject jsHours = hour.getJSONObject(0);
        JSONArray hours = jsHours.getJSONArray("open");
        for(int i = 0; i < hours.length(); i++){
            hoursList.add(hours.getJSONObject(i));
        }
        for(JSONObject jsonObject : hoursList){
            try{
                PreparedStatement insertHours = conn.prepareStatement(
                        "INSERT INTO hours (restaurantID, hourType, dayWeek, " +
                                "startTime, endTime, isOvernight) " +
                                "VALUES (?, ?, ?, ?, ?, ?)" +
                                "ON DUPLICATE KEY UPDATE restaurantID = ?, hourType = ?, " +
                                "dayWeek = ?, startTime = ?, endTime = ?, isOvernight = ?");
                insertHours.setString(1, ID);
                insertHours.setString(2, jsHours.get("hours_type").toString().toLowerCase());
                insertHours.setInt(3, jsonObject.getInt("day"));
                insertHours.setString(4, jsonObject.getString("start"));
                insertHours.setString(5, jsonObject.getString("end"));
                insertHours.setBoolean(6, jsonObject.getBoolean("is_overnight"));
                insertHours.setString(7, ID);
                insertHours.setString(8, jsHours.get("hours_type").toString().toLowerCase());
                insertHours.setInt(9, jsonObject.getInt("day"));
                insertHours.setString(10, jsonObject.getString("start"));
                insertHours.setString(11, jsonObject.getString("end"));
                insertHours.setBoolean(12, jsonObject.getBoolean("is_overnight"));
                try{
                    insertHours.executeUpdate();
                } catch (SQLException throwables) {
                    System.err.println("Error when executing SQL command.");
                    throwables.printStackTrace();
                }
            } catch (SQLException | JSONException throwables) {
                throwables.printStackTrace();
            }
        }
        // get hourID
        ArrayList<Integer> hourIDs = new ArrayList<>();
        try{
            PreparedStatement getHourID = conn.prepareStatement(
                "SELECT hourID FROM hours WHERE restaurantID=?");
            getHourID.setString(1, ID);
            ResultSet rs = getHourID.executeQuery();
            while(rs.next()){
                hourIDs.add(rs.getInt("hourID"));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        System.out.println(hourIDs);

        String hourID = hourIDs.get(0).toString();
        for (int k = 1; k < cuisineIDs.size(); k++) {
            hourID = hourID.concat(", " + cuisineIDs.get(k).toString());
        }

        // update restaurant

        try{
            PreparedStatement updateRestaurant = conn.prepareStatement(
                    "UPDATE restaurants SET cuisineID = ?," +
                            "locationID = ?," +
                            "hourID = ? " +
                            "WHERE ID = ?");
            updateRestaurant.setString(1, cuisineID);
            updateRestaurant.setInt(2, locationID);
            updateRestaurant.setString(3, hourID);
            updateRestaurant.setString(4, ID);
            try{
                updateRestaurant.executeUpdate();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException | JSONException throwables) {
            throwables.printStackTrace();
        }

        finally {
            conn.close();
        }
        return  0;
    }

    @Override
    public List<Restaurant> getListRest(List<String> restStringList){
        List<Restaurant> restList = new ArrayList<Restaurant>();
        for(String restString : restStringList){
            Restaurant rest = get(restString);
            restList.add(rest);
        }
        return restList;
    }
}
