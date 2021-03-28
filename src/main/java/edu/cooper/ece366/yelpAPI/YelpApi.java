package edu.cooper.ece366.yelpAPI;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.Nullable;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class YelpApi {

    private static final String API_HOST = "api.yelp.com";

    private static final int SEARCH_LIMIT = 8;
    private static final String SEARCH_PATH = "/v3/businesses/search";
    private static final String BUSINESS_PATH = "/v3/businesses";

    private final String APIkey;

    public YelpApi() throws IOException {
        this.APIkey = Files.readString(Path.of("yelpAPIkey.txt"));
    }


    public JSONArray searchForBusinessesByLocation(@Nullable String term, String location) {
        term = term == null ? "" : term;
        HttpResponse<JsonNode> response = Unirest.get("https://api.yelp.com/v3/businesses/search")
                .header("Authorization", "Bearer " + APIkey)
                .queryString("term", term)
                .queryString("location", "NYC")
                .queryString("limit", 3)
                .asJson();
        return response.getBody().getObject().getJSONArray("businesses");
    }

    public JSONArray searchForBusinessesByGeo(@Nullable String term, double lat, double lng) {
        term = term == null ? "" : term;
        HttpResponse<JsonNode> response = Unirest.get("https://api.yelp.com/v3/businesses/search")
                .header("Authorization", "Bearer " + APIkey)
                .queryString("term", term)
                .queryString("latitude", lat)
                .queryString("longitude", lng)
                .queryString("limit", 3)
                .asJson();
        return response.getBody().getObject().getJSONArray("businesses");

    }


    public JSONObject searchByBusinessId(String businessID) {
        HttpResponse<JsonNode> response = Unirest.get("https://api.yelp.com/v3/businesses/" + businessID)
                .header("Authorization", "Bearer " + APIkey)
                .queryString("limit", 1)
                .asJson();
        return response.getBody().getObject();
    }

}