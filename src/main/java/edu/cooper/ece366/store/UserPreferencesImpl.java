package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;

import java.util.List;
import java.util.Map;

public class UserPreferencesImpl implements UserPreferences {

    @Override
    public String getID(){
        return null;
    }

    @Override
    public Integer updatePreferences(String RestID, Enum preference){

        preferences.replace(RestID, preference);

        return 0;
    }

    @Override
    public Integer putPreferences(String RestID, Enum preference){

        preferences.put(RestID, preference);

        return 0;
    }

}
