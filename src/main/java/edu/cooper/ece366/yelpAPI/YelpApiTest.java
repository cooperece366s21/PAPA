package edu.cooper.ece366.yelpAPI;

import edu.cooper.ece366.model.Cuisine;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


/**
 * Tests for Yelps
 */
public class YelpApiTest {

    //@Test
    public void getByLatLong() throws IOException{

        YelpApi yelpApi = new YelpApi();
        JSONArray response = yelpApi.searchForBusinessesByGeo(null, 49.33, -0.39, 3);

        JSONObject firstBusiness = (JSONObject) response.get(0);
        String firstBusinessID = firstBusiness.get("id").toString();

        assertEquals("H_wfMdEEvpEwaPgIqwownA", firstBusinessID);

    }

    //@Test
    public void getByLocation() throws IOException{

        String businessAlias = "levain-bakery-new-york";

        YelpApi yelpApi = new YelpApi();
        kong.unirest.json.JSONArray response = yelpApi.searchForBusinessesByLocation(null, "NYC", null);

        JSONObject firstBusiness = (JSONObject) response.get(0);

        String alias = (String) firstBusiness.get("alias");
        assertEquals(businessAlias, alias);
        assertEquals("+19174643769", firstBusiness.get("phone"));

    }

    //@Test
    public void getByID() throws IOException{
        YelpApi yelpApi = new YelpApi();
        JSONObject response = yelpApi.searchByBusinessId("V7lXZKBDzScDeGB8JmnzSA");
        assertEquals("V7lXZKBDzScDeGB8JmnzSA",response.get("id"));
    }

    //@Test
    public static void getFromCMD() throws IOException{
        YelpApi yelpApi = new YelpApi();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Location: ");
        String loc = scanner.nextLine();

        JSONArray response = yelpApi.searchForBusinessesByLocation(null, loc, null);

        ArrayList<Object> res = new ArrayList<>();

        for(Object js : response){
            res.add(js);
        }

        for(Object r : res){
            Cuisine cui = new Cuisine();
            JSONObject rs = (JSONObject) r;
            JSONObject rd = yelpApi.searchByBusinessId(rs.get("id").toString());
            JSONArray cuisineObj = (JSONArray) rd.get("categories");

        }

    }

    public static void main(String[] args) throws IOException {
        getFromCMD();

    }
}
