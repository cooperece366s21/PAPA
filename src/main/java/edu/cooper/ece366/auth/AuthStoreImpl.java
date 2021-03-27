package edu.cooper.ece366.auth;

import edu.cooper.ece366.framework.User;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AuthStoreImpl implements AuthStore {

    // these should probably have an expiry time
    private static final Map<User, String> tokenMap = new ConcurrentHashMap<>();
    private static final Map<String, User> reverseMap = new ConcurrentHashMap<>();

    @Override
    public Boolean validate(final User user, final String cookie) {
        return tokenMap.get(user).equals(cookie);
    }

    @Override
    public Optional<User> getUser(final String cookie) {
        return Optional.ofNullable(reverseMap.get(cookie));
    }

    @Override
    public String setUser(final User user) {
        String token = String.valueOf(user.ID());
        tokenMap.put(user, token);
        reverseMap.put(token, user);
        return token;
    }

    @Override
    public void invalidate(final String token) {
        User user = reverseMap.getOrDefault(token, null);
        if (user == null) {
            return;
        }
        tokenMap.remove(user);
        reverseMap.remove(token);
    }
}