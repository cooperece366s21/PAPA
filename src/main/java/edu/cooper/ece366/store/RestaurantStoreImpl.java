package edu.cooper.ece366.store;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.categories.RestaurantBuilder;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.model.Address;
import edu.cooper.ece366.model.Cuisine;
import edu.cooper.ece366.model.OperatingHours;
import edu.cooper.ece366.model.PhoneNumber;

import java.util.List;
import java.util.ArrayList;
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
                                .name("Panya Bakery")
                                .price("$")
                                .rating(3.5)
                                .cuisine(new Cuisine.CuisineBuilder().addCuisine(Cuisine.cuisineType.bakeries).build())
                                .address(new Address.AddressBuilder()
                                                .name("Panya Bakery")
                                                .streetAddress("8 Stuyvesant St")
                                                .city("New York").state("NY").zipCode("10003")
                                                .build())
                                .phoneNumber(new PhoneNumber.PhoneNumberBuilder()
                                                    .area(212).exch(777).ext(1930)
                                                    .build())
                                .operatingHours(new OperatingHours.OperatingHoursBuilder()
                                                        .putOperatingHour(0,"0830","1900",false)
                                                        .putOperatingHour(1,"0830","1900",false)
                                                        .putOperatingHour(2,"0830","1900",false)
                                                        .putOperatingHour(3,"0830","1900",false)
                                                        .putOperatingHour(4,"0830","1900",false)
                                                        .putOperatingHour(5,"0830","1900",false)
                                                        .putOperatingHour(6,"0830","1900",false)
                                                        .build())
                                .build(),
                        new RestaurantBuilder()
                                .id("mamoun's-halal-new-york")
                                .name("Mamoun's Falafel")
                                .price("$")
                                .rating(3.5)
                                .cuisine(new Cuisine.CuisineBuilder().addCuisine(Cuisine.cuisineType.halal).build())
                                .address(new Address.AddressBuilder()
                                        .name("Mamoun's Falafel")
                                        .streetAddress("30 St. Mark's Place")
                                        .city("New York").state("NY").zipCode("10003")
                                        .build())
                                .phoneNumber(new PhoneNumber.PhoneNumberBuilder()
                                        .area(212).exch(387).ext(7747)
                                        .build())
                                .operatingHours(new OperatingHours.OperatingHoursBuilder()
                                        .putOperatingHour(0,"0830","1900",false)
                                        .putOperatingHour(1,"0830","1900",false)
                                        .putOperatingHour(2,"0830","1900",false)
                                        .putOperatingHour(3,"0830","1900",false)
                                        .putOperatingHour(4,"0830","1900",false)
                                        .putOperatingHour(5,"0830","1900",false)
                                        .putOperatingHour(6,"0830","1900",false)
                                        .build())
                                .build(),
                        new RestaurantBuilder()
                                .id("smac's-american-new-york")
                                .name("Smac's Mac and Cheese")
                                .price("$")
                                .rating(3.5)
                                .cuisine(new Cuisine.CuisineBuilder().addCuisine(Cuisine.cuisineType.newamerican).build())
                                .address(new Address.AddressBuilder()
                                        .name("Smac's Mac and Cheese")
                                        .streetAddress("197 1st Avenue")
                                        .city("New York").state("NY").zipCode("10003")
                                        .build())
                                .phoneNumber(new PhoneNumber.PhoneNumberBuilder()
                                        .area(212).exch(358).ext(7912)
                                        .build())
                                .operatingHours(new OperatingHours.OperatingHoursBuilder()
                                        .putOperatingHour(0,"1100","2300",false)
                                        .putOperatingHour(1,"1100","2300",false)
                                        .putOperatingHour(2,"1100","2300",false)
                                        .putOperatingHour(3,"1100","2300",false)
                                        .putOperatingHour(4,"1100","2300",false)
                                        .putOperatingHour(5,"1100","2300",false)
                                        .putOperatingHour(6,"1100","2300",false)
                                        .build())
                                .build()
                ).stream()
                        .collect(Collectors.toMap(Restaurant::id, Function.identity()));
    }

    @Override
    public Restaurant get(final String id) {
        return RESTAURANT_MAP.get(id);
    }

    @Override
    public String getRestId(Restaurant restaurant){

        return restaurant.id();
    }

    @Override
    public List<Restaurant> getListRest(List<String> restStringList){
        List<Restaurant> restList = new ArrayList<Restaurant>();
        for(String restString : restStringList){
            Restaurant rest = get(restString);
            restList.add(rest);
        }
        return restList;
    }
}
