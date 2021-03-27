package edu.cooper.ece366.auth;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Auth {
    String username();

    String password();
}
