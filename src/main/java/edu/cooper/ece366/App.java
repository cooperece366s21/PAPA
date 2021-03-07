package edu.cooper.ece366;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.initExceptionHandler;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cooper.ece366.handler.Handler;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.UserBuilder;
import edu.cooper.ece366.service.SwipingServiceImpl;
import edu.cooper.ece366.store.LobbyStoreImpl;
import edu.cooper.ece366.store.UserStoreImpl;
import io.norberg.automatter.AutoMatter;
import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import spark.Request;
import spark.Response;
import spark.ResponseTransformer;


public class App 
{
    public static void main( String[] args )
    {

        staticFiles.location("/public"); // Static files

        Gson gson =
                new GsonBuilder().registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory()).create();

        ResponseTransformer responseTransformer =
                model -> {
                    if (model == null){
                        return "";
                    }
                    return gson.toJson(model);
                };

        initExceptionHandler(Throwable::printStackTrace);

        Handler handler = new Handler(
                new UserStoreImpl(), new LobbyStoreImpl(), new SwipingServiceImpl(), gson);

        options(
            "/*",
            (request, response) -> {
                String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                if (accessControlRequestHeaders != null) {
                    response.header("Access-Control-Allow-Headers", "*");
                }

                String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
                if (accessControlRequestMethod != null) {
                    response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                    response.header("Access-Control-Allow-Methods", "*");
                }

                return "OK";
            });

        before(
            (req, res) -> {
                res.header("Access-Control-Allow-Origin", "*");
                res.header("Access-Control-Allow-Headers", "*");
                res.type("application/json");
            });

        get("/ping", (req, res) -> "OK");
        get("/user/:userId", (req, res) -> handler.getUser(req), gson::toJson);
        //get("/user/:lobbyId/feed", (req, res) -> handler.getLobby(req), gson::toJson); once lobby is done

        //get("/me", (req, res) -> handler.me(req, res), gson::toJson);


        //post("/restaurant/:UserId/like", (req, res) -> handler.like(req), gson::toJson);
        //post("/restaurant/:UserId/dislike", (req, res) -> handler.dislike(req), gson::toJson);


        //get("/cookie-example", App::cookieExample, responseTransformer);
        //get("/header-example", App::headerExample, responseTransformer);

        //post("/login", (req, res) -> handler.login(req, res), gson::toJson);
        //post("/logout", (req, res) -> handler.logout(req, res), gson::toJson);

    }
}
