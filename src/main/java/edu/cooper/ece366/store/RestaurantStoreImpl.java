package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.categories.RestaurantBuilder;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.model.Cuisine;
import edu.cooper.ece366.model.PhoneNumber;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RestaurantStoreImpl implements RestaurantStore{

    public static final Map<String, Restaurant> RESTAURANT_MAP;

    static {
        RESTAURANT_MAP =
                List.of(
                        new RestaurantBuilder()
                                .id("panya-bakery-new-york")
                                .phoneNumber(new PhoneNumber.PhoneNumberBuilder()
                                                    .area(212).exch(777).ext(1930)
                                                    .build())
                                .cuisine(new Cuisine.CuisineBuilder().addCuisine(Cuisine.cuisineType.bakeries).build())
                                .build()
                ).stream()
                        .collect(Collectors.toMap(Restaurant::id, Function.identity()));
    }

    @Override
    public Restaurant get(final String id) {
        return RESTAURANT_MAP.get(id);
    }
}
