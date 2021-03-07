package edu.cooper.ece366.store;


import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.UserBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserStoreImpl implements UserStore {

    private static final Map<String, User> USER_MAP;

    static {
        USER_MAP =
                List.of(
                        new UserBuilder()
                        .ID(UUID.randomUUID().toString())
                        .build()
                ).stream()
                .collect(Collectors.toMap(User::ID, Function.identity()));
    }

    @Override
    public User get(final String id) {
        return USER_MAP.get(id);
    }

}