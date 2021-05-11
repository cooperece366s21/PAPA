package edu.cooper.ece366.framework;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface User {
    String ID();

    String name();

    String password();

}
