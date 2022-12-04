package com.example.choose2help4175.ui.navigation;

import android.os.Bundle;
import android.view.View;

import com.example.choose2help4175.R;
import com.example.choose2help4175.databinding.ActivityDashboardBinding;


public class DashboardActivity extends BaseActivity {

    ActivityDashboardBinding activityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard, frameLayout);
        //setContentView(activityDashboardBinding.getRoot());


    }
}