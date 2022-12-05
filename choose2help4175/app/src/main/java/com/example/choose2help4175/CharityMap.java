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
import android.widget.FrameLayout;

import com.example.choose2help4175.databinding.ActivityBaseBinding;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.choose2help4175.databinding.ActivityCharityMapBinding;
import com.google.android.material.navigation.NavigationView;

public class CharityMap extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    // I had to manually add navigation drawer, because I couldn't extend BaseActivity
    private GoogleMap mMap;
    private ActivityCharityMapBinding binding;
    protected FrameLayout frameLayout;
    private Context context;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityCharityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context=this;
        initView();
        frameLayout = (FrameLayout) findViewById(R.id.container);
        //View rootView = getLayoutInflater().inflate(R.layout.activity_charity_map, frameLayout);


        frameLayout = (FrameLayout) findViewById(R.id.container);
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        menuItem.setChecked(true);

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                // Need to change this to another activity.
                // I can't redirect to map because the app crashes.
                Intent switchToHomeIntent = new Intent(this, CharityMap.class);
                startActivity(switchToHomeIntent);

            case R.id.nav_map:
                Intent switchToMapIntent = new Intent(this, CharityMap.class);
                startActivity(switchToMapIntent);

                break;
            case R.id.nav_donations:
                Intent switchToDonationsIntent = new Intent(this, DonationListsActivity.class);
                startActivity(switchToDonationsIntent);

                break;
            case R.id.nav_offerservice:

                Intent switchToFreeServiceIntent = new Intent(this, FreeServiceListsActivity.class);
                startActivity(switchToFreeServiceIntent);
                break;

            case R.id.nav_donationhistory:

                Intent switchToHistoryIntent = new Intent(this, HistoryActivity.class);
                startActivity(switchToHistoryIntent);
                break;
        }

        return true;
    }


}