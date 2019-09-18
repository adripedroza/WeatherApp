package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

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
        Map<String, Object> values = DarkSkyAPIWrapper.getValues();
        TextView temp = (TextView) findViewById(R.id.tempValue);
        TextView humid = (TextView) findViewById(R.id.humidValue);
        TextView rain = (TextView) findViewById(R.id.rainValue);
        TextView wind = (TextView) findViewById(R.id.windValue);

        temp.setText(values.get("Temperature").toString() + "f");
        humid.setText(values.get("Humidity").toString() + "%");
        rain.setText(values.get("Precipitation").toString() + "%");
        wind.setText(values.get("Wind Speed").toString() + "mph");
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
