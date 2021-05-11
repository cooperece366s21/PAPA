package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LobbyPreferencesImpl implements LobbyPreferences {

    DataSource dbcp;
    Connection conn = null;

    @Override
    public int storeToDB(DBconnection com_in, String lobbyID, String restID) throws SQLException {
        this.dbcp = com_in.getDataSource();
        this.conn = dbcp.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO lobby_preferred_restaurants (lobbyID, restaurantID, vote) " +
                            "VALUES (?, ?, 0);");
            stmt.setString(1, lobbyID);
            stmt.setString(2, restID);
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

    @Override
    public int incrementDB(DBconnection com_in, String lobbyID, String restaurantID) throws SQLException {
        this.dbcp = com_in.getDataSource();
        this.conn = dbcp.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE lobby_preferred_restaurants " +
                    "SET vote = vote+1 " +
                    "WHERE lobbyID = ? AND restaurantID = ?;");
            stmt.setString(1, lobbyID);
            stmt.setString(2, restaurantID);
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