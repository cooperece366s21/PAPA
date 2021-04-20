package edu.cooper.ece366.handler;

import com.google.gson.Gson;
import edu.cooper.ece366.auth.authLobby.AuthLobby;
import edu.cooper.ece366.auth.authLobby.AuthLobbyStore;
import edu.cooper.ece366.auth.authUser.AuthUser;
import edu.cooper.ece366.auth.authUser.AuthUserStore;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.UserBuilder;
import edu.cooper.ece366.service.SwipingService;
import edu.cooper.ece366.store.*;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.store.RestaurantStore;
import edu.cooper.ece366.DBconnection.DBconnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.List;

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
    private final AuthUserStore authUserStore;
    private final AuthLobbyStore authLobbyStore;
    private final Gson gson;

    public Handler(
            ConnectStore connectStore, LobbyPreferences lobbyPreferences, UserPreferences userPreferences,
            UserStore userStore, LobbyStore lobbyStore, RestaurantStore restaurantStore, SwipingService swipingService,
            final AuthUserStore authUserStore, final AuthLobbyStore authLobbyStore, final Gson gson) {
        this.connectStore = connectStore;
        this.lobbyPreferences = lobbyPreferences;
        this.userPreferences = userPreferences;
        this.userStore = userStore;
        this.lobbyStore = lobbyStore;
        this.restaurantStore = restaurantStore;
        this.swipingService = swipingService;
        this.authUserStore = authUserStore;
        this.authLobbyStore = authLobbyStore;
        this.gson = gson;
    }


    //need to figure out issue with the type - request.params works with string but not UUID

    // User
    public int addUser(Request request) throws SQLException {
        String userID = request.params(":userID");
        String username = request.params(":userName");
        String password = request.params(":userPassword");
//        User user = new UserBuilder().ID(userID)
//                .name(username)
//                .password(password)
//                .build();
        return userStore.storeToDB(userID,username,password);
    }

    public User getUser(Request request) throws SQLException {
        String userID = request.params(":userID");
        return userStore.get(userID);
    }

    public int updateUser(Request request) throws SQLException {
        String userID = request.params(":userID");
        String newname = request.params(":newname");
        return userStore.updateDB(userID, newname);
    }

    // Lobby
    public Lobby getLobby(Request request) throws SQLException {
        String lobbyID = request.params(":lobbyID");
        return lobbyStore.getCurrentLobby(lobbyID);
    }

    public Lobby getCurrentLobby(Request request) throws IOException, SQLException {
        String ownerID = request.params(":ownerID");
        String lobbyID = request.params(":lobbyID");
        String location = request.params(":location");
        return lobbyStore.initLobby(ownerID, lobbyID, location);
    }

    // User Preferences
    public int addUserPreference(Request request) throws SQLException {
        String userID = request.params(":userID");
        String restID = request.params(":restID");
        UserPreferences.preference preference = Enum.valueOf(UserPreferences.preference.class, request.params(":userPassword"));

        return userPreferences.storeToDB(userID, restID, preference);
    }

    public int updateUserPreference(Request request) throws SQLException {
        String userID = request.params(":userID");
        String restID = request.params(":restID");
        UserPreferences.preference preference = Enum.valueOf(UserPreferences.preference.class, request.params(":userPassword"));

        return userPreferences.updateDB(userID, restID, preference);
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

    public Restaurant result(Request req) throws SQLException {

        String lobbyID = req.params(":lobbyID");
        Lobby lobby = lobbyStore.getCurrentLobby(lobbyID);

        return null;
    }

    public Integer like(Request req) throws SQLException {
        //HTTP POST /:userID/:lobbyID/:restID/like
        //curl -s localhost:4567/user/1/feed
        //curl -s localhost:number/user/:userID/lobby/:lobbyID/restaurant/:restID/like

        String userID = req.params(":userID");
        User user = userStore.get(userID);

        String lobbyID = req.params(":lobbyID");
        Lobby lobby = lobbyStore.getCurrentLobby(lobbyID);

        String restID = req.params(":restID");
        Restaurant restaurant = restaurantStore.get(restID);

        return swipingService.like(lobby, restaurant, user);
    }


    public Integer dislike(Request req) throws SQLException {

        //HTTP POST /:userID/:lobbyID/:restID/dislike

        String userID1 = req.params(":userID");
        User user1 = userStore.get(userID1);

        String lobbyID1 = req.params(":lobbyID");
        Lobby lobby1 = lobbyStore.getCurrentLobby(lobbyID1);

        String restID1 = req.params(":restID");
        Restaurant restaurant1 = restaurantStore.get(restID1);

        return swipingService.dislike(lobby1, restaurant1, user1);
    }

    public List<Restaurant> getRestaurantList(Request req) throws SQLException {
        String lobbyID = req.params(":lobbyID");
        //String lobbyID = req.headers("papalobby");

        return lobbyStore.getRestaurantList(lobbyID);
    }

    public List<Restaurant> getLobbyFeed(Request req) throws SQLException {
        String lobbyID = req.params(":lobbyID");
        //String lobbyID = req.headers("papalobby");
        Lobby lobby = lobbyStore.getCurrentLobby(lobbyID);

        //List<String> lobbyListString = lobbyStore.getLobbyList(lobby);
        return null;
    }

    public Map<String, Integer> initLobbyMap(Request req) throws SQLException {
        String lobbyID = req.params(":lobbyID");
        Lobby lobby = lobbyStore.getCurrentLobby(lobbyID);


        return null;
    }

    public Map<String, Integer> getLobbyMap(){

        return null;

    }

    public Map<String, UserPreferences> getConnectionMap(){

        return connectStore.getConnectionMap();

    }

    public Map<String, Enum> getPreferenceMap(){

        return userPreferences.getPreferencesMap();

    }

    public User login(final Request request, final Response response) throws SQLException {
        AuthUser auth = gson.fromJson(request.body(), AuthUser.class);
        User user = userStore.get(auth.username());
        if ("password".equals(auth.password())) {
            // authorized
            String token = authUserStore.setUser(user);
            response.header("papauser", token);
            response.status(200);
            return user;
        }
        // unauthorized
        response.status(401);

        return null;
    }

    public Object logout(final Request req, final Response res) {
        authUserStore.invalidateUser(req.headers("papauser"));
        res.status(200);

        return null;
    }

    public Lobby joinLobby(final Request request, final Response response) throws SQLException {
        AuthLobby auth = gson.fromJson(request.body(), AuthLobby.class);
        Lobby lobby = lobbyStore.getCurrentLobby(auth.code());
/*        if (lobby.equals(auth.code())) {
            // authorized
            String token = authLobbyStore.setLobby(lobby);
            response.header("papalobby", token);
            response.status(200);
            return lobby;
        }*/
//        // unauthorized
//        response.status(401);
        String token = authLobbyStore.setLobby(lobby);
        response.header("papalobby", token);
        response.status(200);
        return lobby;

 //       return null;
    }

    public Object leaveLobby(final Request req, final Response res) {
        authLobbyStore.invalidateLobby(req.headers("papalobby"));
        res.status(200);

        return null;
    }

    public User me(final Request req, final Response res) {
        String token = req.headers("papauser");
        if (token == null) {
            res.status(401);
            return null;
        }
        Optional<User> user = authUserStore.getUser(token);
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
