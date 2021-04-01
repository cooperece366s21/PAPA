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

    /**
     * function to search for n=limit restaurants within a given location, with a optional keyword = (String) term
     * @param term Optional search keyword:
     *      *      - can be search term (i.e. "food" or "restaurant")
     *      *      - can be business name (i.e. "Starbucks")
     *      *      - If term is not included the endpoint will default to searching across businesses from
     *      *        a small number of popular categories.
     * @param location This string indicates the geographic area to be used when searching for businesses:
     *      *          - can be country name, state name, city name, street name... (i.e. "NYC")
     *      *          - can be a region name (i.e. "east village")
     *      *          - can be ZIP code (i.e. "10003")
     *      *          - can be a precise address (i.e. "41 Cooper Sq, New York, NY 10003")
     * @param limit Optional. Number of business results to return. By default, it will return 20. Maximum is 50.
     * @return JSONArray of returned business objects.
     *      *    Response body format: https://www.yelp.com/developers/documentation/v3/business_search
     */
    public JSONArray searchForBusinessesByLocation(@Nullable String term, String location, @Nullable Integer limit) {
        String searchTerm = term == null ? "" : term;
        int searchLimit = limit == null ? SEARCH_LIMIT : limit;
        HttpResponse<JsonNode> response = Unirest.get(API_HOST + SEARCH_PATH)
                .header("Authorization", "Bearer " + APIkey)
                .queryString("term", searchTerm)
                .queryString("location", location)
                .queryString("limit", searchLimit)
                .asJson();
        //System.out.println(response.getBody().toString());
        return response.getBody().getObject().getJSONArray("businesses");
    }

    /**
     *
     * @param term optional search keyword:
     *      *             - can be search term (i.e. "food" or "restaurant")
     *      *             - can be business name (i.e. "Starbucks")
     *      *             - If term is not included the endpoint will default to searching across businesses from
     *      *               a small number of popular categories.
     * @param lat Latitude of the location you want to search nearby.
     * @param lng Longitude of the location you want to search nearby.
     * @param limit Optional. Number of business results to return. By default, it will return 20. Maximum is 50.
     * @return JSONArray of returned business objects.
     *      *    Response body format: https://www.yelp.com/developers/documentation/v3/business_search
     */
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

    /**
     *
     * @param businessID Unique Yelp ID of business used to retrieve it detail information
     * @return detailed business object.
     *      *   Response body format: https://www.yelp.com/developers/documentation/v3/business
     */
    public JSONObject searchByBusinessId(String businessID) {
        HttpResponse<JsonNode> response = Unirest.get(API_HOST + BUSINESS_PATH +"/" + businessID)
                .header("Authorization", "Bearer " + APIkey)
                .queryString("limit", SEARCH_LIMIT)
                .asJson();
        return response.getBody().getObject();
    }

}