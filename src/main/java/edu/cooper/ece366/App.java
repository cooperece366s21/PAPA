package edu.cooper.ece366;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.initExceptionHandler;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.UserBuilder;
import edu.cooper.ece366.handler.Handler;
import edu.cooper.ece366.store.*;
import io.norberg.automatter.AutoMatter;
import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;
import spark.Request;
import spark.Response;
import spark.ResponseTransformer;

import java.util.HashMap;
import java.util.Optional;
import java.util.Map;


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


        Handler handler = new Handler(gson);

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
        get("/me", handler::getCurrentUser, gson::toJson);

      //  get("/user/:userId", (req, res) -> handler.getCurrentUser(req), gson::toJson);
        get("/lobby/:lobbyId", (req, res) -> handler.getCurrentLobby(req), gson::toJson);
        get("/:lobbyID/getList", (req, res) -> handler.getRestaurantList(req), gson::toJson);
        get("/:restaurantID/image_url", (req, res) -> handler.getRestaurantUrl((req)), gson::toJson);
        get("/:lobbyID/image_url_list", (req, res) -> handler.getRestaurantUrlList((req)), gson::toJson);

        get("/:lobbyID/recommendation", (req, res) -> handler.getRecommendation(req), gson::toJson);
        get("/:lobbyID/getLobbyUsers", (req, res) -> handler.getLobbyUsers(req), gson::toJson);

        post("/:userID/:lobbyID/:restID/like", (req, res) -> handler.like(req), gson::toJson);
        post("/:userID/:lobbyID/:restID/dislike", (req, res) -> handler.dislike(req), gson::toJson);

        post("/:username/:password/signUp", (req, res) -> handler.signUp(req), gson::toJson);
        post("/login", handler::login, gson::toJson);
        post("/logout", handler::logout, gson::toJson);

        post("/:userID/:location/createLobby", (req, res) -> handler.createLobby(req), gson::toJson);
        post("/:userID/:location/:keyword/createLobby", (req, res) -> handler.createLobbyWithKeyword(req), gson::toJson);
        post("/joinLobby", handler::joinLobby, gson::toJson);
        post("/leaveLobby", handler::leaveLobby, gson::toJson);
    }


}
