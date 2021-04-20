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
    Lobby getCurrentLobby(String lobbyID) throws SQLException;

    List<Restaurant> getRestaurantList(String lobbyID) throws SQLException;

    Lobby initLobby(String ownerID, String lobbyID, String location) throws SQLException, IOException; // insert into lobbies and setup lobbypreference

    Lobby joinLobby(String userID, String lobbyID) throws SQLException;

    void leaveLobby(String lobbyID, String userID) throws SQLException;

    Restaurant getRecommendation(String lobbyID) throws SQLException;

}
