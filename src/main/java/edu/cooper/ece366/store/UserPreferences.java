package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.List;
import java.util.Map;

public interface UserPreferences {

    public enum preference {dislike, like}

    public String getUserID(User user);
    public String getRestID(Restaurant rest);
    public Map<String, preference> preferences = null;

    public Integer updatePreferences(User user, Restaurant restaurant, Enum preference);

    public Integer putPreferences(String RestID, Enum preference);

    public Map<String, Enum> getPreferencesMap();

    public String getString(User user, Restaurant restaurant);
}
