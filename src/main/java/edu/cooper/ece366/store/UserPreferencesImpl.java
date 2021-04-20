package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.UserBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPreferencesImpl implements UserPreferences {

    public Map<String, Enum> preferences = new HashMap<>();

    DataSource dbcp;
    Connection conn = null;

    @Override
    public String getUserID(User user) {

        return user.ID();
    }

    @Override
    public String getString(User user, Restaurant restaurant) {
        String userID = user.ID();
        String restID = restaurant.ID();

        String output = userID + ':' + restID;

        return output;

    }

    @Override
    public String getRestID(Restaurant rest){
        return rest.ID();
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
    public Map<String, Enum> getPreferencesMap(){ return null; }

    @Override
    public int updateDB(String userID, String restID, UserPreferences.preference preference) throws SQLException{
        this.dbcp = DBconnection.getDataSource();
        try{
            conn = dbcp.getConnection();
            PreparedStatement getUsername = conn.prepareStatement("UPDATE user_preferred_restaurants SET preference=? WHERE userID=? AND restaurantID=?;");
            getUsername.setString(1, preference.name());
            getUsername.setString(2, userID);
            getUsername.setString(3, restID);
            try {
                ResultSet rs = getUsername.executeQuery();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException err) {
            System.err.println("Error when connecting to database.");
            err.printStackTrace();
            return -1;
        }
        finally {
            conn.close();
        }
        return 0;
    }

    @Override
    public int storeToDB(String userID, String restID, UserPreferences.preference preference) throws SQLException {
        this.dbcp = DBconnection.getDataSource();
        try{
            conn = dbcp.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_preferred_restaurants (userID, restaurantID, preference) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE preference = ?;");
            stmt.setString(1, userID);
            stmt.setString(2, restID);
            stmt.setString(3, preference.toString());
            stmt.setString(4, preference.toString());
            try{
                stmt.executeUpdate();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException err) {
            System.err.println("Error when connecting to database.");
            err.printStackTrace();
            return -1;
        }
        finally {
            conn.close();
        }
        return  0;
    }

}
