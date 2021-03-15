package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;

import java.util.List;
import java.util.Map;

public interface UserPreferences {

    public enum preference {dislike, like}

    public String getID();
    public Map<String, Enum> preferences = null;

    public Integer updatePreferences(String RestID, Enum preference);

    public Integer putPreferences(String RestID, Enum preference);
}
