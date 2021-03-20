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
import edu.cooper.ece366.service.SwipingServiceImpl;
import edu.cooper.ece366.store.*;
import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;
import spark.Request;
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

      UserStore userStore = new UserStoreImpl();
      ConnectStore connectStore = new ConnectStoreImpl();
      LobbyPreferences lobbyPreferences = new LobbyPreferencesImpl();
      UserPreferences userPreferences = new UserPreferencesImpl();
      Handler handler = new Handler(connectStore, lobbyPreferences, userPreferences,
              userStore, new LobbyStoreImpl(), new RestaurantStoreImpl() ,
              new SwipingServiceImpl(connectStore, lobbyPreferences, userPreferences), gson);

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
        get("/lobby/:lobbyId", (req, res) -> handler.getLobby(req), gson::toJson);

        get("/getConnectionMap", (req, res) -> handler.getConnectionMap(), gson::toJson);
        get("/getLobbyLikes", (req, res) -> handler.getLobbyMap(), gson::toJson);

        get("/:lobbyId/init", (req, res) -> handler.initLobbyMap(req), gson::toJson);
        //get("/:lobbyId/recommendation", (req, res) -> handler.result(req), gson::toJson);

        post("/:userId/:lobbyID/:restID/like", (req, res) -> handler.like(req), gson::toJson);
        post("/:userId/:lobbyID/:restID/dislike", (req, res) -> handler.dislike(req), gson::toJson);


        //get("/cookie-example", App::cookieExample, responseTransformer);
        //get("/header-example", App::headerExample, responseTransformer);

        //post("/login", (req, res) -> handler.login(req, res), gson::toJson);
        //post("/logout", (req, res) -> handler.logout(req, res), gson::toJson);


        /*
        curl lobby/generateRestList
        curl lobby/begin

        users in the lobby
        restaurants in the lobby

        optional:
        curl restaurant/getInfo

        display first option
        curl user1/rest1/like
        curl user2/rest1/dislike
        curl user3/rest1/like


        curl user1/rest2/like
        curl user2/rest2/dislike
        curl user3/rest2/like

        curl user1/rest3/like
        curl user2/rest3/dislike
        curl user3/rest3/like

        curl lobby/getRecommendation
     */

    }
}
