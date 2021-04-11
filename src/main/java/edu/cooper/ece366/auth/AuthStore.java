package edu.cooper.ece366.auth;

import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import java.util.Optional;

public interface AuthStore {

    // validates cookie value
    Boolean validateUser(User user, String cookie);
    Boolean validateLobby(Lobby lobby, String cookie);

    Optional<User> getUser(String cookie);
    Optional<Lobby> getLobby(String cookie);

    // returns cookie value
    String setUser(User user);

    // returns cookie value
    String setLobby(Lobby lobby);

    // invalidates cookie
    void invalidateUser(String cookie);

    // invalidates cookie
    void invalidateLobby(String cookie);

}
