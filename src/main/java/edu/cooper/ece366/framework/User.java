package edu.cooper.ece366.framework;

import edu.cooper.ece366.categories.Restaurant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class User {
    private UUID ID;

    public User(){
        this.ID= generateID();
//        this.leftList= new ArrayList<Restaurant>();
//        this.rightList= new ArrayList<Restaurant>();
    }
//
//    ArrayList<Restaurant> leftList=new ArrayList<Restaurant>();
//    ArrayList<Restaurant> rightList=new ArrayList<Restaurant>();
//    ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
//
//    public ArrayList<Restaurant> getRestaurants() {
//        return restaurants;
//    }

//    public void output(ArrayList<Restaurant> restaurants) {
//        // some of this code could be used for demo, but you'll want this separate from the server as a mock "client"
//        Scanner swipe = new Scanner(System.in);
//        String userSwipe;
//
//        for(Restaurant option:restaurants) {
//            System.out.println(option.toString() + "\n");
//            System.out.println("Type in l (dislike) or r (like): ");
//            userSwipe = swipe.nextLine();
//
//            while(!userSwipe.equals("l") && !userSwipe.equals("r")){
//                System.out.println("Please enter l or r \n");
//                userSwipe = swipe.nextLine();
//            }
//
//            //add to the left and right lists
//            if(userSwipe.equals("l")){
//                leftList.add(option);
//            } else {
//                rightList.add(option);
//            }
//
//        }
//
//        if(rightList.isEmpty()){
//
//            System.out.println("You did not like any of the options :(\n");
//
//        } else {
//
//            System.out.println("Here is the list of everything you liked: \n");
//            for (Restaurant liked : rightList) {
//                System.out.println(liked.getName() + "\n");
//            }
//
//            //randomly pick from the options you liked
//            Random rand = new Random();
//            Restaurant randChoice = rightList.get(rand.nextInt(rightList.size()));
//
//            System.out.println("Our recommendation is to go to: " + randChoice.toString());
//
//        }
//
//    }

    public UUID generateID(){
        return UUID.randomUUID();
    }
    public UUID getID() {
        return ID;
    }
}

