package edu.cooper.ece366.service;

import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.categories.Restaurant;

public interface SwipingService {

    public Integer like(Lobby lobby, Restaurant restaurant, User user);
    public  Integer dislike(Lobby lobby, Restaurant restaurant, User user);

}