package edu.cooper.ece366.framework;

import io.norberg.automatter.AutoMatter;

import java.util.List;
import java.util.UUID;

@AutoMatter
public interface Lobby {
    UUID ID();

    String code();

    List<User> userList();
}