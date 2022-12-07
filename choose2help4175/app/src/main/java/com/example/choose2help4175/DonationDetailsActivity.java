package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.choose2help4175.model.UserAction;
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
    Button btnAddReview;
    Button btnSeeReview;
    RecyclerView recyclerViewReviews;
    ReviewDAO dao;
    ReviewAdapter adapter;
    String ozCode;
    private static final String TAG = "REVIEW_TABLE";
    List<String> ReviewAuthors = new ArrayList<>(Arrays.asList("Mary N", "Kate L", "Nick V"));
    List<String> ReviewTexts = new ArrayList<>(Arrays.asList("Great organization!", "I enjoyed volunteering for them!", "They helped my aunt a lot!"));


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

        imgOz = rootView.findViewById(R.id.imgOZ);
        txtOzTitle = rootView.findViewById(R.id.txtTitleOZ);
        txtOzDescription = rootView.findViewById(R.id.txtDescriptionOZ);
        txtOzAddress = rootView.findViewById(R.id.txtAddressOZ);
        txtOzEmail = rootView.findViewById(R.id.txtEmailOZ);
        txtOzPhoneNumber = rootView.findViewById(R.id.txtPhoneOZ);
        btnDonation = rootView.findViewById(R.id.btnDonationOZ);
        btnVolunteer = rootView.findViewById(R.id.btnVolunteerOZ);
        btnAddReview = rootView.findViewById(R.id.btnAddReview);
        btnSeeReview = rootView.findViewById(R.id.btnSeeReviews);

        recyclerViewReviews = rootView.findViewById(R.id.recyclerViewReviews);

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
        ozCode = getIntent().getExtras().getString("OZCODE");

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

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerViewReviews.setLayoutManager(manager);

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

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonationDetailsActivity.this, WriteReviewActivity.class);
                intent.putExtra("OZCODE", ozCode);
                startActivity(intent);
            }
        });

        btnSeeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

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



    private void loadData(){

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data : snapshot.getChildren()){
                    Review review = data.getValue(Review.class);
                    Review reviewFull = new Review(review.getReviewText(),review.getReviewAuthor(),ozCode );
                    reviewList.add(reviewFull);

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load Review List");
            }
        });
    }


}


