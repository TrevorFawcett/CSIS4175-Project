package com.example.choose2help4175;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.choose2help4175.R;
import com.example.choose2help4175.databinding.ActivityHistoryBinding;
import com.example.choose2help4175.ui.navigation.BaseActivity;

public class HistoryActivity extends BaseActivity {
    ActivityHistoryBinding activityHistoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHistoryBinding = ActivityHistoryBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_history, frameLayout);
        //setContentView(R.layout.activity_history);
    }
}