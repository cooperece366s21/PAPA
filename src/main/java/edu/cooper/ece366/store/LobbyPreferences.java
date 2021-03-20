package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.Map;

public interface LobbyPreferences {

    public String getString(Lobby lobby, Restaurant restaurant);

    public Integer getNumOfLikes(Lobby lobby, Restaurant restaurant);

    public String parseLobby(String string);

    public String parseRest(String string);

    public Integer initLobbyLikes(LobbyStore lobbyStore, Lobby lobby);

    public Integer updateLobbyLikes(Lobby lobby, Restaurant restaurant, Integer likes);

    //public Integer putLobby(Lobby lobby, Restaurant restaurant);

    public Integer lobbyLike(Lobby lobby, Restaurant restaurant);

//    public Map<String, Integer> getLobbyMap(String lobbyId);

    //public Integer getRecommendation(Map<String, Integer> lobbyLikes);

//    public Restaurant getRecommendation(Map<String, Integer> restaurant_maps, RestaurantStore restaurantStore);

    public Restaurant getRecommendation(Lobby lobby, RestaurantStore restaurantStore);

    //public Map<String, Integer> getLobbyMap(Lobby lobby);

}