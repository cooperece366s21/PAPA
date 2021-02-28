/*
What we want to add:
- User has the option to enter a lobby id or generate a lobby
- If they made a new lobby generate a random code and a list of restaurants from our db
- If they enter a code they join a lobby (for now each lobby just has a unique restaurant list
- Swipe part starts here
 */
package edu.cooper.ece366;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.*;

import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.Lobby;

public class main
{
    public static void main( String[] args )
    {
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
    }
}