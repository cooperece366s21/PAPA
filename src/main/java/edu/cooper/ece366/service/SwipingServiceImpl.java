package edu.cooper.ece366.service;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.store.ConnectStore;
import edu.cooper.ece366.store.LobbyPreferences;
import edu.cooper.ece366.store.UserPreferences;
import edu.cooper.ece366.store.UserStore;

public class SwipingServiceImpl implements SwipingService {

    private ConnectStore connectStore;
    private LobbyPreferences lobbyPreferences;
    private UserPreferences userPreferences;

    public SwipingServiceImpl(ConnectStore connectStore, LobbyPreferences lobbyPreferences, UserPreferences userPreferences) {
        this.connectStore = connectStore;
        this.lobbyPreferences = lobbyPreferences;
        this.userPreferences = userPreferences;

    }

    @Override
    public Integer dislike(User user, Restaurant restaurant, Lobby lobby){

        //get the connection key
        String connectionKey = connectStore.getString(lobby,restaurant, user);

        //update the user preference
        String restID = connectStore.parseRest(connectionKey);
        userPreferences.updatePreferences(restID, UserPreferences.preference.dislike);

        //update the connection store
        connectStore.updateConnection(connectionKey, userPreferences);


        return 0;
    }

    @Override
    public Integer like(User user, Restaurant restaurant, Lobby lobby){

        //get the connection key
        String connectionKey = connectStore.getString(lobby,restaurant, user);

        //update the user preference
        String restID = connectStore.parseRest(connectionKey);
        userPreferences.updatePreferences(restID, UserPreferences.preference.like);

        //update the connection store
        connectStore.updateConnection(connectionKey, userPreferences);


        //update the lobby likes
        String lobbyPreferenceKey = lobbyPreferences.getString(lobby,restaurant);

        //add to the like counter for the restaurant
        Integer likeCount = lobbyPreferences.getNumOfLikes(lobbyPreferenceKey);
        likeCount++;

        lobbyPreferences.updateLobbyLikes(lobbyPreferenceKey, likeCount);

        return 0;
    }

}
