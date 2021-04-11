package edu.cooper.ece366.auth.authUser;

import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AuthUserStoreImpl implements AuthUserStore {

    // these should probably have an expiry time
    private static final Map<User, String> tokenMapUser = new ConcurrentHashMap<>();
    private static final Map<String, User> reverseMapUser = new ConcurrentHashMap<>();


    @Override
    public Boolean validateUser(final User user, final String cookie) {
        return tokenMapUser.get(user).equals(cookie);
    }

    @Override
    public Optional<User> getUser(final String cookie) {
        return Optional.ofNullable(reverseMapUser.get(cookie));
    }

    @Override
    public String setUser(final User user) {
        String token = String.valueOf(user.ID());
        tokenMapUser.put(user, token);
        reverseMapUser.put(token, user);
        return token;
    }

    @Override
    public void invalidateUser(final String token) {
        User user = reverseMapUser.getOrDefault(token, null);
        if (user == null) {
            return;
        }
        tokenMapUser.remove(user);
        reverseMapUser.remove(token);
    }
}