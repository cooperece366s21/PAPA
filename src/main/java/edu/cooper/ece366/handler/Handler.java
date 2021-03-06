package edu.cooper.ece366.handler;

import com.google.gson.Gson;
import edu.cooper.ece366.store.LobbyStore;
import edu.cooper.ece366.store.UserStore;
import edu.cooper.ece366.framework.User;
import java.util.Optional;
import java.util.UUID;
import spark.Request;
import spark.Response;

public class Handler {

    private final UserStore userStore;
    private final LobbyStore lobbyStore;
    private final Gson gson;

    public Handler(
            UserStore userStore, LobbyStore lobbyStore, final Gson gson) {
        this.userStore = userStore;
        this.lobbyStore = lobbyStore;
        this.gson = gson;
    }

    /*
    need to figure out issue with the type - request.params works with string but not UUID

    public User getUser(Request request) {
        UUID userID = request.params(":userID");
        return userStore.get(userID);
    }
     */

    /*
    when working on front end add the following:
    - login and logout
    - cookie stuff
    - token stuff
     */

}
