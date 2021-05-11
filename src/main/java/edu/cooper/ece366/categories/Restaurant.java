package edu.cooper.ece366.categories;

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
