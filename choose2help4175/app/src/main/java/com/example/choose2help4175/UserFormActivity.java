package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.choose2help4175.DAO.UserDataDAO;
import com.example.choose2help4175.model.UserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class UserFormActivity extends AppCompatActivity {
    //address, city, province, country, postal code
    UserDataDAO dao;
    private static final String TAG = UserFormActivity.class.getSimpleName();

    EditText fName;
    EditText lName;
    EditText phone;
    Button submitBtn;
    TextView userData;
    EditText streetAdd;
    EditText city;
    EditText province;
    EditText country;
    EditText postalCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        //This string will be changed based on user login
        //String emailAdd = checkCurrentUser();
        ArrayList<String> userInfo = new ArrayList<>();
        checkCurrentUser(userInfo);

        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        phone = findViewById(R.id.phoneNumber);
        streetAdd = findViewById(R.id.streetAdd);
        city = findViewById(R.id.city);
        province = findViewById(R.id.province);
        country = findViewById(R.id.country);
        postalCode = findViewById(R.id.postalCode);

        dao = new UserDataDAO();


        TextView email = findViewById(R.id.userEmail);
        email.setText(userInfo.get(0));

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            createUserData(userInfo);

                //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                //DatabaseReference usersRef = rootRef.child("users");
                //User user = new User("John", 25, "male", "101 Main Street", "john@email.com");
                //usersRef.child(uid).setValue(user);

            }
        });


    }

    private void createUserData(ArrayList<String> userInfo){
        String emailAdd = userInfo.get(0);
        String userID = userInfo.get(1);

        String firstName = fName.getText().toString();
        String lastName = lName.getText().toString();
        String pNumber = phone.getText().toString();
        String streetAddress = streetAdd.getText().toString();
        String mCity = city.getText().toString();
        String mProvince = province.getText().toString();
        String mCountry = country.getText().toString();
        String mPostalCode = postalCode.getText().toString();

        UserData userData = new UserData();
        userData.setEmail(emailAdd);
        userData.setFirstName(firstName);
        userData.setLastName(lastName);
        userData.setPhoneNumber(pNumber);
        userData.setAddress(streetAddress);
        userData.setCity(mCity);
        userData.setProvince(mProvince);
        userData.setCountry(mCountry);
        userData.setPostalCode(mPostalCode);


        dao.createUserData(userData, userID).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Success add UserData");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Failed to create UserData");
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
            //emailAdd = email;
            userInfo.add(email);
            userInfo.add(uid);


        } else {
            // No user is signed in

        }
        // [END check_current_user]
    }

}



