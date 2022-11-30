package com.example.choose2help4175;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.choose2help4175.model.Organization;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDisplayActivity extends AppCompatActivity {

    DatabaseReference dbReference;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);

        //FirebaseDatabase db = FirebaseDatabase.getInstance();
        display = findViewById(R.id.userEmail);
        UserObj currentUser = new UserObj();
        checkCurrentUser(display, currentUser);

        String username = currentUser.getEmail();
        //Toast.makeText(getApplicationContext(),username,Toast.LENGTH_SHORT).show();

        Button btn = findViewById(R.id.switchToFormBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDisplayActivity.this,UserFormActivity.class);
                startActivity(i);
            }
        });

    }

    public void checkCurrentUser(TextView tv, UserObj current) {
        // [START check_current_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            String email = user.getEmail();
            //String uid = user.getUid();

            current.setEmail(email);
            tv.setText(email);

        } else {
            // No user is signed in
        }
        // [END check_current_user]
    }





}