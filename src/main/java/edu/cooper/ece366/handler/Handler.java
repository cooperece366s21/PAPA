package edu.cooper.ece366.handler;

import com.google.gson.Gson;
import edu.cooper.ece366.auth.authLobby.AuthLobby;
import edu.cooper.ece366.auth.authLobby.AuthLobbyStore;
import edu.cooper.ece366.auth.authLobby.AuthLobbyStoreImpl;
import edu.cooper.ece366.auth.authUser.AuthUser;
import edu.cooper.ece366.auth.authUser.AuthUserStore;
import edu.cooper.ece366.auth.authUser.AuthUserStoreImpl;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.store.*;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.store.RestaurantStore;
import edu.cooper.ece366.DBconnection.DBconnection;

import java.io.IOException;
import java.sql.SQLException;
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
        String lobbyID = request.params(":lobbyID");

        return lobbyStore.getRestaurantList(com_in, lobbyID);
    }

    public String getRestaurantUrl(final Request request) throws SQLException {
        String restaurantID = request.params(":restaurantID");
        return restaurantStore.getRestaurantUrl(com_in, restaurantID);
    }

    public List<String> getRestaurantUrlList(final Request request) throws SQLException {
        String lobbyID = request.params(":lobbyID");
        return lobbyStore.getImageURLs(com_in, lobbyID);
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

}
