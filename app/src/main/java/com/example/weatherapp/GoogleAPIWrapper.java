package com.example.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GoogleAPIWrapper {
    private static JSONObject addressInfo = null;
    private static final GoogleAPIWrapper GOOGLE_API_WRAPPER = new GoogleAPIWrapper();
    private static ArrayList<String> coords = null;
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
    protected static void parseCoords(JSONObject object){
        JSONObject location = null;
        coords = new ArrayList<>();
        try {
            addressInfo = object.getJSONObject("results");

            location = object.getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");
            coords.add(location.get("lat").toString());
            coords.add(location.get("lng").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    protected static JSONObject getAddressInfo() {
        return addressInfo;
    }

    protected static ArrayList<String> getCoords() {
        return coords;
    }
}
