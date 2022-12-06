package com.example.choose2help4175.ui.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.choose2help4175.CharityMap;
import com.example.choose2help4175.DonationListsActivity;
import com.example.choose2help4175.FreeServiceListsActivity;
import com.example.choose2help4175.HistoryActivity;
import com.example.choose2help4175.R;
import com.example.choose2help4175.UserDisplayActivity;
import com.example.choose2help4175.UserFormActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected FrameLayout frameLayout;
    private Context context;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    TextView userName;
    TextView userEmail;


    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_base);
        //frameLayout = (FrameLayout) findViewById(R.id.container);
        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_base, drawer );
        setContentView(view);
        context=this;
        initView();
        frameLayout = (FrameLayout) findViewById(R.id.container);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


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

        //This string will be changed based on user login
        //String emailAdd = checkCurrentUser();
        ArrayList<String> userInfo = new ArrayList<>();
        checkCurrentUser(userInfo);

        userEmail = drawer.findViewById(R.id.txtViewUserEmail);

        //userEmail = drawer.findViewById(R.id.txtViewUserEmail);

        //userEmail= findViewById(R.id.txtViewUserEmail);
        //userName.setText("");
        userEmail.setText(userInfo.get(0));

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

            case R.id.nav_userdata:

                Intent switchToSettings = new Intent(this, UserDisplayActivity.class);
                startActivity(switchToSettings);
                break;
        }

        return true;
    }
    public void checkCurrentUser(ArrayList<String> userInfo) {
        // [START check_current_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            String email = user.getEmail();
            String uid = user.getUid();
            String name = user.getDisplayName();
            //emailAdd = email;
            userInfo.add(email);
            userInfo.add(uid);


        } else {
            // No user is signed in

        }
        // [END check_current_user]
    }
}