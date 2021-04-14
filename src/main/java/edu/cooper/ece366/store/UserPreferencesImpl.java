package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPreferencesImpl implements UserPreferences {

    public Map<String, Enum> preferences = new HashMap<>();

    @Override
    public String getUserID(User user){

        return user.ID();
    }

    @Override
    public String getString(User user, Restaurant restaurant) {
        String userID = user.ID();
        String restID = restaurant.id();

        String output = userID + ':' + restID;

        return output;

    }

    @Override
    public String getRestID(Restaurant rest){
        return rest.id();
    }

    @Override
    public Integer updatePreferences(User user, Restaurant restaurant, Enum preference){

        String key = getString(user, restaurant);
        preferences.put(key, preference);

        return 0;
    }

    @Override
    public Integer putPreferences(String RestID, Enum preference){

        preferences.put(RestID, preference);

        return 0;
    }

    @Override
    public Map<String, Enum> getPreferencesMap(){ return preferences; }

}
