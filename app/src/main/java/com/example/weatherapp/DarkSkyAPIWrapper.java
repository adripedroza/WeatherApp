package com.example.weatherapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DarkSkyAPIWrapper {
    private String APIKey, baseURL;

    public DarkSkyAPIWrapper() {
        this.APIKey = MainActivity.resources.getString(R.string.darkSky_API_key);
        this.baseURL = MainActivity.resources.getString(R.string.darkSky_request);
    }

    public JSONObject getCurrentWeather(ArrayList<String> coords) {
        float lat = Float.parseFloat(coords.get(0));
        float log = Float.parseFloat(coords.get(1)); //i know it's longitude

        HTTPRequest request = new HTTPRequest();

        String URL = baseURL + APIKey + lat + "," + log;

        try {
            JSONObject object = request.execute(URL).get();

            return object.getJSONObject("currently");
        } catch (Exception e) {
            System.out.println("Error in DarkSky API call: " + e.getStackTrace().toString());
        }
        return null;
    }
}
