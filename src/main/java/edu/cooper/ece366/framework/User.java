package edu.cooper.ece366.framework;

import edu.cooper.ece366.categories.Restaurant;
import io.norberg.automatter.AutoMatter;

import java.util.List;
import java.util.UUID;

@AutoMatter
public interface User {
    String ID();

    String name();

    String password();

}
