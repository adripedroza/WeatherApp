package com.example.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        HttpURLConnection connection = null;
        try{
            URL url = new URL(strings[0]);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = rd.readLine()) != null){
                response += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response){
       JSONObject object = parse(response);
       if(object.has("results")){ //if JSON response is google geolocation
          LatLng coords = GoogleAPIWrapper.parseCoords(object);
          if(coords != null) {
              DarkSkyAPIWrapper.getInstance().getCurrentWeather(coords);
          }
          else{
              errorToast();
          }
      }
      else if(object.has("currently")){ // if JSON response is darksky weather info
          DarkSkyAPIWrapper.getInstance().setValues(object);
          Context context = MainActivity.getAppContext();
          Intent intent = new Intent(context, MapDisplayActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(intent);
       }
  }

    private static JSONObject parse(String response){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
        } catch (JSONException e) {
            System.out.println("Error: " + e.toString());
        }
        return jsonObject;
    }

    private void errorToast(){
        Toast toast = Toast.makeText(MainActivity.getAppContext(), "Invalid Address : Unable to resolve coordinates", Toast.LENGTH_SHORT);
        toast.show();
    }

}
