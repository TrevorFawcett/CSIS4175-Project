package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.choose2help4175.DAO.ReviewDAO;
import com.example.choose2help4175.databinding.ActivityWriteReviewBinding;
import com.example.choose2help4175.model.Review;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriteReviewActivity extends BaseActivity {
    ActivityWriteReviewBinding writeReviewBinding;
    EditText reviewTextEdit;
    String reviewAuthor;
    String reviewText;
    String ozCode;
    Button btnSubmitReview;

    private static final String TAG = "REVIEW_TABLE";
    List<String> ReviewAuthors = new ArrayList<>(Arrays.asList("Mary N", "Kate L", "Nick V"));
    List<String> ReviewTexts = new ArrayList<>(Arrays.asList("Great organization!", "I enjoyed volunteering for them!", "They helped my aunt a lot!"));
    List<String> ReviewDates = new ArrayList<>(Arrays.asList("11.11.2022", "12.11.2022", "13.11.2022"));

    ArrayList<Review> allReviewList = new ArrayList<>();
    ArrayList<Review> reviewList = new ArrayList<>();

    ReviewDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_write_review);
        writeReviewBinding = ActivityWriteReviewBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_write_review, frameLayout);

        btnSubmitReview = findViewById(R.id.btnSubmitReview);
        reviewTextEdit = findViewById(R.id.editTextReviewText);

        ozCode = getIntent().getExtras().getString("OZCODE");
        ArrayList<String> userInfo = new ArrayList<>();
        checkCurrentUser(userInfo);
        reviewAuthor = userInfo.get(0);

        dao = new ReviewDAO();


        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewText = reviewTextEdit.getText().toString();
                createReview();
                Toast.makeText(WriteReviewActivity.this, "Review created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WriteReviewActivity.this, DonationDetailsActivity.class);
                startActivity(intent);

                // redirect back to Details activity after 2 seconds of review submission
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(WriteReviewActivity.this, DonationDetailsActivity.class);
//                        startActivity(intent);
//                    }
//                }, 2000);
            }
        });
    }

    private void createReview(){

            Review nr = new Review(reviewText, reviewAuthor, ozCode);
            dao.createReview(nr).addOnSuccessListener(new OnSuccessListener<Void>() {
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