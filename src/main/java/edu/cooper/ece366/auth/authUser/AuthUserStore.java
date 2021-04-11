package edu.cooper.ece366.auth.authUser;

import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.Optional;

public interface AuthUserStore {

    // validates cookie value
    Boolean validateUser(User user, String cookie);

    Optional<User> getUser(String cookie);

    // returns cookie value
    String setUser(User user);

    // invalidates cookie
    void invalidateUser(String cookie);

}
