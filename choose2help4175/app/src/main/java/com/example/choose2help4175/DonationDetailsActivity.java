package com.example.choose2help4175;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DonationDetailsActivity extends AppCompatActivity {

    ImageView imgOz;
    TextView txtOzTitle;
    TextView txtOzDescription;
    TextView txtOzAddress;
    TextView txtOzEmail;
    TextView txtOzPhoneNumber;
    Button btnDonation;
    Button btnVolunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_details);

        imgOz = findViewById(R.id.imgOZ);
        txtOzTitle = findViewById(R.id.txtTitleOZ);
        txtOzDescription = findViewById(R.id.txtDescriptionOZ);
        txtOzAddress = findViewById(R.id.txtAddressOZ);
        txtOzEmail = findViewById(R.id.txtEmailOZ);
        txtOzPhoneNumber = findViewById(R.id.txtPhoneOZ);
        btnDonation = findViewById(R.id.btnDonationOZ);
        btnVolunteer = findViewById(R.id.btnVolunteerOZ);

        String ozTitle = getIntent().getExtras().getString("OZTITLE");
        String ozDescription = getIntent().getExtras().getString("OZDESCRIPTON");
        String ozAddress = getIntent().getExtras().getString("OZADDRESS");
        String ozCity = getIntent().getExtras().getString("OZCITY");
        String ozProvince = getIntent().getExtras().getString("OZPROVINCE");
        String ozPostalCode = getIntent().getExtras().getString("OZPOSTALCODE");
        String ozEmail = getIntent().getExtras().getString("OZEMAIL");
        String ozPhoneNumber = getIntent().getExtras().getString("OZPHONENUMBER");
        int ozImage = getIntent().getExtras().getInt("OZIMAGE");
        String ozDonationURL = getIntent().getExtras().getString("OZDONATIONURL");
        String ozVolunteerURL = getIntent().getExtras().getString("OZVOLUNTEERURL");

        imgOz.setImageResource(ozImage);
        txtOzTitle.setText(ozTitle);
        txtOzDescription.setText(ozDescription);

        String fullAddress = ozAddress + " " + ozCity + " " + ozProvince + ", "+ ozPostalCode;
        txtOzAddress.setText(fullAddress);
        txtOzEmail.setText(ozEmail);
        txtOzPhoneNumber.setText(ozPhoneNumber);

        btnDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ozDonationURL));
                startActivity(i);
            }
        });

        btnVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ozVolunteerURL));
                startActivity(i);
            }
        });


    }
}