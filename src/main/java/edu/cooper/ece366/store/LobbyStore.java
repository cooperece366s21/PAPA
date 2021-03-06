package edu.cooper.ece366.store;


import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.List;
import java.util.UUID;

public interface LobbyStore {
    Lobby get(UUID id);

}
