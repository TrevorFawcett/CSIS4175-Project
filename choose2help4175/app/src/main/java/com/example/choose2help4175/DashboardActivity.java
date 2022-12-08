package com.example.choose2help4175;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.choose2help4175.R;
import com.example.choose2help4175.databinding.ActivityDashboardBinding;
import com.example.choose2help4175.ui.navigation.BaseActivity;

import java.util.Calendar;
import java.util.Date;


public class DashboardActivity extends BaseActivity {

    ActivityDashboardBinding activityDashboardBinding;
    Button charBtn;
    Button donationBtn;
    Button listBtn;
    Button regBtn;
    Button historyBtn;
    Button settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard, frameLayout);

        TextView greeting = (TextView) findViewById(R.id.greetingTextView);
        charBtn = (Button) findViewById(R.id.homeCharBtn);
        donationBtn = (Button) findViewById(R.id.homeDonationBtn);
        listBtn = (Button) findViewById(R.id.homeListBtn);
        regBtn = (Button) findViewById(R.id.homeRegBtn);
        historyBtn = (Button) findViewById(R.id.homeHistoryBtn);
        settingsBtn = (Button) findViewById(R.id.homeSettingsBtn);

        //setContentView(activityDashboardBinding.getRoot());
        charBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CharityMap.class);
                startActivity(intent);
            }
        });

        donationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, DonationListsActivity.class);
                startActivity(intent);
            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, FreeServiceListsActivity.class);
                startActivity(intent);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, RegisterFreeServiceActivity.class);
                startActivity(intent);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, UserDisplayActivity.class);
                startActivity(intent);
            }
        });

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour>= 12 && hour < 17){
            greeting.setText("Good Afternoon");
        } else if(hour >= 17 && hour < 21){
            greeting.setText("Good Evening");
        } else if(hour >= 21 && hour < 24){
            greeting.setText("Good Night");
        } else {
            greeting.setText("Good Morning");;
        }
    }

}