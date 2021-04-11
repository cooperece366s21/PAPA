package edu.cooper.ece366.auth;

import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AuthStoreImpl implements AuthStore {

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

    //SAME BUT NOW FOR LOBBY
    // these should probably have an expiry time
    private static final Map<Lobby, String> tokenMapLobby = new ConcurrentHashMap<>();
    private static final Map<String, Lobby> reverseMapLobby = new ConcurrentHashMap<>();


    @Override
    public Boolean validateLobby(final Lobby lobby, final String cookie) {
        return tokenMapLobby.get(lobby).equals(cookie);
    }

    @Override
    public Optional<Lobby> getLobby(final String cookie) {
        return Optional.ofNullable(reverseMapLobby.get(cookie));
    }

    @Override
    public String setLobby(final Lobby lobby) {
        String token = String.valueOf(lobby.ID());
        tokenMapLobby.put(lobby, token);
        reverseMapLobby.put(token, lobby);
        return token;
    }

    @Override
    public void invalidateLobby(final String token) {
        Lobby lobby = reverseMapLobby.getOrDefault(token, null);
        if (lobby == null) {
            return;
        }
        tokenMapLobby.remove(lobby);
        reverseMapLobby.remove(token);
    }

}