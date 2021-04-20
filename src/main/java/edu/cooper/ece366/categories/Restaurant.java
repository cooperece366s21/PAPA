package edu.cooper.ece366.categories;

import edu.cooper.ece366.model.Address;
import edu.cooper.ece366.model.Cuisine;
import edu.cooper.ece366.model.OperatingHours;
import edu.cooper.ece366.model.PhoneNumber;
import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Restaurant {
    String ID();
    String alias();
    String name();
    Boolean isOpenNow();
    String displayPhone();
    String price();
    double rating();
    String cuisine();
    String address();
    String phoneNumber();
    String operatingHours();

}
