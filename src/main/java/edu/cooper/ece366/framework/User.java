package edu.cooper.ece366.framework;

import io.norberg.automatter.AutoMatter;
import java.util.UUID;

@AutoMatter
public interface User {
    UUID ID();

    String username();



}

