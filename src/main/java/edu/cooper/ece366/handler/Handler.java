package edu.cooper.ece366.handler;

import com.google.gson.Gson;
import edu.cooper.ece366.auth.Auth;
import edu.cooper.ece366.auth.AuthStore;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.service.SwipingService;
import edu.cooper.ece366.store.*;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.categories.Restaurant;

import java.util.Map;
import java.util.Optional;

import spark.Request;
import spark.Response;

public class Handler {

    private final ConnectStore connectStore;
    private final LobbyPreferences lobbyPreferences;
    private final UserPreferences userPreferences;
    private final UserStore userStore;
    private final LobbyStore lobbyStore;
    private final RestaurantStore restaurantStore;
    private final SwipingService swipingService;
    private final AuthStore authStore;
    private final Gson gson;

    public Handler(
            ConnectStore connectStore, LobbyPreferences lobbyPreferences, UserPreferences userPreferences,
            UserStore userStore, LobbyStore lobbyStore, RestaurantStore restaurantStore, SwipingService swipingService,
            final AuthStore authStore, final Gson gson) {
        this.connectStore = connectStore;
        this.lobbyPreferences = lobbyPreferences;
        this.userPreferences = userPreferences;
        this.userStore = userStore;
        this.lobbyStore = lobbyStore;
        this.restaurantStore = restaurantStore;
        this.swipingService = swipingService;
        this.authStore = authStore;
        this.gson = gson;
    }


    //need to figure out issue with the type - request.params works with string but not UUID

    public User getUser(Request request) {
        String userID = request.params(":userID");
        return userStore.get(userID);
    }

    public Lobby getLobby(Request request) {
        String lobbyID = request.params(":lobbyID");
        return lobbyStore.get(lobbyID);
    }

    /*
    public List<Restaurant> getRestList(Request req){

    return lobbyStore.generateRestList();
    }
     */
/*
    public Map<String, Integer> start(Request req){

        UUID lobbyID = UUID.fromString(req.params(":lobbyID"));

        return lobbyStore.initializeLobby(lobbyStore.generateRestList(), lobbyStore.get(lobbyID));
    }

 */

    public Restaurant result(Request req){

        String lobbyID = req.params(":lobbyID");
        Lobby lobby = lobbyStore.get(lobbyID);

        return lobbyPreferences.getRecommendation(lobby, restaurantStore);
    }

    public Integer like(Request req){
        //HTTP POST /:userID/:lobbyID/:restID/like
        //curl -s localhost:4567/user/1/feed
        //curl -s localhost:number/user/:userID/lobby/:lobbyID/restaurant/:restID/like

        String userID = req.params(":userID");
        User user = userStore.get(userID);

        String lobbyID = req.params(":lobbyID");
        Lobby lobby = lobbyStore.get(lobbyID);

        String restID = req.params(":restID");
        Restaurant restaurant = restaurantStore.get(restID);

        return swipingService.like(lobby, restaurant, user);
    }


    public Integer dislike(Request req){

        //HTTP POST /:userID/:lobbyID/:restID/dislike

        String userID1 = req.params(":userID");
        User user1 = userStore.get(userID1);

        String lobbyID1 = req.params(":lobbyID");
        Lobby lobby1 = lobbyStore.get(lobbyID1);

        String restID1 = req.params(":restID");
        Restaurant restaurant1 = restaurantStore.get(restID1);

        return swipingService.dislike(lobby1, restaurant1, user1);
    }

    public Map<String, Integer> initLobbyMap(Request req){
        String lobbyID = req.params(":lobbyID");
        Lobby lobby = lobbyStore.get(lobbyID);


        return lobbyPreferences.initLobbyLikes(lobbyStore, lobby);
    }

    public Map<String, Integer> getLobbyMap(){

        return lobbyPreferences.returnLobbyLikes();

    }

    public Map<String, UserPreferences> getConnectionMap(){

        return connectStore.getConnectionMap();

    }

    public User login(final Request request, final Response response) {
        Auth auth = gson.fromJson(request.body(), Auth.class);
        User user = userStore.get(auth.username());
        if ("password".equals(auth.password())) {
            // authorized
            String token = authStore.setUser(user);
            response.header("papauser", token);
            response.status(200);
            return user;
        }
        // unauthorized
        response.status(401);

        return null;
    }

    public Object logout(final Request req, final Response res) {
        authStore.invalidate(req.headers("papauser"));
        res.status(200);

        return null;
    }

    public User me(final Request req, final Response res) {
        String token = req.headers("papauser");
        if (token == null) {
            res.status(401);
            return null;
        }
        Optional<User> user = authStore.getUser(token);
        if (user.isPresent()) {
            return user.get();
        } else {
            res.status(401);
            return null;
        }
    }

    /*
    when working on front end add the following:
    - login and logout
    - cookie stuff
    - token stuff
     */

}
