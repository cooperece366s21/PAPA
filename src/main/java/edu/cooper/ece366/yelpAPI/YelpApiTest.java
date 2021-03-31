package edu.cooper.ece366.yelpAPI;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * Tests for Yelps
 */
public class YelpApiTest {

    @Test
    public void getByLatLong() throws IOException{

        YelpApi yelpApi = new YelpApi();
        JSONArray response = yelpApi.searchForBusinessesByGeo(null, 49.33, -0.39, 3);

        JSONObject firstBusiness = (JSONObject) response.get(0);
        String firstBusinessID = firstBusiness.get("id").toString();

        assertEquals("H_wfMdEEvpEwaPgIqwownA", firstBusinessID);

    }

    @Test
    public void getByLocation() throws IOException{

        String businessAlias = "levain-bakery-new-york";

        YelpApi yelpApi = new YelpApi();
        kong.unirest.json.JSONArray response = yelpApi.searchForBusinessesByLocation(null, "NYC", null);

        JSONObject firstBusiness = (JSONObject) response.get(0);

        String alias = (String) firstBusiness.get("alias");
        assertEquals(businessAlias, alias);
        assertEquals("+19174643769", firstBusiness.get("phone"));

    }

    @Test
    public void getByID() throws IOException{
        YelpApi yelpApi = new YelpApi();
        JSONObject response = yelpApi.searchByBusinessId("V7lXZKBDzScDeGB8JmnzSA");
        assertEquals("V7lXZKBDzScDeGB8JmnzSA",response.get("id"));
    }

}
