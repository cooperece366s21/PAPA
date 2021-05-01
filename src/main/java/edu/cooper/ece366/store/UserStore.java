package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.User;

import java.util.List;
import java.sql.SQLException;

public interface UserStore {

  User get(DBconnection com_in, String ID) throws SQLException;

  int updateDB (DBconnection com_in, final String ID,  final String name) throws SQLException;

  //String getUserId(User user);

  int storeToDB(DBconnection com_in, String userID, String username, String password) throws SQLException;

  User newUser(DBconnection com_in, String name, String password) throws SQLException;

  User getUserbyID(DBconnection com_in, String userID) throws SQLException;

}
