package edu.cooper.ece366.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import edu.cooper.ece366.framework.*;

public class Lobby {

    //Members
    private static final int INIT_SIZE = 4;
    private static final int MIN_SIZE = 1; //change to 2 for final implementation - solo q might be a different feature
    private static final int MAX_SIZE = 10;
    private static int numLobbies = 100000;

    private final int lobby_id;
    private final String lobby_code;
    private int numUsers = 1;
    private Vector<User> userList = new Vector<User>(INIT_SIZE);


// Dont think we will need another constructor
//    public lobbyImpl(Map<String, String> map) {
//        this.map = map;
//        this.maxSize = 4;
//    }

//    public lobbyImpl(int maxSize) {
//        this.maxSize = maxSize;
//        this.map = new HashMap<>();
//    }

//    @Override
//    public String get(final String key) {
//        return map.get(key);
//    }
//
//    @Override
//    public void put(final String key, final String value) {
//        validateKeySize(key);
//
//        map.put(key, value);
//    }

//    private void validateKeySize(final String key) {
//        if (key.length() > maxSize) {
//            throw new IllegalArgumentException("exceeded max size: " + key);
//        }
//    }

}