package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.store.UserPreferences;

import java.util.Map;

public interface ConnectStore {
    public Map<String, UserPreferences> connection = null;

    public String getString(Lobby lobby, Restaurant restaurant, User user);

    public Map<String, UserPreferences> getConnectionMap();

    public String parseLobby(String string);

    public String parseRest(String string);

    public String parseUser(String string);

    public Integer updateConnection(Lobby lobby, Restaurant restaurant, User user, UserPreferences userPreferences);

    public Integer putConnection(String key, UserPreferences userPreferences);




}
