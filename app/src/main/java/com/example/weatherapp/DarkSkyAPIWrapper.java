package com.example.weatherapp;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

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

    protected void getCurrentWeather(LatLng coords) {

        String URL = baseURL + APIKey + "/" + coords.latitude + "," + coords.longitude;
        new HTTPRequest().execute(URL);
    }

    protected void setValues(JSONObject object) {
        try {
            JSONObject currently = object.getJSONObject("currently");
            Double precipProbability = Double.parseDouble(currently.get("precipProbability").toString());
            precipProbability *= 100;
            Double humidity = Double.parseDouble(currently.get("humidity").toString());
            humidity *=100;
            values.put("Wind Speed", currently.get("windSpeed").toString());
            values.put("Temperature", currently.get("temperature").toString());
            values.put("Precipitation", precipProbability);
            values.put("Humidity", humidity);
        } catch (JSONException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    protected static Map<String, Object> getValues() {
        return values;
    }
}
