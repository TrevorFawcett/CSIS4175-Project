package com.example.csis4175choose2help.ui.donationlinks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.csis4175choose2help.databinding.ActivityDonationLinksBinding;

public class DonationDetailsActivity extends AppCompatActivity {

    private ActivityDonationLinksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_donation_details);

        binding = ActivityDonationLinksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}