package edu.cooper.ece366.DBconnection;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.categories.RestaurantBuilder;
import edu.cooper.ece366.framework.UserBuilder;
import edu.cooper.ece366.model.Address;
import edu.cooper.ece366.model.Cuisine;
import edu.cooper.ece366.model.OperatingHours;
import edu.cooper.ece366.model.PhoneNumber;
import edu.cooper.ece366.store.RestaurantStoreImpl;
import org.jdbi.v3.core.Jdbi;
import edu.cooper.ece366.framework.User;
import static org.hamcrest.MatcherAssert.assertThat;
import edu.cooper.ece366.DBconnection.UserDao;

import java.sql.SQLException;
import java.util.List;

public class jdbitest {
    public static void main(String[] args) throws SQLException {
        DBconnection dBconnection = new DBconnection();
        Restaurant panya = new RestaurantBuilder()
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
                .build();
        RestaurantStoreImpl restaurantstoreimpl = new RestaurantStoreImpl();
        restaurantstoreimpl.storeToDB(dBconnection,panya);
    }
}
