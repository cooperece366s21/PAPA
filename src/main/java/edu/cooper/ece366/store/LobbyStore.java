package edu.cooper.ece366.store;


import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;

public interface LobbyStore {
    Lobby getCurrentLobby(DBconnection com_in, String lobbyID) throws SQLException;

    List<Restaurant> getRestaurantList(DBconnection com_in, String lobbyID) throws SQLException;

    Lobby initLobby(DBconnection com_in, String ownerID, String location) throws SQLException, IOException; // insert into lobbies and setup lobbypreference

    Lobby initLobbyWithKeyword(DBconnection com_in, String ownerID, String location, String keyword) throws IOException, SQLException;

    Lobby joinLobby(DBconnection com_in, String userID, String lobbyID) throws SQLException;

    void leaveLobby(DBconnection com_in, String lobbyID, String userID) throws SQLException;

    Restaurant getRecommendation(DBconnection com_in, String lobbyID) throws SQLException;

    Lobby getCurrentLobbyByCode(DBconnection com_in, String lobbyCode) throws SQLException;

    List<String> getLobbyUsers(DBconnection com_in, String lobbyID) throws SQLException;
}
