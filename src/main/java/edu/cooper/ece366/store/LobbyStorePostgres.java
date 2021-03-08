package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import java.util.List;
import java.util.Map;

public class LobbyStorePostgres implements LobbyStore {

  @Override
  public Lobby get(final String id) {
    return null;
  }

  @Override
  public String getCode(final Lobby lobby) {
    return null;
  }

  @Override
  public List<User> getUsers(final Lobby lobby) {
    return null;
  }

  @Override
  public List<Restaurant> getByLocation(final Double miles) {
    return null;
  }

  @Override
  public List<Restaurant> generateRestList() {
    return null;
  }

  @Override
  public Restaurant getRecommendation(final Map<String, Integer> restaurants_maps,
      final RestaurantStore restaurantStore) {
    return null;
  }
}
