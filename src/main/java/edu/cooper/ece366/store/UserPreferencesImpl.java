package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPreferencesImpl implements UserPreferences {

    public Map<String, Enum> preferences = new HashMap<>();

    DataSource dbcp;
    Connection conn = null;

    @Override
    public String getUserID(User user){

        return user.ID();
    }

    @Override
    public String getRestID(Restaurant rest){
        return rest.id();
    }

    @Override
    public Integer updatePreferences(String RestID, Enum preference){

        preferences.put(RestID, preference);

        return 0;
    }

    @Override
    public Integer putPreferences(String RestID, Enum preference){

        preferences.put(RestID, preference);

        return 0;
    }

    @Override
    public Map<String, Enum> getPreferencesMap(){ return preferences; }

    @Override
    public int storeToDB(DBconnection con_in, User user, Restaurant rest, UserPreferences.preference preference) throws SQLException {
        this.dbcp = DBconnection.getDataSource();
        try{
            conn = dbcp.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_preferred_restaurants (user_id, rest_id, preference) VALUES (?, ?, ?);");
            stmt.setString(1, user.ID());
            stmt.setString(2, rest.id());
            stmt.setString(3, preference.toString());
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
