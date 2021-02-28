/*
plan for now:
- implement classes for the lobby
- when running main user will be promoted with options (for now only resturant)
- when they select resturant they will get options thrown at them
    - create a list of some options
- they can enter l or r to simulate the swipe
- when finishing all the options well return the options they liked
 */
package edu.cooper.ece366;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.*;


public class main
{
    public static void main( String[] args )
    {
        //category list
        ArrayList<String> catList = new ArrayList<String>();
        catList.add("Restaurants");
        catList.add("Movies (DO NOT SELECT FOR NOW!)");

        //test resturant list
        ArrayList<String> restaurants = new ArrayList<String>();
        restaurants.add("Smac");
        restaurants.add("5 guys");
        restaurants.add("Artichoke");
        restaurants.add("2 Bros");
        restaurants.add("Mamouns");
        restaurants.add("Stickies");

        System.out.println("Select option from one of the following categories: \n");

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

        //initialize the left and right swipe lists
        ArrayList<String> rightList = new ArrayList<String>();
        ArrayList<String> leftList = new ArrayList<String>();

        Scanner swipe = new Scanner(System.in);
        String userSwipe;
        //change to work for any category selected
        for(String option:restaurants) {
            System.out.println(option + "\n");
            System.out.println("Type in l (dislike) or r (like): ");
            userSwipe = swipe.nextLine();
            //System.out.println("Outside while loop " + userSwipe + "\n");

            while(!userSwipe.equals("l") && !userSwipe.equals("r")){
                //System.out.println("is not l: " + (!userSwipe.equals("l")));
                //System.out.println("is not r: " + (!userSwipe.equals("r")));

                System.out.println("Please enter l or r \n");
                userSwipe = swipe.nextLine();
            }

            //add to the left and right lists
            if(userSwipe.equals("l")){
                leftList.add(option);
            } else {
                rightList.add(option);
            }

        }

        if(rightList.isEmpty()){

            System.out.println("You did not like any of the options :(\n");

        } else {

            System.out.println("Here is the list of everything you liked: \n");
            for (String liked : rightList) {
                System.out.println(liked + "\n");
            }

            //randomly pick from the options you liked
            Random rand = new Random();
            String randChoice = rightList.get(rand.nextInt(rightList.size()));

            System.out.println("Our recommendation is to go to: " + randChoice);

        }
    }
}