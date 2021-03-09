package edu.cooper.ece366.store;


import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.UserBuilder;
import edu.cooper.ece366.store.RestaurantStoreImpl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class UserStoreImpl implements UserStore {

    public static final Map<String, User> USER_MAP;

    static {
        USER_MAP =
                List.of(
                        new UserBuilder()
                                .ID("KollKid")
                                .nickname("KollKid")
                                .addLike(RestaurantStoreImpl.RESTAURANT_MAP.get("smac's-american-new-york"))
                                .addLike(RestaurantStoreImpl.RESTAURANT_MAP.get("mamoun's-halal-new-york"))
                                .addDisLike(RestaurantStoreImpl.RESTAURANT_MAP.get("panya-bakery-new-york"))
                                .build(),
                        new UserBuilder()
                                .ID("Pablo")
                                .nickname("Pablo")
                                .addLike(RestaurantStoreImpl.RESTAURANT_MAP.get("smac's-american-new-york"))
                                .addLike(RestaurantStoreImpl.RESTAURANT_MAP.get("mamoun's-halal-new-york"))
                                .addLike(RestaurantStoreImpl.RESTAURANT_MAP.get("panya-bakery-new-york"))
                                .build(),
                        new UserBuilder()
                                .ID("xXx_Sephiroth_xXx")
                                .nickname("xXx_Sephiroth_xXx")
                                .addLike(RestaurantStoreImpl.RESTAURANT_MAP.get("smac's-american-new-york"))
                                .addDisLike(RestaurantStoreImpl.RESTAURANT_MAP.get("mamoun's-halal-new-york"))
                                .addDisLike(RestaurantStoreImpl.RESTAURANT_MAP.get("panya-bakery-new-york"))
                                .build()
                ).stream()
                .collect(Collectors.toMap(User::ID, Function.identity()));
    }

    @Override
    public User get(final String id) {
        return USER_MAP.get(id);
    }

    @Override
    public void update(final User user) {
        USER_MAP.put(user.ID(), user);
    }

}
