package edu.cooper.ece366.store;


import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.List;
import java.util.UUID;

public interface LobbyStore {
    Lobby get(UUID id);
    String getCode(Lobby lobby)

    // Function to get the list of Restaurants within a certain amount of miles
    List<Restaurant> getByLocation(Double miles);

//    List<Content> getByRating(Rating rating);

    void beginSearch(List<Users> users, List<Restaurants> resteraunts);

}
