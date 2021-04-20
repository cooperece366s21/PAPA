package edu.cooper.ece366.DBconnection;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.store.LobbyStoreImpl;
import edu.cooper.ece366.store.RestaurantStoreImpl;
import edu.cooper.ece366.yelpAPI.YelpApi;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class jdbitest {
    public static void insertRest() throws IOException, SQLException {
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

    public static void initLobby() throws IOException, SQLException {
        LobbyStoreImpl lobbyStore = new LobbyStoreImpl();
        //Lobby lobby = lobbyStore.initLobby("owner",  "10003");
    }

    public static void getRestList() throws SQLException {
        LobbyStoreImpl lobbyStore = new LobbyStoreImpl();
        //List<Restaurant> rList = lobbyStore.getRestaurantList("lobby");
//        for (Restaurant r:rList) {
//            System.out.println(r.toString());
//        }
    }

    public static void getRec() throws SQLException {
        LobbyStoreImpl lobbyStore = new LobbyStoreImpl();
//        Restaurant res = lobbyStore.getRecommendation("lobby");
//        System.out.println(res.toString());
    }

    public static void main(String[] args) throws SQLException, IOException {
        initLobby();

//        User and User Preferences Curl Commands
//        post("/:userID/:username/:password/addUser", (req, res) -> handler.addUser(req), gson::toJson);
//        post("/:userID/:username/:password/updateUser", (req, res) -> handler.updateUser(req), gson::toJson);
    }
}
