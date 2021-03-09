package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;

import java.util.List;

public interface UserPreferences {

    List<Restaurant> likes();

    List<Restaurant> dislikes();
}
