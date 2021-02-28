package edu.cooper.ece366.framework;

import edu.cooper.ece366.categories.Restaurant;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.*;
import java.util.Random;

public class User {                  //NOTE: CHANGE STRING TO TYPE RESTAURANT IN ARRAYLIST!!!!!
    private String ID;

    public User(){
        this.ID= generateID();
        this.leftList= new ArrayList<Restaurant>();
        this.rightList= new ArrayList<Restaurant>();
    }

    ArrayList<Restaurant> leftList=new ArrayList<Restaurant>();
    ArrayList<Restaurant> rightList=new ArrayList<Restaurant>();
    //private Queue <String> restaurantQueue = new LinkedList<>();
    ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    void output (ArrayList<Restaurant>restaurants) {
        Scanner swipe = new Scanner(System.in);
        String userSwipe;

        for(String option:restaurants) {
            System.out.println(option + "\n");
            System.out.println("Type in l (dislike) or r (like): ");
            userSwipe = swipe.nextLine();

            while(!userSwipe.equals("l") && !userSwipe.equals("r")){
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

    public UUID generateID(){
        UUID uniqueID= UUID.randomUUID();
        return uniqueID;
    }



/*    public void selectCategory(catList) {
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


    }*/


    //Getters and setters

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setLeftList(ArrayList<Restaurant> leftList) {
        this.leftList = leftList;
    }

    public void setRightList(ArrayList<Restaurant> rightList) {
        this.rightList = rightList;
    }
}

