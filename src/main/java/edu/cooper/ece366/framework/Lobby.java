package edu.cooper.ece366.framework;

import edu.cooper.ece366.categories.Restaurant;
import io.norberg.automatter.AutoMatter;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@AutoMatter
public interface Lobby {
    UUID ID();

    String code();

    List<User> userList();

    HashMap<Restaurant, Integer> lobbyRestaurants();    //contains all the likes from all the users

}