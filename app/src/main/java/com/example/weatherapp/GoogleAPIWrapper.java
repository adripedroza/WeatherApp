package com.example.weatherapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GoogleAPIWrapper {
    String APIKey, URLbase;

    public GoogleAPIWrapper() {
        this.APIKey = MainActivity.resources.getString(R.string.google_API_key);
        this.URLbase = MainActivity.resources.getString(R.string.geo_request);
    }

    public ArrayList<String> coordRequest(String address) {
        HTTPRequest request = new HTTPRequest();
        String encodedAddress = "address=" + request.encodeString(address);

        String URL = buildURL(encodedAddress);

        try {
            JSONObject object = request.execute(URL).get();

            JSONArray location = object.getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONArray("location");

            ArrayList<String> coords = new ArrayList<String>();

            coords.add(location.getJSONObject(0).toString());
            coords.add(location.getJSONObject(1).toString());

            return coords;
        } catch (Exception e) {
            System.out.println("Error in Google API call: " + e.getStackTrace().toString());
        }
        return null;
    }

    private String buildURL(String param) {
        return URLbase + param + "&key=" + APIKey;
    }
}
