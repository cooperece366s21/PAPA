package edu.cooper.ece366.handler;

//import edu.cooper.ece366.model.Feed;
//import edu.cooper.ece366.model.User;
//import edu.cooper.ece366.service.FeedService;
//import edu.cooper.ece366.store.UserStore;
import spark.Request;

public class Handler {

    private final UserStore userStore;
    private final FeedService feedService;

    public Handler(UserStore userStore, FeedService feedService) {
        this.userStore = userStore;
        this.feedService = feedService;
    }

    public User getUser(Request request) {
        String userId = request.params(":userId");
        return userStore.get(userId);
    }

    public Feed getFeed(Request request) {
        User user = getUser(request);
        return feedService.getFeedForUser(user);
    }

}