package edu.cooper.ece366.store;


import com.google.common.base.Splitter;
import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.categories.RestaurantBuilder;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.LobbyBuilder;
import edu.cooper.ece366.store.RestaurantStoreImpl;
import edu.cooper.ece366.store.UserStoreImpl;
import edu.cooper.ece366.restaurantTest;
import edu.cooper.ece366.yelpAPI.YelpApi;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.util.*;

public class LobbyStoreImpl implements LobbyStore {

    DataSource dbcp;
    Connection conn = null;

    @Override
    public Lobby getCurrentLobby(String lobbyID) throws SQLException {
        DBconnection dBconnection = new DBconnection();
        this.dbcp = DBconnection.getDataSource();
        this.conn = dbcp.getConnection();
        String returnLobbyID = null, returnLobbyCode = null, returnLobbyOwner = null;
        try {
            PreparedStatement getCuisineID = conn.prepareStatement(
                    "SELECT * FROM lobbies WHERE ID=?");
            getCuisineID.setString(1, lobbyID);
            ResultSet rs = getCuisineID.executeQuery();
            while(rs.next()){
                returnLobbyID = rs.getString("ID");
                returnLobbyCode = rs.getString("code");
                returnLobbyOwner = rs.getString("owner");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            conn.close();
        }
        return new LobbyBuilder().ID(returnLobbyID).code(returnLobbyCode).ownerID(returnLobbyOwner).build();
    }

    @Override
    public List<Restaurant> getRestaurantList(String lobbyID) throws SQLException {
        DBconnection dBconnection = new DBconnection();
        this.dbcp = DBconnection.getDataSource();
        this.conn = dbcp.getConnection();

        List<String> restaurantIDList = new ArrayList<>();
        List<Restaurant> returnList = new ArrayList<>();

        String returnID = null, returnAlias = null, returnName = null, returnDisplayPhone = null, returnPrice = null;
        String returnCuisine = null, returnAddress = null, returnPhoneNumber = null, returnOperatingHours = null;
        String returnYelpInfo = null;
        Boolean returnIsOpenNow = null;
        Double returnRating = null;
        try{
            PreparedStatement getRestaurantID = conn.prepareStatement(
                    "SELECT restaurantID FROM lobby_preferred_restaurants WHERE lobbyID=?");
            getRestaurantID.setString(1, lobbyID);
            ResultSet rs = getRestaurantID.executeQuery();
            while(rs.next()){
                restaurantIDList.add(rs.getString("restaurantID"));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        for (String restID : restaurantIDList) {

            // get restaurant
            try{
                PreparedStatement getRestaurant = conn.prepareStatement(
                        "SELECT * FROM restaurants WHERE ID=?");
                getRestaurant.setString(1, restID);
                ResultSet rs1 = getRestaurant.executeQuery();
                while(rs1.next()){
                    returnID = rs1.getString("ID");
                    returnAlias = rs1.getString("alias");
                    returnName = rs1.getString("name");
                    returnDisplayPhone = rs1.getString("displayPhone");
                    returnPrice = rs1.getString("price");
                    returnPhoneNumber = rs1.getString("phone");
                    returnIsOpenNow = rs1.getBoolean("isOpenNow");
                    returnRating = rs1.getDouble("rating");

                    returnYelpInfo = rs1.getString("yelpInfo");
                    JSONObject yelpInfo = new JSONObject(returnYelpInfo);

                    returnCuisine = yelpInfo.get("categories").toString();
                    returnAddress = yelpInfo.get("location").toString();
                    returnOperatingHours = yelpInfo.get("hours").toString();
                }
            } catch(SQLException throwables){
                throwables.printStackTrace();
            }

            // create restaurant object and append to list
            returnList.add(new RestaurantBuilder().ID(returnID)
                    .alias(returnAlias)
                    .name(returnName)
                    .phoneNumber(returnPhoneNumber)
                    .displayPhone(returnDisplayPhone)
                    .price(returnPrice)
                    .cuisine(returnCuisine)
                    .address(returnAddress)
                    .operatingHours(returnOperatingHours)
                    .isOpenNow(returnIsOpenNow)
                    .rating(returnRating)
                    .build());
        }
        conn.close();
        return returnList;
    }

    @Override
    public Lobby initLobby(String ownerID, String lobbyID, String location) throws SQLException, IOException {
        DBconnection dBconnection = new DBconnection();
        this.dbcp = DBconnection.getDataSource();
        this.conn = dbcp.getConnection();

        String lobbyCode = UUID.randomUUID().toString();

        try{
            PreparedStatement insertLobby = conn.prepareStatement(
                    "INSERT INTO lobbies (ID, code, owner) " +
                            "VALUES (?, ?, ?)" +
                            "ON DUPLICATE KEY UPDATE ID = ?, owner = ?");
            insertLobby.setString(1, lobbyID);
            insertLobby.setString(2, lobbyCode);
            insertLobby.setString(3, ownerID);
            insertLobby.setString(4, lobbyID);
            insertLobby.setString(5, ownerID);

            try{
                insertLobby.executeUpdate();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        YelpApi yelpApi = new YelpApi();
        JSONArray response = yelpApi.searchForBusinessesByLocation(null, location, 10);

        ArrayList<Object> res = new ArrayList<>();

        for(Object object : response){
            res.add(object);
        }
        RestaurantStoreImpl restaurantstoreimpl = new RestaurantStoreImpl();
        for(Object r : res){
            JSONObject jsonObject = (JSONObject) r;
            String restaurantID = jsonObject.getString("id");
            JSONObject restaurantDetail = yelpApi.searchByBusinessId(jsonObject.getString("id"));
            System.out.println(restaurantDetail.toString());
            // put restaurant into restaurants
            JSONArray cuisine = (JSONArray) restaurantDetail.get("categories");
            JSONObject loc = (JSONObject) restaurantDetail.get("location");
            JSONObject coordinates = (JSONObject) restaurantDetail.get("coordinates");
            JSONArray hour = (JSONArray) restaurantDetail.get("hours");
            restaurantstoreimpl.storeToDB(dBconnection,
                    restaurantDetail.toString(),
                    restaurantDetail.getString("id"),
                    restaurantDetail.getString("alias"),
                    restaurantDetail.getString("name"),
                    restaurantDetail.getString("phone"),
                    restaurantDetail.getString("display_phone"),
                    cuisine,
                    restaurantDetail.getDouble("rating"),
                    loc,
                    coordinates,
                    restaurantDetail.getString("price"),
                    hour);

            // put restaurant into lobbyPreference

            try{
                PreparedStatement insertLobby = conn.prepareStatement(
                        "INSERT INTO lobby_preferred_restaurants (lobbyID, restaurantID, vote)" +
                                " VALUES (?, ?, 0)" +
                                "ON DUPLICATE KEY UPDATE lobbyID = ?, restaurantID = ?");
                insertLobby.setString(1, lobbyID);
                insertLobby.setString(2, restaurantID);
                insertLobby.setString(3, lobbyID);
                insertLobby.setString(4, restaurantID);
                try{
                    insertLobby.executeUpdate();
                } catch (SQLException throwables) {
                    System.err.println("Error when executing SQL command.");
                    throwables.printStackTrace();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        conn.close();
        return new LobbyBuilder().ID(lobbyID).code(lobbyCode).ownerID(ownerID).build();
    }

    @Override
    public Lobby joinLobby(String userID, String lobbyID) throws SQLException {
        DBconnection dBconnection = new DBconnection();
        this.dbcp = DBconnection.getDataSource();
        this.conn = dbcp.getConnection();
        try{
            PreparedStatement insertUsertoLobby = conn.prepareStatement(
                    "INSERT INTO lobbyusers (lobbyID, userID)" +
                            " VALUES (?, ?)" +
                            "ON DUPLICATE KEY UPDATE lobbyID = ?, userID = ?");
            insertUsertoLobby.setString(1, lobbyID);
            insertUsertoLobby.setString(2, userID);
            insertUsertoLobby.setString(3, lobbyID);
            insertUsertoLobby.setString(4, userID);
            try{
                insertUsertoLobby.executeUpdate();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            conn.close();
        }
        return getCurrentLobby(lobbyID);
    }

    @Override
    public void leaveLobby(String lobbyID, String userID) throws SQLException {
        DBconnection dBconnection = new DBconnection();
        this.dbcp = DBconnection.getDataSource();
        this.conn = dbcp.getConnection();
        try{
            PreparedStatement deleteUser = conn.prepareStatement(
                    "DELETE FROM lobbyusers WHERE lobbyID = ? AND userID = ?");
            deleteUser.setString(1, lobbyID);
            deleteUser.setString(2, userID);
            try{
                deleteUser.executeUpdate();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            conn.close();
        }
    }

    @Override
    public Restaurant getRecommendation(String lobbyID) throws SQLException {
        DBconnection dBconnection = new DBconnection();
        this.dbcp = DBconnection.getDataSource();
        this.conn = dbcp.getConnection();
        String returnID = null, returnAlias = null, returnName = null, returnDisplayPhone = null, returnPrice = null;
        String returnCuisine = null, returnAddress = null, returnPhoneNumber = null, returnOperatingHours = null;
        String returnYelpInfo = null;
        Boolean returnIsOpenNow = null;
        Double returnRating = null;
        try{
            PreparedStatement getRestaurantID = conn.prepareStatement(
                    "SELECT restaurantID " +
                            "FROM lobby_preferred_restaurants " +
                            "WHERE lobbyID = ? AND vote = " +
                            "(SELECT MAX(vote) FROM lobby_preferred_restaurants)");
            getRestaurantID.setString(1, lobbyID);
            ResultSet rs = getRestaurantID.executeQuery();
            if(rs.next()){
                try{
                    PreparedStatement getRestaurant = conn.prepareStatement(
                            "SELECT * FROM restaurants WHERE ID=?");
                    getRestaurant.setString(1, rs.getString("restaurantID"));
                    ResultSet rs1 = getRestaurant.executeQuery();
                    while(rs1.next()){
                        returnID = rs1.getString("ID");
                        returnAlias = rs1.getString("alias");
                        returnName = rs1.getString("name");
                        returnDisplayPhone = rs1.getString("displayPhone");
                        returnPrice = rs1.getString("price");
                        returnPhoneNumber = rs1.getString("phone");
                        returnIsOpenNow = rs1.getBoolean("isOpenNow");
                        returnRating = rs1.getDouble("rating");

                        returnYelpInfo = rs1.getString("yelpInfo");
                        JSONObject yelpInfo = new JSONObject(returnYelpInfo);

                        returnCuisine = yelpInfo.get("categories").toString();
                        returnAddress = yelpInfo.get("location").toString();
                        returnOperatingHours = yelpInfo.get("hours").toString();
                    }
                } catch(SQLException throwables){
                    throwables.printStackTrace();
                }
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            conn.close();
        }
        return new RestaurantBuilder().ID(returnID)
                .alias(returnAlias)
                .name(returnName)
                .phoneNumber(returnPhoneNumber)
                .displayPhone(returnDisplayPhone)
                .price(returnPrice)
                .cuisine(returnCuisine)
                .address(returnAddress)
                .operatingHours(returnOperatingHours)
                .isOpenNow(returnIsOpenNow)
                .rating(returnRating)
                .build();
    }
}
