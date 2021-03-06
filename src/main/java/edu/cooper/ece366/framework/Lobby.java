package edu.cooper.ece366.framework;

import io.norberg.automatter.AutoMatter;
import java.util.UUID;

@AutoMatter
public interface Lobby {
    UUID ID();

    String code();
}