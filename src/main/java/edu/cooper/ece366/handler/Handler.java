package edu.cooper.ece366.handler;

import com.google.gson.Gson;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.service.SwipingService;
import edu.cooper.ece366.store.*;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.categories.Restaurant;

import java.util.Map;

import spark.Request;

public class Handler {

//    private final ConnectStore connectStore;
//    private final LobbyPreferences lobbyPreferences;
//    private final UserPreferences userPreferences;
    private final UserStore userStore;
    private final LobbyStore lobbyStore;
    private final RestaurantStore restaurantStore;
    private final SwipingService swipingService;
    private final Gson gson;

    public Handler(
            //ConnectStore connectStore, LobbyPreferences lobbyPreferences, UserPreferences userPreferences,
            UserStore userStore, LobbyStore lobbyStore, RestaurantStore restaurantStore, SwipingService swipingService, final Gson gson) {
//        this.connectStore = connectStore;
//        this.lobbyPreferences = lobbyPreferences;
//        this.userPreferences = userPreferences;
        this.userStore = userStore;
        this.lobbyStore = lobbyStore;
        this.restaurantStore = restaurantStore;
        this.swipingService = swipingService;
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

        Map<String, Integer> hashMap = LobbyStoreImpl.LOBBY_MAP.get(lobbyID).restaurant_maps();

        return lobbyStore.getRecommendation(hashMap, restaurantStore);
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

        return swipingService.like(user, restaurant, lobby);
    }


    public Integer dislike(Request req){

        //HTTP POST /:userID/:lobbyID/:restID/dislike

        String userID1 = req.params(":userID");
        User user1 = userStore.get(userID1);

        String lobbyID1 = req.params(":lobbyID");
        Lobby lobby1 = lobbyStore.get(lobbyID1);

        String restID1 = req.params(":restID");
        Restaurant restaurant1 = restaurantStore.get(restID1);

        return swipingService.dislike(user1, restaurant1, lobby1);
    }

//    public Map<String, UserPreferences> getConnectionMap(Request req){
//
//        String userID = req.params(":userID");
//        User user = userStore.get(userID);
//
//        String lobbyID = req.params(":lobbyID");
//        Lobby lobby = lobbyStore.get(lobbyID);
//
//        String restID = req.params(":restID");
//        Restaurant restaurant = restaurantStore.get(restID);
//
//        String connectionKey = connectStore.getString(lobby, restaurant, user);
//
//        return connectStore.
//
//    }

    /*
    when working on front end add the following:
    - login and logout
    - cookie stuff
    - token stuff
     */

}
