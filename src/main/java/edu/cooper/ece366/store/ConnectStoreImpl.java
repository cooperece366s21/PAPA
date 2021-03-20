package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;

import java.util.HashMap;
import java.util.Map;

public class ConnectStoreImpl implements ConnectStore {
    //might need to include @Override

    public Map<String, UserPreferences> connection = new HashMap<>();

    @Override
    public String getString(Lobby lobby, Restaurant restaurant, User user){
        String lobbyID = lobby.ID();
        String restID = restaurant.id();
        String userID = user.ID();

        String output = lobbyID + ':' + restID + ':' + userID;

        return output;

    }

//    @Override
//    public String parseLobby(String string){
//        String[] parse = string.split(":");
//
//        return parse[0];
//    }
//
//    @Override
//    public String parseRest(String string){
//        String[] parse = string.split(":");
//
//        return parse[1];
//    }
//
//    @Override
//    public String parseUser(String string){
//        String[] parse = string.split(":");
//
//        return parse[2];
//    }

    @Override
    public Integer updateConnection(Lobby lobby, Restaurant restaurant, User user, UserPreferences userPreferences){

        String key = getString(lobby, restaurant, user);
        connection.replace(key, userPreferences);
        return 0;
    }

    @Override
    public Integer putConnection(String key, UserPreferences userPreferences){
        connection.put(key, userPreferences);
        return 0;
    }

    @Override
    public Map<String, UserPreferences> getConnectionMap(){
        return connection;
    }

}
