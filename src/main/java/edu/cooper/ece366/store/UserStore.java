package edu.cooper.ece366.store;

import edu.cooper.ece366.framework.User;

public interface UserStore {

  User get(String id);

  void update(User user);

  String getUserId(User user);
}
