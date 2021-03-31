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

    private static final String API_HOST = "https://api.yelp.com";

    private static final int SEARCH_LIMIT = 3;      //set to 3 by default
    private static final String SEARCH_PATH = "/v3/businesses/search";
    private static final String BUSINESS_PATH = "/v3/businesses";

    private final String APIkey;

    public YelpApi() throws IOException {
        this.APIkey = Files.readString(Path.of("yelpAPIkey.txt"));
    }


    public JSONArray searchForBusinessesByLocation(@Nullable String term, String location, @Nullable Integer limit) {
        String searchTerm = term == null ? "" : term;
        int searchLimit = limit == null ? SEARCH_LIMIT : limit;
        HttpResponse<JsonNode> response = Unirest.get(API_HOST + SEARCH_PATH)
                .header("Authorization", "Bearer " + APIkey)
                .queryString("term", searchTerm)
                .queryString("location", location)
                .queryString("limit", searchLimit)
                .asJson();
        return response.getBody().getObject().getJSONArray("businesses");
    }

    public JSONArray searchForBusinessesByGeo(@Nullable String term, double lat, double lng, @Nullable Integer limit) {
        String searchTerm = term == null ? "" : term;
        int searchLimit = limit == null ? SEARCH_LIMIT : limit;
        HttpResponse<JsonNode> response = Unirest.get(API_HOST + SEARCH_PATH)
                .header("Authorization", "Bearer " + APIkey)
                .queryString("term", searchTerm)
                .queryString("latitude", lat)
                .queryString("longitude", lng)
                .queryString("limit", searchLimit)
                .asJson();
        return response.getBody().getObject().getJSONArray("businesses");

    }


    public JSONObject searchByBusinessId(String businessID) {
        HttpResponse<JsonNode> response = Unirest.get(API_HOST + BUSINESS_PATH +"/" + businessID)
                .header("Authorization", "Bearer " + APIkey)
                .queryString("limit", SEARCH_LIMIT)
                .asJson();
        return response.getBody().getObject();
    }

}