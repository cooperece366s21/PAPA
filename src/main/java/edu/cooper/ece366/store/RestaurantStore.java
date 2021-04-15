package edu.cooper.ece366.store;

import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;

import java.sql.SQLException;
import java.util.List;

public interface RestaurantStore {


    Restaurant get(String id);

    List<Restaurant> getListRest(List<String> restStringList);

    String getRestId(Restaurant restaurant);

    int storeToDB(DBconnection com_in, Restaurant restaurant) throws SQLException;
}
