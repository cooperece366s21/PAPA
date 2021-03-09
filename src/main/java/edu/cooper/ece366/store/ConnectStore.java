package edu.cooper.ece366.store;

import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.store.UserPreferences;

import java.util.Map;

public interface ConnectStore {
    public Map<String, Map<String, Map<String, UserPreferences>>> connection();

}
