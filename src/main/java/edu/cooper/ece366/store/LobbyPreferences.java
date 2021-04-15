package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.sql.SQLException;
import java.util.Map;

public interface LobbyPreferences {

    public String getString(Lobby lobby, Restaurant restaurant);

    public Integer getNumOfLikes(Lobby lobby, Restaurant restaurant);

    public Map<String, Integer> returnLobbyLikes();

    public String parseLobby(String string);

    public String parseRest(String string);

    public Map<String, Integer> initLobbyLikes(LobbyStore lobbyStore, Lobby lobby);

    public Map<String, Integer> updateLobbyLikes(Lobby lobby, Restaurant restaurant, Integer likes);

    //public Integer putLobby(Lobby lobby, Restaurant restaurant);

    public Map<String, Integer> lobbyLike(Lobby lobby, Restaurant restaurant);


//    public Map<String, Integer> getLobbyMap(String lobbyId);

    //public Integer getRecommendation(Map<String, Integer> lobbyLikes);

//    public Restaurant getRecommendation(Map<String, Integer> restaurant_maps, RestaurantStore restaurantStore);

    public Restaurant getRecommendation(Lobby lobby, RestaurantStore restaurantStore);

    public Map<String, Integer> getLobbyMap(Lobby lobby);

    public int storeToDB(DBconnection con_in, Lobby lobby, Restaurant rest) throws SQLException;

    public int incrementDB(DBconnection con_in, Lobby lobby, Restaurant rest) throws SQLException;

}