package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.User;

import java.util.List;
import java.util.UUID;

public interface UserStore {
    User get(UUID id);


}
