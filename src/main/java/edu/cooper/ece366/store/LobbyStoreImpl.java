package edu.cooper.ece366.store;


import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.LobbyBuilder;
import edu.cooper.ece366.store.RestaurantStoreImpl;
import edu.cooper.ece366.store.UserStoreImpl;
import edu.cooper.ece366.restaurantTest;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.util.*;

public class LobbyStoreImpl implements LobbyStore {

    public static final Map<String, Lobby> LOBBY_MAP;
    static {
        LOBBY_MAP =
                List.of(
                        new LobbyBuilder()
                                .ID("code1")
                                .code("code1")
                                .addUser_list(UserStoreImpl.USER_MAP.get("KollKid"))
                                .addUser_list(UserStoreImpl.USER_MAP.get("Pablo"))
                                .addUser_list(UserStoreImpl.USER_MAP.get("xXx_Sephiroth_xXx"))
                                .putRestaurant_map("panya-bakery-new-york", 0)
                                .putRestaurant_map("mamoun's-halal-new-york", 0)
                                .putRestaurant_map("smac's-american-new-york", 0)
                                .build(),
                        new LobbyBuilder()
                                .ID("code2")
                                .code("code2")
                                .addUser_list(UserStoreImpl.USER_MAP.get("KollKid"))
                                .addUser_list(UserStoreImpl.USER_MAP.get("Pablo"))
                                .putRestaurant_map("panya-bakery-new-york", 0)
                                .putRestaurant_map("mamoun's-halal-new-york", 0)
                                .putRestaurant_map("smac's-american-new-york", 0)
                                .build())
                        .stream()
                        .collect(Collectors.toMap(Lobby::ID, Function.identity()));
    }

    @Override
    public Lobby get(String id) {
        return LOBBY_MAP.get(id);
    }

    @Override
    public String getCode(Lobby lobby) {

        return lobby.code();
    }

    @Override
    public String getLobbyId(Lobby lobby) {

        return lobby.ID();
    }

    @Override
    public List<User> getUsers(Lobby lobby) {
        return lobby.user_lists();
    }

    @Override
    public List<Restaurant> getByLocation(Double miles) {

        return null;
    }

    @Override
    public List<Restaurant> generateRestList(){
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        for (Map.Entry<String, Restaurant> entry : RestaurantStoreImpl.RESTAURANT_MAP.entrySet()){
            restaurants.add(entry.getValue());
        }

        return restaurants;
    }
    /*
    @Override
    public Map<Restaurant, Integer> initializeLobby(List<Restaurant> restaurants, Lobby lobby) {
        //ArrayList<Restaurant> restaurantsList;
        //restaurants = RestaurantStoreImpl.RESTAURANT_MAP
        //Restaurant recommendation = RestaurantStoreImpl.RESTAURANT_MAP.get("panya-bakery-new-york")

        for(Restaurant option: restaurants){
            lobby.restaurant_maps().put(option, 0);
        }
        //return null;
        return lobby.restaurant_maps();
    }

    */

    @Override
    public Restaurant getRecommendation(Map<String, Integer> restaurant_maps, RestaurantStore restaurantStore) {

        Integer maxLikes = Collections.max(restaurant_maps.values());

        for (Map.Entry<String, Integer> temp : restaurant_maps.entrySet()) {
            if (temp.getValue().equals(maxLikes)) {

                return restaurantStore.get(temp.getKey());
            }
        }
        return null;
    }

    @Override
    public Map<String, UserPreferences> getConnection(Lobby lobby){
        return null;
    }

}
