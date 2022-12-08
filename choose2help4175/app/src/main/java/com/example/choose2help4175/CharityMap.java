package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.choose2help4175.adapter.UserSettingsAdapter;
import com.example.choose2help4175.databinding.ActivityBaseBinding;
import com.example.choose2help4175.databinding.ActivityCharityMapBinding;
import com.example.choose2help4175.databinding.ActivityDonationDetailsBinding;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class CharityMap extends BaseActivity implements OnMapReadyCallback, UserSettingsAdapter.ListItemListener {

    ActivityCharityMapBinding activityCharityMapBinding;


    private GoogleMap mMap;
    protected FrameLayout frameLayout;
    private Context context;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private Button char1;
    private Button char2;
    private Button char3;
    private Button char4;
    private Button char5;

    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_charity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
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

        char1 = (Button) findViewById(R.id.char1Btn);
        char2 = (Button) findViewById(R.id.char2Btn);
        char3 = (Button) findViewById(R.id.char3Btn);
        char4 = (Button) findViewById(R.id.char4Btn);
        char5 = (Button) findViewById(R.id.char5Btn);

        LatLng defaultLoc = new LatLng(49.261702821968306, -123.13852665087629);
        float zoomLevel = 11.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLoc, zoomLevel));

        LatLng childrensHospital = new LatLng(49.24437, -123.12422);
        mMap.addMarker(new MarkerOptions().position(childrensHospital).title("BC Children's Hospital"));

        LatLng bigBrothers = new LatLng(49.252607833172625, -123.08023217107609);
        mMap.addMarker(new MarkerOptions().position(bigBrothers).title("Big Brothers"));

        LatLng dewc = new LatLng(49.28233185178941, -123.10225451572576);
        mMap.addMarker(new MarkerOptions().position(dewc).title("Downtown EastSide Women's Centre"));

        LatLng mom2mom = new LatLng(49.27943336455819, -123.09939664564565);
        mMap.addMarker(new MarkerOptions().position(mom2mom).title("Mom2Mom"));

        LatLng seva = new LatLng(49.26095033617323, -123.15101710674585);
        mMap.addMarker(new MarkerOptions().position(seva).title("Seva Canada"));

        char1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float zoomLevel = 12.5f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(childrensHospital, zoomLevel));
            }
        });

        char2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float zoomLevel = 12.5f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bigBrothers, zoomLevel));
            }
        });

        char3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float zoomLevel = 12.5f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dewc, zoomLevel));
            }
        });

        char4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float zoomLevel = 12.5f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mom2mom, zoomLevel));
            }
        });

        char5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float zoomLevel = 12.5f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seva, zoomLevel));
            }
        });


    }

    @Override
    public void onListItemClick(int position) {

    }


}