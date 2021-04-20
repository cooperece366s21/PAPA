package edu.cooper.ece366;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.initExceptionHandler;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cooper.ece366.auth.authLobby.AuthLobbyStoreImpl;
import edu.cooper.ece366.auth.authUser.AuthUserStoreImpl;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.UserBuilder;
import edu.cooper.ece366.handler.Handler;
import edu.cooper.ece366.service.SwipingServiceImpl;
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

        UserStore userStore = new UserStoreImpl();
        ConnectStore connectStore = new ConnectStoreImpl();
        LobbyPreferences lobbyPreferences = new LobbyPreferencesImpl();
        UserPreferences userPreferences = new UserPreferencesImpl();
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

        get("/cookie-example", App::cookieExample, responseTransformer);
        get("/header-example", App::headerExample, responseTransformer);

      //  get("/user/:userId", (req, res) -> handler.getCurrentUser(req), gson::toJson);
        get("/lobby/:lobbyId", (req, res) -> handler.getCurrentLobby(req), gson::toJson);
        get("/:lobbyID/getList", (req, res) -> handler.getRestaurantList(req), gson::toJson);

        //get("/:lobbyID/getLobbyFeed", (req, res) -> handler.getLobbyFeed(req), gson::toJson);

        //get("/getConnectionMap", (req, res) -> handler.getConnectionMap(), gson::toJson);
        //get("/getLobbyLikes", (req, res) -> handler.getLobbyMap(), gson::toJson);
        //get("/getPreferenceMap", (req, res) -> handler.getPreferenceMap(), gson::toJson);

        //get("/:lobbyId/init", (req, res) -> handler.initLobbyMap(req), gson::toJson);
        get("/:lobbyID/recommendation", (req, res) -> handler.getRecommendation(req), gson::toJson);

        post("/:userID/:lobbyID/:restID/like", (req, res) -> handler.like(req), gson::toJson);
        post("/:userID/:lobbyID/:restID/dislike", (req, res) -> handler.dislike(req), gson::toJson);

        post("/:username/:password/signUp", (req, res) -> handler.signUp(req), gson::toJson);
        post("/login", handler::login, gson::toJson);
        post("/logout", handler::logout, gson::toJson);

        post("/:userID/:location/createLobby", (req, res) -> handler.createLobby(req), gson::toJson);
        post("/joinLobby", handler::joinLobby, gson::toJson);
        post("/leaveLobby", handler::leaveLobby, gson::toJson);
    }


    private static HeaderExample headerExample(final Request request, final Response response) {
        String accessToken = Optional.ofNullable(request.headers("access-token")).orElseThrow();
        response.header("current-time", "now");
        response.header("my-app-header", "yeet");
        return new HeaderExampleBuilder().build();
    }

    @AutoMatter
    interface CookieExample {
        String requestCookie();

        String responseCookie();
    }

    @AutoMatter
    interface HeaderExample {
        Optional<String> request();

        Optional<String> response();
    }

    private static final Map<String, User> cookieMap = new HashMap<>();

    static {
        cookieMap.put("decafbad", new UserBuilder().ID("Pablo").name("Pablo").password("password").build());
    }

    // "me" endpoint

    private static User cookieExample(final Request request, final Response response) {
        String msg = Optional.ofNullable(request.cookie("user")).orElseThrow();
        //String msg = Optional.ofNullable(request.cookie("msg")).orElse("default-msg");

        User user = cookieMap.get(msg);
//
        if (user == null) {
          response.status(401);
          return null;
        }

            //response.cookie("server-msg", "yeet");

            //return new CookieExampleBuilder().requestCookie(msg).responseCookie("yeet").build();
        return user;
      }

}
