package edu.cooper.ece366.service;

import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.categories.Restaurant;

public interface SwipingService {


    public Integer swipeRight(User user, Restaurant restaurant, Lobby lobby);
    public Integer swipeLeft(User user, Restaurant restaurant, Lobby lobby);

}