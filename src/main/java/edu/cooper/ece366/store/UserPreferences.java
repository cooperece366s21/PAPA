package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;

import java.util.List;
import java.util.Map;

public interface UserPreferences {

    public String ID();
    public Map<String, Integer> likes();

    public Map<String, Integer> dislikes();
//    public static Map<String, User> USER_MAP;
}
