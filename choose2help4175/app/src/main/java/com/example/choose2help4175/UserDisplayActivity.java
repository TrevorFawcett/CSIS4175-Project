package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.choose2help4175.DAO.UserDataDAO;
import com.example.choose2help4175.adapter.OrganizationAdapter;
import com.example.choose2help4175.adapter.UserSettingsAdapter;

import com.example.choose2help4175.databinding.ActivityUserDisplayBinding;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.example.choose2help4175.model.UserData;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDisplayActivity extends BaseActivity implements UserSettingsAdapter.ListItemListener {
    UserDataDAO dao;
    private static final String TAG = UserDisplayActivity.class.getSimpleName();
    ArrayList<UserData> userList = new ArrayList<>();
    UserSettingsAdapter adapter;

    ActivityUserDisplayBinding userDisplayBinding;

    RecyclerView recyclerView;
    TextView txtTitle;
    boolean bringData = false;
    Button bringDataBtn;
    Button formBtn;
    ArrayList<String> usersData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_display);
        userDisplayBinding = ActivityUserDisplayBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_user_display, frameLayout);

        ArrayList<String> userInfo = new ArrayList<>();
        checkCurrentUser(userInfo);
        txtTitle = findViewById(R.id.TitleDisplay);
        txtTitle.setText("Current User\n" +userInfo.get(0));

        dao = new UserDataDAO();

        recyclerView = findViewById(R.id.UserSettingsRecyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        bringDataBtn = findViewById(R.id.userListBtn);

        adapter = new UserSettingsAdapter(this, usersData);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);

        if(bringData == false){
            bringDataBtn.setText("Load user info");
            bringData = true;
        }
        //first receive list and check if user exists

        //if exists load data, if not create userData object

        bringDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersData.clear();
                loadData(userInfo);
                bringDataBtn.setText("Reload user info");
                bringData = false;
            }
        });

        formBtn = findViewById(R.id.btnSwitchtoForm);
        formBtn.setText("Update info");
        formBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDisplayActivity.this, UserFormActivity.class);
                startActivity(intent);
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

    private void loadData(ArrayList<String> userInfo){

        dao.getUser(userInfo.get(1)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userList.clear();

                /*
                for(DataSnapshot data : snapshot.getChildren()){

                    UserData user = data.getValue(UserData.class);
                    userList.add(user);
                } */

                if (snapshot.getValue() == null) {
                    // The child doesn't exist
                    createUser(userInfo);
                    loadData(userInfo);
                }
                else{
                    UserData user = snapshot.getValue(UserData.class);
                    usersData.add("First Name: " + user.getFirstName());
                    usersData.add("Last Name: " +user.getLastName());
                    usersData.add("Email: " + user.getEmail());
                    usersData.add("Phone Number: " + user.getPhoneNumber());
                    usersData.add("Street Address: " +user.getAddress());
                    usersData.add("City: " + user.getCity());
                    usersData.add("Province: " + user.getProvince());
                    usersData.add("Country: " +user.getCountry());
                    usersData.add("Postal Code: " + user.getPostalCode());
                }


                int size = usersData.size();
                Log.e(TAG, size + " objects");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load FreeService List");
            }
        });
    }

    @Override
    public void onListItemClick(int position) {

    }

    public void createUser(ArrayList<String> userInfo){
        String emailAdd = userInfo.get(0);
        String userID = userInfo.get(1);

        UserData userData = new UserData();
        userData.setEmail(emailAdd);

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
}

