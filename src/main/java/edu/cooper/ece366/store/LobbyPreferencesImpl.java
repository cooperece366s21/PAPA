package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.store.LobbyStore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LobbyPreferencesImpl implements LobbyPreferences {


    public Map<String, Integer> lobbyLikes = new HashMap<>();

    @Override
    public String getString(Lobby lobby, Restaurant restaurant) {
        String lobbyID = lobby.ID();
        String restID = restaurant.id();

        String output = lobbyID + ':' + restID;

        return output;

    }

    @Override
    public String parseLobby(String string) {
        String[] parse = string.split(":");

        return parse[0];
    }

    @Override
    public String parseRest(String string) {
        String[] parse = string.split(":");

        return parse[1];
    }

    @Override
    public Map<String, Integer> initLobbyLikes(LobbyStore lobbyStore, Lobby lobby){
        lobbyLikes = lobbyStore.generateLobbyMap(lobby);

        return lobbyLikes;
    }

    @Override
    public Map<String, Integer> updateLobbyLikes(Lobby lobby, Restaurant restaurant, Integer likes) {

        String key = getString(lobby, restaurant);
        lobbyLikes.put(key, likes);

        return lobbyLikes;
    }

//    @Override
//    public Integer putLobbyLikes(Lobby lobby, Restaurant restaurant) {
//        String key = getString(lobby, restaurant);
//        lobbyLikes.put(key, 0);
//
//        return 0;
//    }

    @Override
    public Integer getNumOfLikes(Lobby lobby, Restaurant restaurant){
        String key = getString( lobby, restaurant);
        Integer likeCount = lobbyLikes.get(key);

        return likeCount;

    }

    @Override
    public Map<String, Integer> lobbyLike(Lobby lobby, Restaurant restaurant){

        //add to the like counter for the restaurant
        Integer likeCount = getNumOfLikes(lobby, restaurant);
        likeCount++;

        return updateLobbyLikes(lobby, restaurant, likeCount);
    }

    @Override
    public Restaurant getRecommendation(Lobby lobby, RestaurantStore restaurantStore) {
        // If we cant parse due to inefficiencies we can change the Int to another object with the  lobby and restaurant in it
        //Map<String, Integer> lobbyMap = null;

        //lobbyMap = getLobbyMap(lobby);

        Integer maxLikes = Collections.max(lobbyLikes.values());

        for (Map.Entry<String, Integer> temp : lobbyLikes.entrySet()) {
            if (temp.getValue().equals(maxLikes)) {
                return restaurantStore.get(parseRest(temp.getKey()));
            }
        }
        return null;
    }

    public Map<String, Integer> returnLobbyLikes(){
        return lobbyLikes;
    }

//    @Override
//    public Map<String, Integer> getLobbyMap(Lobby lobby){
//        String lobbyID = lobby.ID();
//        String[] parse;
//        Map<String, Integer> lobbyMap = null;
//        for (Map.Entry<String, Integer> l : lobbyLikes.entrySet()) {
//            parse = l.getKey().split(":");
//            if (parse[0].equals(lobbyID)) {
//                lobbyMap.put(l.getKey(), l.getValue());
//            }
//
//        }
//
//        return lobbyMap;
//    }

}