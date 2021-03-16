package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;

public class LobbyPreferencesImpl implements LobbyPreferences {

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

}