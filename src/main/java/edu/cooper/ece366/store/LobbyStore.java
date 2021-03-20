package edu.cooper.ece366.store;


import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;

public interface LobbyStore {
    Lobby get(String id);
    String getCode(Lobby lobby);

    String getLobbyId(Lobby lobby);

    public List<String> getLobbyList(Lobby lobby);

    // Function to get the list of Restaurants within a certain amount of miles
    List<Restaurant> getByLocation(Double miles);

    //    List<Content> getByRating(Rating rating);

    public Map<String, Integer> generateLobbyMap(Lobby lobby);

    //public Map<Restaurant, Integer> initializeLobby(List<Restaurant> restaurants, Lobby lobby);

    //public Restaurant getRecommendation(Map<String, Integer> restaurants_maps, RestaurantStore restaurantStore);

}
