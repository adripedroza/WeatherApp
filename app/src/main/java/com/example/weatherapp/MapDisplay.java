package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;





public class MapDisplay extends AppCompatActivity {

//    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_display);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
    }

    private void showWeatherInfo(){
        TextView infoView = (TextView)
                findViewById(R.id.textview_weather);
        infoView.setText(DarkSkyAPIWrapper.getValues().toString());
    }


//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        ArrayList<String> coords = GoogleAPIWrapper.getCoords();
//        // Add a marker in Sydney and move the camera
//        LatLng address = new LatLng(Float.parseFloat(coords.get(0)), Float.parseFloat(coords.get(1)));
//
//        mMap.addMarker(new MarkerOptions().position(address));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(address));
//    }
}
