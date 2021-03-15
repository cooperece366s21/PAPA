package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;

public interface RestaurantStore {
    Restaurant get(String id);

    String getRestId(Restaurant restaurant);
}
