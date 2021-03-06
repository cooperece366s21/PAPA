package edu.cooper.ece366.store;


public class LobbyStore {
    //Methods
    private void addUser(){};
    private void dropUser(){};
    //private void beginSearch(){}
    //private void openSettings(){};
    //private void closeSettings(){};

    //Testing Methods - delete when implementing visuals
    private void printUsers(){}; // print users in list
    private void printid(){};

    // Essentially making each lobby have an ID from 100000-999999
    // Not sure right now whether or not this implementation is good because I am reusing values
    // Code to join can be the id in hexadecimal
    //    public Lobby() {
    //        if (numLobbies == 999999)
    //            numLobbies = 99999;
    //        this.lobby_id = ++numLobbies;
    //        this.lobby_code = Integer.toHexString(lobby_id);
    //    }

    private void addUser(User u){
        this.userList.add(u);
        numUsers++;
    }
    private void dropUser(User u) {
        this.userList.remove(u);
        numUsers--;
    }
    //Do these 3 later
    private void beginSearch() {}
    private void openSettings() {}
    private void closeSettings() {}
    //    @Override
    //    public User get(final String id) {
    //        return userMap.get(id);
    //    }
}
