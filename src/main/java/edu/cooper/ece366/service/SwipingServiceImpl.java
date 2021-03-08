package edu.cooper.ece366.service;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.Lobby;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.UserBuilder;
import edu.cooper.ece366.store.UserStore;

public class SwipingServiceImpl implements SwipingService {

    private final UserStore userStore;

    public SwipingServiceImpl(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public Integer dislike(User user, Restaurant restaurant, Lobby lobby){

        if(user.dislikes().contains(restaurant))
            user.dislikes().add(restaurant);


        return 0;
    }

    @Override
    public Integer like(User user, Restaurant restaurant, Lobby lobby){
        if(!user.likes().contains(restaurant)) {
            User updatedUser = UserBuilder.from(user).addLike(restaurant).build();
            userStore.update(updatedUser);
        }

//        //add to the like counter for the restaurant
//        Integer likeCount = (Integer) lobby.restaurant_maps().get(restaurant);
//        likeCount++;
//
//        lobby.restaurant_maps().replace(restaurant.id(),likeCount);

        return 0;
    }

}
