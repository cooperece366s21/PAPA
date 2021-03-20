package edu.cooper.ece366.framework;

import edu.cooper.ece366.categories.Restaurant;
import io.norberg.automatter.AutoMatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AutoMatter
public interface Lobby {
    String ID();

    String code();

    //List<User> user_lists();

    List<String> restaurant_maps(); //list of restaurants

    //Map<String, Integer> restaurant_maps();    //contains all the likes from all the users

}