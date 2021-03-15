package edu.cooper.ece366.service;

import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.categories.Restaurant;

public interface SwipingService {

    public Integer like(User user, Restaurant restaurant, Lobby lobby);
    public  Integer dislike(User user, Restaurant restaurant, Lobby lobby);

}