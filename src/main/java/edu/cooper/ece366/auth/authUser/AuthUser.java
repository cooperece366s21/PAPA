package edu.cooper.ece366.auth.authUser;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface AuthUser {
    String username();

    String password();
}
