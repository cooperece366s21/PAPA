package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.User;

import java.util.List;
import java.sql.SQLException;

public interface UserStore {

  User get(String id);

  void update(User user);

  String getUserId(User user);

  int storeToDB(DBconnection com_in, User user) throws SQLException;

}
