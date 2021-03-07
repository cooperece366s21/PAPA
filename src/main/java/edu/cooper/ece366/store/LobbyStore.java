package edu.cooper.ece366.store;


import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.List;
import java.util.UUID;

public interface LobbyStore {
    Lobby get(UUID id);
    String getCode(Lobby lobby);

    // Function to get the list of Restaurants within a certain amount of miles
    List<Restaurant> getByLocation(Double miles);
    List<Restaurant> likes();

//    List<Content> getByRating(Rating rating);

    void beginSearch(List<User> users, List<Restaurant> restaurants);

}
