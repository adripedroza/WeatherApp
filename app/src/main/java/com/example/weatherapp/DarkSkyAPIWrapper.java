package com.example.weatherapp;

import java.util.ArrayList;

public class DarkSkyAPIWrapper {
    private String APIKey, baseURL;
    private static final DarkSkyAPIWrapper DARK_SKY_API_WRAPPER = new DarkSkyAPIWrapper();

    private DarkSkyAPIWrapper() {
        this.APIKey = MainActivity.resources.getString(R.string.darkSky_API_key);
        this.baseURL = MainActivity.resources.getString(R.string.darkSky_request);
    }

    protected static DarkSkyAPIWrapper getInstance(){
        return DARK_SKY_API_WRAPPER;
    }

    protected void getCurrentWeather(ArrayList<String> coords) {
        float lat = Float.parseFloat(coords.get(0));
        float lng = Float.parseFloat(coords.get(1)); //i know it's longitude

        String URL = baseURL + APIKey + "/" + lat + "," + lng;
        new HTTPRequest().execute(URL);
    }
}
