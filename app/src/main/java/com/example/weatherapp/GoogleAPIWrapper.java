package com.example.weatherapp;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GoogleAPIWrapper {
    private static String locName;
    private static LatLng coords;
    private static final GoogleAPIWrapper GOOGLE_API_WRAPPER = new GoogleAPIWrapper();
    String APIKey, URLbase;

    private GoogleAPIWrapper() {
        this.APIKey = MainActivity.resources.getString(R.string.google_API_key);
        this.URLbase = MainActivity.resources.getString(R.string.geo_request);
    }

    protected static GoogleAPIWrapper getInstance(){
        return GOOGLE_API_WRAPPER;
    }

    protected void coordRequest(String address) {
        String URL = buildURL(address);
        new HTTPRequest().execute(URL);

    }
    protected static LatLng parseCoords(JSONObject object){
        JSONObject location;
        try {
            location = object.getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");
            coords = new LatLng(Double.parseDouble(location.get("lat").toString()), Double.parseDouble(location.get("lng").toString()));
            locName = object.getJSONArray("results").getJSONObject(0).getString("formatted_address");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return coords;
    }

    private String buildURL(String param) {
        param = "address=" + encodeString(param);
        return URLbase + param + "&key=" + APIKey;
    }

    private static String encodeString(String text){
        String encodedText = text;
        try {
            encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedText;
    }

    protected static LatLng getCoords() {
        return coords;
    }
    protected static String getLocName(){
        return locName;
    }
}
