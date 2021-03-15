package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.Map;

public interface LobbyPreferences {

    public Map<String, Integer> lobbyLikes = null;

    public String getString(Lobby lobby, Restaurant restaurant);

    public Integer getNumOfLikes(String string);

    public String parseLobby(String string);

    public String parseRest(String string);

    public Integer updateLobbyLikes(String key, Integer likes);

    public Integer putLobbyLikes(String key, Integer likes);

    //public Integer getRecommendation(Map<String, Integer> lobbyLikes);

}
