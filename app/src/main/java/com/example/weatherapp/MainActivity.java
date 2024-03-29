package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
    private static MainActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resources = getResources();
        super.onCreate(savedInstanceState);
        instance = this;

        setContentView(R.layout.activity_main);
    }
    public void submitLocation(View view){
        TextInputEditText locationText = findViewById(R.id.locationText);
        String address = locationText.getText().toString();

        GoogleAPIWrapper.getInstance().coordRequest(address);
    }

    protected static Context getAppContext(){
         return  instance.getApplicationContext();
    }

}

