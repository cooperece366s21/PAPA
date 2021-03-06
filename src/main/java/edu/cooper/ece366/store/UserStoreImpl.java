package edu.cooper.ece366.store;


import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.User;

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
                        .id(UUID.randomUUID())
                        .build()
                ).stream()
                .collect(Collectors.toMap(User::ID, Function.identity()));
    }

    @Override
    public User get(final UUID id) {
        return USER_MAP.get(id);
    }

}