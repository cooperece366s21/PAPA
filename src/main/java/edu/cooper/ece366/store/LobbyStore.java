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

    List<User> getUsers(Lobby lobby);
    // Function to get the list of Restaurants within a certain amount of miles
    List<Restaurant> getByLocation(Double miles);
    //List<Restaurant> like

//    List<Content> getByRating(Rating rating);


    public List<Restaurant> generateRestList();

    //public Map<Restaurant, Integer> initializeLobby(List<Restaurant> restaurants, Lobby lobby);

    public Restaurant getRecommendation(Map<String, Integer> restaurants_maps, RestaurantStore restaurantStore);

}
