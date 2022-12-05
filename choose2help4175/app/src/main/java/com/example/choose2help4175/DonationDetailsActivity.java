package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.choose2help4175.DAO.FreeServiceDAO;
import com.example.choose2help4175.DAO.ReviewDAO;
import com.example.choose2help4175.adapter.FreeServiceAdapter;
import com.example.choose2help4175.adapter.ReviewAdapter;
import com.example.choose2help4175.databinding.ActivityDonationDetailsBinding;
import com.example.choose2help4175.model.FreeService;
import com.example.choose2help4175.model.Review;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonationDetailsActivity extends BaseActivity {

    ActivityDonationDetailsBinding activityDonationDetailsBinding;

    ImageView imgOz;
    TextView txtOzTitle;
    TextView txtOzDescription;
    TextView txtOzAddress;
    TextView txtOzEmail;
    TextView txtOzPhoneNumber;
    Button btnDonation;
    Button btnVolunteer;
    RecyclerView recyclerViewReviews;
    ReviewDAO dao;
    ReviewAdapter adapter;
    private static final String TAG = "REVIEW_TABLE";
    List<String> ReviewAuthors = new ArrayList<>(Arrays.asList("Mary N", "Kate L", "Nick V"));
    List<String> ReviewTexts = new ArrayList<>(Arrays.asList("Great organization!", "I enjoyed volunteering for them!", "They helped my aunt a lot!"));
    List<String> ReviewDates = new ArrayList<>(Arrays.asList("11.11.2022", "12.11.2022", "13.11.2022"));

    ArrayList<Review> allReviewList = new ArrayList<>();
    ArrayList<Review> reviewList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_donation_details);
        activityDonationDetailsBinding = ActivityDonationDetailsBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_donation_details, frameLayout);

//        imgOz = findViewById(R.id.imgOZ);
//        txtOzTitle = findViewById(R.id.txtTitleOZ);
//        txtOzDescription = findViewById(R.id.txtDescriptionOZ);
//        txtOzAddress = findViewById(R.id.txtAddressOZ);
//        txtOzEmail = findViewById(R.id.txtEmailOZ);
//        txtOzPhoneNumber = findViewById(R.id.txtPhoneOZ);
//        btnDonation = findViewById(R.id.btnDonationOZ);
//        btnVolunteer = findViewById(R.id.btnVolunteerOZ);

        imgOz = activityDonationDetailsBinding.imgOZ;
        txtOzTitle = activityDonationDetailsBinding.txtTitleOZ;
        txtOzDescription = activityDonationDetailsBinding.txtDescriptionOZ;
        txtOzAddress = activityDonationDetailsBinding.txtAddressOZ;
        txtOzEmail = activityDonationDetailsBinding.txtEmailOZ;
        txtOzPhoneNumber = activityDonationDetailsBinding.txtPhoneOZ;
        btnDonation = activityDonationDetailsBinding.btnDonationOZ;
        btnVolunteer = activityDonationDetailsBinding.btnVolunteerOZ;
        recyclerViewReviews = activityDonationDetailsBinding.recyclerViewReviews;

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

        dao = new ReviewDAO();

        adapter = new ReviewAdapter(this, reviewList);
        //adapter.setListener();
        recyclerViewReviews.setAdapter(adapter);

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

        removeExistingData();
        createReview();
        loadData();

    }

    private void removeExistingData(){
        dao.remove().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Success Remove Review Table");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Failed to remove Review table");
            }
        });
    }

    private void createReview(){

        for(int i = 0; i < ReviewTexts.size(); i++){
            Review review = new Review(ReviewTexts.get(i), ReviewAuthors.get(i), ReviewDates.get(i));
            dao.createReview(review).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Success add review");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Failed to create review");
                }
            });
        }

    }

    private void loadData(){

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data : snapshot.getChildren()){

                    Review review = data.getValue(Review.class);
                    allReviewList.add(review);

                    //Review reviewNew = new Review(review.getImgFServiceType(), freeService.getfServiceName(), freeService.getfServiceDate());

                    //.add(fsImgNameDate);
                }

//                if(getIntent().getExtras() != null){
//
//                    fsName = getIntent().getExtras().getString("FSName");
//                    fsImg = getIntent().getExtras().getInt("FSTYPEIMAGE");
//                    fsDate = getIntent().getExtras().getString("FSDATE");
//
//                    FreeService eventfs = new FreeService(fsImg, fsName, fsDate);
//                    fsList.add(eventfs);
//                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load Review List");
            }
        });
    }


}