package com.example.etix42.miniprojet4;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Float varLongitude;
    Float varLatitude;
    String varTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Bundle bundle1 = getIntent().getExtras();

        varTitle = bundle1.getString("Title");
        varLongitude = bundle1.getFloat("Longitude");
        varLatitude = bundle1.getFloat("Latitude");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng marker = new LatLng(varLongitude, varLatitude);
        mMap.addMarker(new MarkerOptions().position(marker).title(varTitle));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }
}
