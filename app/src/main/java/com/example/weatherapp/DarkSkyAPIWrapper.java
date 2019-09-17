package com.example.weatherapp;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DarkSkyAPIWrapper {
    private static Map<String, Object> values = new HashMap<String, Object>();
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
        float lng = Float.parseFloat(coords.get(1));

        String URL = baseURL + APIKey + "/" + lat + "," + lng;
        new HTTPRequest().execute(URL);
    }

    protected void setValues(JSONObject object) {
        try {
            JSONObject currently = object.getJSONObject("currently");
            values.put("Wind Speed", currently.get("windSpeed").toString());
            values.put("Temperature", currently.get("temperature").toString());
            values.put("Precipitation", currently.get("precipProbability").toString());
            values.put("Humidity", currently.get("humidity").toString());
        } catch (JSONException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    protected static Map<String, Object> getValues() {
        return values;
    }
}
