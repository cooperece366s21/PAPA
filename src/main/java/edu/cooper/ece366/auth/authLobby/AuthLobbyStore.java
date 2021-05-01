package edu.cooper.ece366.auth.authLobby;

import edu.cooper.ece366.framework.Lobby;

import java.util.Optional;

public interface AuthLobbyStore {

    Boolean validateLobby(Lobby lobby, String cookie);

    Optional<Lobby> getLobby(String cookie);

    // returns cookie value
    String setLobby(Lobby lobby);

    // invalidates cookie
    void invalidateLobby(String cookie);
}
