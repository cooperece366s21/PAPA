package edu.cooper.ece366.model;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.User;
import io.norberg.automatter.AutoMatter;
import java.util.List;

@AutoMatter
public interface UserPreferences {
  User userId();

  List<Restaurant> likes();

  List<Restaurant> dislikes();
}
