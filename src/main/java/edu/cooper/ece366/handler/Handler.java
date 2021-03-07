package edu.cooper.ece366.handler;

import com.google.gson.Gson;
import edu.cooper.ece366.service.SwipingService;
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
    private final SwipingService swipingService;
    private final Gson gson;

    public Handler(
            UserStore userStore, LobbyStore lobbyStore, SwipingService swipingService, final Gson gson) {
        this.userStore = userStore;
        this.lobbyStore = lobbyStore;
        this.swipingService = swipingService;
        this.gson = gson;
    }


    //need to figure out issue with the type - request.params works with string but not UUID

    public User getUser(Request request) {
        String userID = request.params(":userID");
        return userStore.get(userID);
    }

    /*
    add this one lobby is done

    public User getLobby(Request request) {
        UUID lobbyID = UUID.fromString(request.params(":lobbyID"));
        return userStore.get(lobbyID);
    }
    */

    /*
    public SwipingService like(Request req){
        Restaurant restaurant = request.header("Restaurant");
        UUID userID = UUID.fromString(request.params(":userID"));
        User.get(userID).liked.append(restaurant)
        return System.out.println("Added to liked list");
    }
     */

    /*
    public SwipingService dislike(Request req){
        Restaurant restaurant = request.header("Restaurant");
        UUID userID = UUID.fromString(request.params(":userID"));
        User.get(userID).dislike.append(restaurant)
        return System.out.println("Added to dislike list");
    }
     */

    /*
    when working on front end add the following:
    - login and logout
    - cookie stuff
    - token stuff
     */

}
