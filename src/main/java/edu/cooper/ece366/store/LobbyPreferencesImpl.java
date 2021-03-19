package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.Map;

public class LobbyPreferencesImpl implements LobbyPreferences {

    public Map<String, Integer> lobbyLikes = null;

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
    public Integer updateLobbyLikes(String key, Integer likes) {
        lobbyLikes.replace(key, likes);

        return 0;
    }

    @Override
    public Integer putLobbyLikes(String key, Integer likes) {
        lobbyLikes.put(key, likes);

        return 0;
    }

    @Override
    public Integer getNumOfLikes(String string){
        Integer likeCount = lobbyLikes.get(string);

        return likeCount;

    }

    @Override
    public Integer lobbyLike(Lobby lobby, Restaurant restaurant){
        //update the lobby likes
        String lobbyPreferenceKey = getString(lobby,restaurant);

        //add to the like counter for the restaurant
        Integer likeCount = getNumOfLikes(lobbyPreferenceKey);
        likeCount++;

        updateLobbyLikes(lobbyPreferenceKey, likeCount);

        return 0;
    }

}