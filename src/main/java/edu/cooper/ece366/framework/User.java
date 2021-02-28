package edu.cooper.ece366.framework;

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

    public User(){            //initialize a user called "name" if no parameters specified
        this.ID= "name";
        this.leftList= new ArrayList<String>();
        this.rightList= new ArrayList<String>();
    }

    public User(String username){
        this.ID= username;
        this.leftList= new ArrayList<String>();
        this.rightList= new ArrayList<String>();
    }

    ArrayList<String> leftList=new ArrayList<String>();
    ArrayList<String> rightList=new ArrayList<String>();
    //private Queue <String> restaurantQueue = new LinkedList<>();
    ArrayList<String> restaurants = new ArrayList<String>();

    public ArrayList<String> getRestaurants() {
        return restaurants;
    }

    void output (ArrayList<String>restaurants) {
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

    //Getters and setters

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setLeftList(ArrayList<String> leftList) {
        this.leftList = leftList;
    }

    public void setRightList(ArrayList<String> rightList) {
        this.rightList = rightList;
    }
}

