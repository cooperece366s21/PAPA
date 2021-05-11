package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.sql.SQLException;
import java.util.Map;

public interface LobbyPreferences {

    public int storeToDB(DBconnection com_in, String lobbyID, String restaurantID) throws SQLException;

    public int incrementDB(DBconnection com_in, String lobbyID, String restaurantID) throws SQLException;


}