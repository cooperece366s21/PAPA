package edu.cooper.ece366.store;


import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.restaurantTest;

import java.util.List;
import java.util.UUID;

public class LobbyStoreImpl implements LobbyStore {

    @Override
    public Lobby get(UUID id) {
        return null;
    }

    @Override
    public String getCode(Lobby lobby) {
        return null;
    }

    @Override
    public List<Restaurant> getByLocation(Double miles) {
        return null;
    }



    @Override
    public void beginSearch(List<User> users, List<Restaurant> restaurants) {
        //ArrayList<Restaurant> restaurantsList;
        for(Restaurant option:restaurants){
            System.out.println(option);
            System.out.println("Swipe left or right");
            //HTTP POST /restaurant/:id/like
            //HTTP POST /restaurant/:id/dislike
        }
    }


// Put the hard coded info in here

//    static {
//        List<Content> content =
//                List.of(
//                        new MovieBuilder().genre(Genre.COMEDY).title("Ferris Bueller").rating(Rating.PG13).id("1").build(),
//                        new MovieBuilder().genre(Genre.COMEDY).title("My Cousin Vinny").id("2").build(),
//                        new MovieBuilder().genre(Genre.COMEDY).title("Pink Panther").id("3").build(),
//                        new MovieBuilder().genre(Genre.HORROR).title("Scream").id("4").build(),
//                        new MovieBuilder().genre(Genre.HORROR).title("Scream 2").id("5").build(),
//                        new MovieBuilder().genre(Genre.HORROR).title("Scream 3").id("6").build(),
//                        new MovieBuilder().genre(Genre.ACTION).title("Mission Impossible").id("7").build(),
//                        new MovieBuilder().genre(Genre.ACTION).title("007").id("8").build(),
//                        new MovieBuilder().genre(Genre.ACTION).title("Mad Max: Fury Road").id("9").build(),
//                        new MovieBuilder()
//                                .genre(Genre.MYSTERY)
//                                .title("Scooby Doo: Escape from Island")
//                                .id("10")
//                                .build(),
//                        new MovieBuilder().genre(Genre.MYSTERY).title("Wrath of Kahn").id("11").build(),
//                        new MovieBuilder().genre(Genre.MYSTERY).title("Murder Mystery").id("12").build());
//        contentMap = content.stream().collect(Collectors.toMap(Content::id, Function.identity()));
//    }
}
