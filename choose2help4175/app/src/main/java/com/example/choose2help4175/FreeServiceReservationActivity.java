package com.example.choose2help4175;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.choose2help4175.databinding.ActivityFreeServiceReservationBinding;
import com.example.choose2help4175.ui.navigation.BaseActivity;

public class FreeServiceReservationActivity extends BaseActivity {
    ActivityFreeServiceReservationBinding activityFreeServiceReservationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_free_service_reservation);
        activityFreeServiceReservationBinding = ActivityFreeServiceReservationBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_free_service_reservation, frameLayout);
    }
}