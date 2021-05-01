package edu.cooper.ece366.handler;

import com.google.gson.Gson;
import edu.cooper.ece366.auth.authLobby.AuthLobby;
import edu.cooper.ece366.auth.authLobby.AuthLobbyStore;
import edu.cooper.ece366.auth.authLobby.AuthLobbyStoreImpl;
import edu.cooper.ece366.auth.authUser.AuthUser;
import edu.cooper.ece366.auth.authUser.AuthUserStore;
import edu.cooper.ece366.auth.authUser.AuthUserStoreImpl;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.UserBuilder;
import edu.cooper.ece366.service.SwipingService;
import edu.cooper.ece366.service.SwipingServiceImpl;
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
import java.util.UUID;

import spark.Request;
import spark.Response;


public class Handler {

    private final LobbyPreferences lobbyPreferences;
    private final UserPreferences userPreferences;
    private final UserStore userStore;
    private final LobbyStore lobbyStore;
    private final RestaurantStore restaurantStore;
    //private final SwipingService swipingService;
    private final AuthUserStore authUserStore;
    private final AuthLobbyStore authLobbyStore;
    private final Gson gson;
    private final DBconnection com_in;

    public Handler(final Gson gson){
        this.userStore = new UserStoreImpl();
        this.userPreferences = new UserPreferencesImpl();
        this.lobbyStore = new LobbyStoreImpl();
        this.lobbyPreferences = new LobbyPreferencesImpl();
        this.restaurantStore = new RestaurantStoreImpl();
        //this.swipingService = new SwipingServiceImpl();
        this.authUserStore = new AuthUserStoreImpl();
        this.authLobbyStore = new AuthLobbyStoreImpl();
        this.gson = gson;
        this.com_in = new DBconnection();
    }

    public User getCurrentUser(final Request request, final Response res) throws SQLException {
        String userID = request.params(":userID");
        return userStore.get(com_in, userID);
    }

    public Lobby getCurrentLobby(final Request request) throws SQLException {
        String lobbyID = request.params(":lobbyID");

        return lobbyStore.getCurrentLobby(com_in, lobbyID);
    }

    public User signUp(final Request request) throws SQLException {
        String username = request.params(":username");
        String password = request.params(":password");
        String userID = UUID.randomUUID().toString();
        userStore.storeToDB(com_in, userID,username,password);

        return userStore.get(com_in, userID);
    }

//    copy and paste these for updating user preference
//    userPreferences.updateDB(userID,restID,preference); take in enum of preference type
//    userPreferences.storeToDB(userID,restID,preference);

//    copy and paste these for updating lobby preference
//    lobbyPreferences.incrementDB(lobbyID,restID);
//    lobbyPreferences.storeToDB(lobbyID,restID);


    public User login(final Request request, final Response response) throws SQLException {
        AuthUser auth = gson.fromJson(request.body(), AuthUser.class);
        User user = userStore.get(com_in, auth.username());
        //DOUBLE CHECK TO MAKE SURE USER.PASSWORD() WILL GET YOU THE PASSWORD STRING
        if ((user.password()).equals(auth.password())) {
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

    public Lobby createLobby(final Request request) throws IOException, SQLException {
        String ownerID = request.params(":userID");
        String location = request.params(":location");
        return lobbyStore.initLobby(com_in, ownerID, location);
    }

    public Lobby createLobbyWithKeyword(final Request request) throws IOException, SQLException {
        String ownerID = request.params(":userID");
        String location = request.params(":location");
        String keyword = request.params(":keyword");
        return lobbyStore.initLobbyWithKeyword(com_in, ownerID, location, keyword);
    }

    public Lobby joinLobby(final Request request, final Response response) throws SQLException {
        String userID = request.headers("papauser");
        AuthLobby auth = gson.fromJson(request.body(), AuthLobby.class);
        Lobby lobby = lobbyStore.getCurrentLobbyByCode(com_in, auth.code());
        String token = authLobbyStore.setLobby(lobby);
        response.header("papalobby", token);
        response.status(200);

        return lobbyStore.joinLobby(com_in, userID, lobby.ID());
    }

    public List<String> getLobbyUsers(final Request request) throws SQLException {
        String lobbyID = request.headers("papalobby");
        return lobbyStore.getLobbyUsers(com_in, lobbyID);
    }

    public List<Restaurant> getRestaurantList(final Request request) throws SQLException {
        String lobbyID = request.headers("papalobby");

        return lobbyStore.getRestaurantList(com_in, lobbyID);
    }

    public String getRestaurantInfo(final Request request) throws SQLException {
        String restaurantID = request.params(":restaurantID");
        return restaurantStore.getRestaurantInfo(com_in, restaurantID);
    }

    public Integer like(Request request) throws SQLException {
        String lobbyID = request.params(":lobbyID");
        String userID = request.params(":userID");
        String restaurantID = request.params(":restID");
        lobbyPreferences.incrementDB(com_in, lobbyID, restaurantID);

        userPreferences.storeToDB(com_in, userID, restaurantID, UserPreferences.preference.like);
        return null;
    }

    public Integer dislike(Request request) throws SQLException {
        String userID = request.params(":userID");
        String restaurantID = request.params("restID");
        userPreferences.storeToDB(com_in, userID, restaurantID, UserPreferences.preference.dislike);
        return null;
    }

    public Restaurant getRecommendation(Request request) throws SQLException {
        String lobbyID = request.params("lobbyID");

        return lobbyStore.getRecommendation(com_in, lobbyID);
    }

    public Object leaveLobby(Request request, final Response response) throws SQLException {
        String userID = request.headers("papauser");
        String lobbyID = request.headers("papalobby");
        lobbyStore.leaveLobby(com_in, lobbyID, userID);
        authLobbyStore.invalidateLobby(lobbyID);
        response.status(200);
        return null;
    }

    public Object logout(final Request request, final Response response){
        authUserStore.invalidateUser(request.headers("papauser"));
        response.status(200);

        return null;
    }

//    private final ConnectStore connectStore;
//    private final LobbyPreferences lobbyPreferences;
//    private final UserPreferences userPreferences;
//    private final UserStore userStore;
//    private final LobbyStore lobbyStore;
//    private final RestaurantStore restaurantStore;
//    private final SwipingService swipingService;
//    private final AuthUserStore authUserStore;
//    private final AuthLobbyStore authLobbyStore;
//    private final Gson gson;
//
//    public Handler(
//            ConnectStore connectStore, LobbyPreferences lobbyPreferences, UserPreferences userPreferences,
//            UserStore userStore, LobbyStore lobbyStore, RestaurantStore restaurantStore, SwipingService swipingService,
//            final AuthUserStore authUserStore, final AuthLobbyStore authLobbyStore, final Gson gson) {
//        this.connectStore = connectStore;
//        this.lobbyPreferences = lobbyPreferences;
//        this.userPreferences = userPreferences;
//        this.userStore = userStore;
//        this.lobbyStore = lobbyStore;
//        this.restaurantStore = restaurantStore;
//        this.swipingService = swipingService;
//        this.authUserStore = authUserStore;
//        this.authLobbyStore = authLobbyStore;
//        this.gson = gson;
//    }
//
//
//    //need to figure out issue with the type - request.params works with string but not UUID
//
//    // User
//    public int addUser(Request request) throws SQLException {
//        String userID = request.params(":userID");
//        String username = request.params(":userName");
//        String password = request.params(":userPassword");
////        User user = new UserBuilder().ID(userID)
////                .name(username)
////                .password(password)
////                .build();
//        return userStore.storeToDB(userID,username,password);
//    }
//
//    public User getUser(Request request) throws SQLException {
//        String userID = request.params(":userID");
//        return userStore.get(userID);
//    }
//
//    public int updateUser(Request request) throws SQLException {
//        String userID = request.params(":userID");
//        String name = request.params(":name");
//        return userStore.updateDB(userID, name);
//    }
//
//    // Lobby
//    public Lobby getLobby(Request request) throws SQLException {
//        String lobbyID = request.params(":lobbyID");
//        return lobbyStore.getCurrentLobby(lobbyID);
//    }
//
//    public Lobby getCurrentLobby(Request request) throws IOException, SQLException {
//        String ownerID = request.params(":ownerID");
//        String lobbyID = request.params(":lobbyID");
//        String location = request.params(":location");
//        return lobbyStore.initLobby(ownerID, lobbyID, location);
//    }
//
//    // User Preferences
//    public int addUserPreference(Request request) throws SQLException {
//        String userID = request.params(":userID");
//        String restID = request.params(":restID");
//        UserPreferences.preference preference = Enum.valueOf(UserPreferences.preference.class, request.params(":userPassword"));
//
//        return userPreferences.storeToDB(userID, restID, preference);
//    }
//
//    public int updateUserPreference(Request request) throws SQLException {
//        String userID = request.params(":userID");
//        String restID = request.params(":restID");
//        UserPreferences.preference preference = Enum.valueOf(UserPreferences.preference.class, request.params(":userPassword"));
//
//        return userPreferences.updateDB(userID, restID, preference);
//    }
//
//
//    /*
//    public List<Restaurant> getRestList(Request req){
//
//    return lobbyStore.generateRestList();
//    }
//     */
///*
//    public Map<String, Integer> start(Request req){
//
//        UUID lobbyID = UUID.fromString(req.params(":lobbyID"));
//
//        return lobbyStore.initializeLobby(lobbyStore.generateRestList(), lobbyStore.get(lobbyID));
//    }
//
// */
//
//    public Restaurant result(Request req) throws SQLException {
//
//        String lobbyID = req.params(":lobbyID");
//        Lobby lobby = lobbyStore.getCurrentLobby(lobbyID);
//
//        return null;
//    }
//
//    public Integer like(Request req) throws SQLException {
//        //HTTP POST /:userID/:lobbyID/:restID/like
//        //curl -s localhost:4567/user/1/feed
//        //curl -s localhost:number/user/:userID/lobby/:lobbyID/restaurant/:restID/like
//
//        String userID = req.params(":userID");
//        User user = userStore.get(userID);
//
//        String lobbyID = req.params(":lobbyID");
//        Lobby lobby = lobbyStore.getCurrentLobby(lobbyID);
//
//        String restID = req.params(":restID");
//        Restaurant restaurant = restaurantStore.get(restID);
//
//        return swipingService.like(lobby, restaurant, user);
//    }
//
//
//    public Integer dislike(Request req) throws SQLException {
//
//        //HTTP POST /:userID/:lobbyID/:restID/dislike
//
//        String userID1 = req.params(":userID");
//        User user1 = userStore.get(userID1);
//
//        String lobbyID1 = req.params(":lobbyID");
//        Lobby lobby1 = lobbyStore.getCurrentLobby(lobbyID1);
//
//        String restID1 = req.params(":restID");
//        Restaurant restaurant1 = restaurantStore.get(restID1);
//
//        return swipingService.dislike(lobby1, restaurant1, user1);
//    }
//
//    public List<Restaurant> getRestaurantList(Request req) throws SQLException {
//        String lobbyID = req.params(":lobbyID");
//        //String lobbyID = req.headers("papalobby");
//
//        return lobbyStore.getRestaurantList(lobbyID);
//    }
//
//    public List<Restaurant> getLobbyFeed(Request req) throws SQLException {
//        String lobbyID = req.params(":lobbyID");
//        //String lobbyID = req.headers("papalobby");
//        Lobby lobby = lobbyStore.getCurrentLobby(lobbyID);
//
//        //List<String> lobbyListString = lobbyStore.getLobbyList(lobby);
//        return null;
//    }
//
//    public Map<String, Integer> initLobbyMap(Request req) throws SQLException {
//        String lobbyID = req.params(":lobbyID");
//        Lobby lobby = lobbyStore.getCurrentLobby(lobbyID);
//
//
//        return null;
//    }
//
//    public Map<String, Integer> getLobbyMap(){
//
//        return null;
//
//    }
//
//    public Map<String, UserPreferences> getConnectionMap(){
//
//        return connectStore.getConnectionMap();
//
//    }
//
//    public Map<String, Enum> getPreferenceMap(){
//
//        return userPreferences.getPreferencesMap();
//
//    }
//
//    public User login(final Request request, final Response response) throws SQLException {
//        AuthUser auth = gson.fromJson(request.body(), AuthUser.class);
//        User user = userStore.get(auth.username());
//        if ("password".equals(auth.password())) {
//            // authorized
//            String token = authUserStore.setUser(user);
//            response.header("papauser", token);
//            response.status(200);
//            return user;
//        }
//        // unauthorized
//        response.status(401);
//
//        return null;
//    }
//
//    public Object logout(final Request req, final Response res) {
//        authUserStore.invalidateUser(req.headers("papauser"));
//        res.status(200);
//
//        return null;
//    }
//
//    public Lobby joinLobby(final Request request, final Response response) throws SQLException {
//        AuthLobby auth = gson.fromJson(request.body(), AuthLobby.class);
//        Lobby lobby = lobbyStore.getCurrentLobbyByCode(auth.code());
///*        if (lobby.equals(auth.code())) {
//            // authorized
//            String token = authLobbyStore.setLobby(lobby);
//            response.header("papalobby", token);
//            response.status(200);
//            return lobby;
//        }*/
////        // unauthorized
////        response.status(401);
//        String token = authLobbyStore.setLobby(lobby);
//        response.header("papalobby", token);
//        response.status(200);
//        return lobby;
//
// //       return null;
//    }
//
//    public Object leaveLobby(final Request req, final Response res) throws SQLException {
//        authLobbyStore.invalidateLobby(req.headers("papalobby"));
//        res.status(200);
//        lobbyStore.leaveLobby(req.headers("papalobby"), req.headers("papauser"));
//        return null;
//    }
//
//    public User me(final Request req, final Response res) {
//        String token = req.headers("papauser");
//        if (token == null) {
//            res.status(401);
//            return null;
//        }
//        Optional<User> user = authUserStore.getUser(token);
//        if (user.isPresent()) {
//            return user.get();
//        } else {
//            res.status(401);
//            return null;
//        }
//    }

    /*
    when working on front end add the following:
    - login and logout
    - cookie stuff
    - token stuff
     */

}
