package edu.cooper.ece366.auth.authLobby;

import edu.cooper.ece366.framework.Lobby;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AuthLobbyStoreImpl implements AuthLobbyStore {

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
        String token = String.valueOf(lobbsy.ID());
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
