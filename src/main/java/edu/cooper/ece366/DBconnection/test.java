package edu.cooper.ece366.DBconnection;

import com.google.common.base.Splitter;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.store.LobbyStore;
import edu.cooper.ece366.store.LobbyStoreImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) throws SQLException, IOException {
        DBconnection dbcp = new DBconnection();
        LobbyStore lobbyStore = new LobbyStoreImpl();
        //List<User> userList = lobbyStore.getLobbyUsers(dbcp, "594281f5-8b95-4a5e-94c1-ff513bd0b3f5");
        //System.out.println(userList.toString());
        lobbyStore.initLobbyWithKeyword(dbcp, "016613c0-33cd-4d9f-ba4b-40c188bb52da", "nyc", "pizza");
    }
}
