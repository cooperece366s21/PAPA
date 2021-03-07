package edu.cooper.ece366.categories;

import edu.cooper.ece366.model.Address;
import edu.cooper.ece366.model.Cuisine;
import edu.cooper.ece366.model.OperatingHours;
import edu.cooper.ece366.model.PhoneNumber;
import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Restaurant {
    String id();
    String name();
    String price();
    double rating();
    Cuisine cuisine();
    Address address();
    PhoneNumber phoneNumber();
    OperatingHours operatingHours();
}
