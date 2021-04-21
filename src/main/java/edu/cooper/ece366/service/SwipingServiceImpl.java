package edu.cooper.ece366.service;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.store.ConnectStore;
import edu.cooper.ece366.store.LobbyPreferences;
import edu.cooper.ece366.store.UserPreferences;

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
    public Integer dislike(Lobby lobby, Restaurant restaurant, User user){

        //update the user preference
        userPreferences.updatePreferences(user, restaurant, UserPreferences.preference.dislike);

        //update the connection store
        connectStore.updateConnection(lobby, restaurant, user, userPreferences);

        return 0;
    }

    @Override
    public Integer like(Lobby lobby, Restaurant restaurant, User user){

        //update the user preference
        userPreferences.updatePreferences(user, restaurant, UserPreferences.preference.like);

        //update the connection store
        connectStore.updateConnection(lobby, restaurant, user, userPreferences);

        //update the lobby likes
        //lobbyPreferences.lobbyLike(lobby, restaurant);

        return 0;
    }

}
