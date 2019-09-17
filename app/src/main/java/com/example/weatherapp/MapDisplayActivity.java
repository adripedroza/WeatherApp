package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MapDisplayActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_display);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
        showWeatherInfo();
    }

    private void showWeatherInfo(){
        TextView infoView = (TextView)
                findViewById(R.id.textview_weather);
        Map<String, Object> values = DarkSkyAPIWrapper.getValues();
        String weatherInfo =
            "Temperature: " + values.get("Temperature") + "\n"
            + "Humidity: " + values.get("Humidity") + "\n"
            + "Wind Speed: " + values.get("Wind Speed") + "\n"
            + "Precipitation: " + values.get("Precipitation")
        ;
        infoView.setText(weatherInfo);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng coords = GoogleAPIWrapper.getCoords();
        map.setMinZoomPreference(10);
        map.addMarker(new MarkerOptions().position(coords).title(GoogleAPIWrapper.getLocName()));
        map.moveCamera(CameraUpdateFactory.newLatLng(coords));

    }
}
