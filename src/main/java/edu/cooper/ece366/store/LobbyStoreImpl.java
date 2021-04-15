package edu.cooper.ece366.store;


import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.LobbyBuilder;
import edu.cooper.ece366.store.RestaurantStoreImpl;
import edu.cooper.ece366.store.UserStoreImpl;
import edu.cooper.ece366.restaurantTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.util.*;

public class LobbyStoreImpl implements LobbyStore {

    DataSource dbcp;
    Connection conn = null;

    public static final Map<String, Lobby> LOBBY_MAP;
    static {
        LOBBY_MAP =
                List.of(
                        new LobbyBuilder()
                                .ID("code1")
                                .code("code1")
                                .addRestaurant_map("panya-bakery-new-york")
                                .addRestaurant_map("mamoun's-halal-new-york")
                                .addRestaurant_map("smac's-american-new-york")
                                .build(),
                        new LobbyBuilder()
                                .ID("code2")
                                .code("code2")
                                .addRestaurant_map("panya-bakery-new-york")
                                .addRestaurant_map("mamoun's-halal-new-york")
                                .addRestaurant_map("smac's-american-new-york")
                                .build())
                        .stream()
                        .collect(Collectors.toMap(Lobby::ID, Function.identity()));
    }

    @Override
    public Lobby get(String id) {
        return LOBBY_MAP.get(id);
    }

    @Override
    public String getCode(Lobby lobby) {

        return lobby.code();
    }

    @Override
    public List<String> getLobbyList(Lobby lobby){
        return lobby.restaurant_maps();
    }

    @Override
    public String getLobbyId(Lobby lobby) {

        return lobby.ID();
    }


    @Override
    public List<Restaurant> getByLocation(Double miles) {

        return null;
    }

    public Map<String, Integer> generateLobbyMap(Lobby lobby){

        List<String> restaurantList = lobby.restaurant_maps();

        Map<String, Integer> lobbyMap = new HashMap<>();

        for (String rest : restaurantList) {
            String key = lobby.ID() + ':' + rest;
            lobbyMap.put(key, 0);
        }

        return lobbyMap;

    }

    @Override
    public int storeToDB(DBconnection con_in, Lobby lobby) throws SQLException {
        this.dbcp = DBconnection.getDataSource();
        try{
            conn = dbcp.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO lobbies (id, code) VALUES (?, ?);");
            stmt.setString(1, lobby.ID());
            stmt.setString(2, lobby.code());
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
        finally {
            conn.close();
        }
        return  0;
    }

//    public List<Restaurant> getLobbyFeed(Lobby lobby){
//        List<String> restaurantStringList = lobby.restaurant_maps();
//
//        List<Restaurant> restaurantList = null;
//
//        for (String restString : restaurantStringList) {
//            Restaurant rest =
//        }
//    }

//    @Override
//    public List<Restaurant> generateRestList(){
//        List<Restaurant> restaurants = new ArrayList<Restaurant>();
//
//        for (Map.Entry<String, Restaurant> entry : RestaurantStoreImpl.RESTAURANT_MAP.entrySet()){
//            restaurants.add(entry.getValue());
//        }
//
//        return restaurants;
//    }
    /*
    @Override
    public Map<Restaurant, Integer> initializeLobby(List<Restaurant> restaurants, Lobby lobby) {
        //ArrayList<Restaurant> restaurantsList;
        //restaurants = RestaurantStoreImpl.RESTAURANT_MAP
        //Restaurant recommendation = RestaurantStoreImpl.RESTAURANT_MAP.get("panya-bakery-new-york")

        for(Restaurant option: restaurants){
            lobby.restaurant_maps().put(option, 0);
        }
        //return null;
        return lobby.restaurant_maps();
    }

    */

//    @Override
//    public Restaurant getRecommendation(Map<String, Integer> restaurant_maps, RestaurantStore restaurantStore) {
//
//        Integer maxLikes = Collections.max(restaurant_maps.values());
//
//        for (Map.Entry<String, Integer> temp : restaurant_maps.entrySet()) {
//            if (temp.getValue().equals(maxLikes)) {
//
//                return restaurantStore.get(temp.getKey());
//            }
//        }
//        return null;
//    }


}
