/*
What we want to add:
- User has the option to enter a lobby id or generate a lobby
- If they made a new lobby generate a random code and a list of restaurants from our db
- If they enter a code they join a lobby (for now each lobby just has a unique restaurant list
- Swipe part starts here
 */
package edu.cooper.ece366;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.initExceptionHandler;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cooper.ece366.auth.AuthStoreImpl;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.handler.Handler;
import edu.cooper.ece366.service.SwipingServiceImpl;
import edu.cooper.ece366.store.*;
import edu.cooper.ece366.store.PAPAJdbi;
import io.norberg.automatter.AutoMatter;
import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;
import org.jdbi.v3.core.Jdbi;
import spark.Request;
import spark.ResponseTransformer;

import java.util.HashMap;
import java.util.Optional;


public class main
{
    public static void main( String[] args )
    {
//        UserStore userStore = new UserStoreImpl();
        //    UserStore userStore = new UserStoreImpl();
//        String jdbcUrl = "jdbc:mysql://localhost:3306/coopflix?serverTimezone=UTC";
        // jdbi is the "database client," which all re-implemented stores should accept as a dependency
//        Jdbi jdbi = CoopflixJdbi.create(jdbcUrl);

//        UserStore userStore = new UserStoreMySQL(jdbi.create("jdbc:mysql://localhost:3306/PAPA"));
                //    UserStore userStore = new UserStoreImpl();
//                String jdbcUrl = "jdbc:mysql://localhost:3306/coopflix?serverTimezone=UTC";
        // jdbi is the "database client," which all re-implemented stores should accept as a dependency
//        Jdbi jdbi = CoopflixJdbi.create(jdbcUrl);

//        UserStore userStore = new UserStoreMysql(jdbi);
//        ContentStore contentStore = new ContentStoreImpl();));
//        ContentStore contentStore = new ContentStoreImpl();

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

        String jdbcUrl = "jdbc:mysql://localhost:3306/PAPA";
        // jdbi is the "database client," which all re-implemented stores should accept as a dependency
        Jdbi jdbi = PAPAJdbi.create(jdbcUrl);


        ConnectStore connectStore = new ConnectStoreImpl();
        LobbyPreferences lobbyPreferences = new LobbyPreferencesImpl();
        UserPreferences userPreferences = new UserPreferencesImpl();
        UserStore userStore = new UserStoreMySQL(Jdbi.create("jdbc:mysql://localhost:3306/PAPA"));
        Handler handler = new Handler(connectStore, lobbyPreferences, userPreferences,
                userStore, new LobbyStoreImpl(), new RestaurantStoreImpl() ,
                new SwipingServiceImpl(connectStore, lobbyPreferences, userPreferences), new AuthStoreImpl(), gson);



        /*
        //generate the user
        User user1 = new User();

        //enter or create lobby

        System.out.println("Type C to create a lobby or type J to join an existing one: \n");

        Scanner intPromptScan = new Scanner(System.in);  // Create a Scanner object
        String initialPrompt = intPromptScan.nextLine();  // Read user input

        //check to make sure a valid option was selected
        while(!initialPrompt.equals("C") && !initialPrompt.equals("J")){
            System.out.println("Please enter C or J: \n");

            initialPrompt = intPromptScan.nextLine();
        }

        if(initialPrompt.equals("C")){
            Lobby lobby1 = new Lobby();
            //print id of the lobby using getLobbyId or something

        } else {
            System.out.println("Please enter your unique lobby code: ");
            Scanner lobbyCode = new Scanner(System.in);  // Create a Scanner object
            String code = lobbyCode.nextLine();  // Read user input

            //getLobby function here or something
        }

        //generate the list of restaurants - this should be tied to what lobby your in
        ArrayList<Restaurant> restaurantsList;
        restaurantsList = restaurantTest.generateRestaurants();

        //category list
        ArrayList<String> catList = new ArrayList<String>();
        catList.add("Restaurants");
        catList.add("Movies (DO NOT SELECT FOR NOW!)");

        System.out.println("Select option from one of the following categories: \n");

        // have the user select a category
        //display options
        for(String categories:catList) {
            System.out.println(categories + "   ");
        }
        System.out.println("\n");

        Scanner selectedCat = new Scanner(System.in);  // Create a Scanner object

        String userSelectedCat = selectedCat.nextLine();  // Read user input

        //check to make sure a valid option was selected
        while(!catList.contains(userSelectedCat)){
            System.out.println("Please enter one of the above options: \n");

            userSelectedCat = selectedCat.nextLine();
        }

        System.out.println("You have selected the following category: " + userSelectedCat + "\n");

        user1.output(restaurantsList);

         */
    }
}