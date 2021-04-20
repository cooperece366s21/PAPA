package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.sql.SQLException;
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

    public int storeToDB(String userID, String restID, UserPreferences.preference preference) throws SQLException;

    public int updateDB(String userID, String restID, UserPreferences.preference preference) throws SQLException;

//    public preference get(String userID, String restID) throws SQLException

    public String getString(User user, Restaurant restaurant);
}