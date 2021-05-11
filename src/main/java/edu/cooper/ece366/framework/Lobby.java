package edu.cooper.ece366.framework;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Lobby {
    String ID();

    String code();

    String ownerID();

}