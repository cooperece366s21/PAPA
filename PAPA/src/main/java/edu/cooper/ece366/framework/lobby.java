package edu.cooper.ece366.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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

    //Methods
    private void addUser();
    private void dropUser();
    private void beginSearch();
    private void openSettings();
    private void closeSettings();

    //Testing Methods - delete when implementing visuals
    private void printUsers(); // print users in list
    private void printid();

    // Essentially making each lobby have an ID from 100000-999999
    // Not sure right now whether or not this implementation is good because I am reusing values
    // Code to join can be the id in hexadecimal
    public Lobby() {
        if (numLobbies == 999999)
            numlobbies = 99999;
        this.lobby_id = ++numLobbies;
        this.lobby_code = Integer.toHexString(lobby_id);
    }

    private void addUser(User u){
        this.userlist.add(u);
        numUsers++;
    }
    private void dropUser(User u) {
        this.userlist.drop(u);
        numUsers--;
    }
    //Do these 3 later
    private void beginSearch() {}
    private void openSettings() {}
    private void closeSettings() {}

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