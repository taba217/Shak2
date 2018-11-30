package com.example.harith.shak;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double v,h;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
       // double v= 15.525565,v1=32.575005;//rwda
        double v2=15.535188,v3=32.508806;//abdo alhay
        double v4= 15.527966,v5= 32.515708;//jpra 9
        Intent iin= getIntent();
        Bundle extras = iin.getExtras();
        if(extras != null) {
             v = extras.getDouble("v");
             h = extras.getDouble("h");
             s = extras.getString("title");
        }
        LatLng mosq1 = new LatLng(v,h);
        LatLng mosq2 = new LatLng(v2, v3);
        mMap.addMarker(new MarkerOptions().position(mosq1).title(s));//.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
      //mMap.addMarker(new MarkerOptions().snippet("my loc"));//.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.addMarker(new MarkerOptions().position(mosq2).title("مسجد جامعة أفريقيا العالمية"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(v4,v5)).title("مسجد جبرة شمال 9"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(v,h),15));
    }
}