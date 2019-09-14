package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resources = getResources();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitLocation(View view){
        TextInputEditText locationText = findViewById(R.id.locationText);
        String address = locationText.getText().toString();

        GoogleAPIWrapper gw = new GoogleAPIWrapper();
        ArrayList<String> coords = gw.coordRequest(address);


        DarkSkyAPIWrapper ds = new DarkSkyAPIWrapper();
        JSONObject data = ds.getCurrentWeather(coords);

        System.out.println(data.toString());
    }

}

