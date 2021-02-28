package edu.cooper.ece366;

/**
 * Hello world!
 *
 */
import static spark.Spark.get;
import static spark.Spark.initExceptionHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cooper.ece366.handler.Handler;
import edu.cooper.ece366.service.FeedServiceImpl;
import edu.cooper.ece366.store.ContentStoreImpl;
import edu.cooper.ece366.store.UserStoreImpl;
import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;

public class App 
{
    public static void main( String[] args )
    {
        Gson gson =
                new GsonBuilder().registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory()).create();

        initExceptionHandler(Throwable::printStackTrace);

        Handler handler = new Handler(new UserStoreImpl(), new FeedServiceImpl(new ContentStoreImpl()));

    /* goal: a user can load a feed and then watch content
     pre-req
     * create store with content -- couple movies, couple shows/seasons/episodes (record genre)
     * create store with users -- maybe we capture genre preferences for the feed
     service layer
     * service can fetch user and content info as well as derive a feed based on user prefs/existing content
     * api/handler layer that will extract req info and delegate to service and generate response info
    */

        get("/ping", (req, res) -> "OK");
        get("/user/:userId", (req, res) -> handler.getUser(req), gson::toJson);
        get("/user/:userId/feed", (req, res) -> handler.getFeed(req), gson::toJson);
    }
}
