package edu.cooper.ece366.service;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.Lobby;

import java.util.List;
import java.util.HashMap;

public class SwipingServiceImpl implements SwipingService {

    @Override
    public Integer swipeLeft(User user, Restaurant restaurant, Lobby lobby){

        if(user.dislikes().contains(restaurant))
            user.dislikes().add(restaurant);


        return 0;
    }

    @Override
    public Integer swipeRight(User user, Restaurant restaurant, Lobby lobby){
        if(!user.likes().contains(restaurant))
            user.likes().add(restaurant);
//        Restaurant r =lobby.lobbyRestaurants().get(restaurant)


        //lobby.lobbyLikes().add(restaurant);
        return 0;
    }

}