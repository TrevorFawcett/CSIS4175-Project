package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.choose2help4175.DAO.UserActionDAO;
import com.example.choose2help4175.adapter.HistoryAdapter;
import com.example.choose2help4175.databinding.ActivityHistoryBinding;
import com.example.choose2help4175.model.UserAction;
import com.example.choose2help4175.model.UserData;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryActivity extends BaseActivity {
    ActivityHistoryBinding activityHistoryBinding;
    RecyclerView recyclerView;
    UserActionDAO userActionDAO;
    HistoryAdapter adapter;
    Button btnSeeHistory;
    String email;

    ArrayList<UserAction> userActionList = new ArrayList<>();
    ArrayList<UserAction> allUserActionList = new ArrayList<>();

    private final String TAG = "HISTORYACTIVITY";

    List<String> userActions = new ArrayList<>(Arrays.asList("Donation to Charity1", "Volunteer at Charity 2"));
    List<String> userDates = new ArrayList<>(Arrays.asList("11.12.2022", "04.09.2022"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityHistoryBinding = ActivityHistoryBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_history, frameLayout);
        Log.d(TAG, "Created view ok");
        //setContentView(R.layout.activity_history);

        recyclerView = rootView.findViewById(R.id.recyclerViewHistory);
        userActionDAO = new UserActionDAO();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new HistoryAdapter(this, userActionList);
        recyclerView.setAdapter(adapter);

        btnSeeHistory = findViewById(R.id.btnSeeHistory);

        btnSeeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

    }

    private void loadData(){
        userActionDAO.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    Log.d("HISTORY", "We are at line 89");
                    UserAction userAction = data.getValue(UserAction.class);
                    UserAction userActionFull = new UserAction(userAction.getUserAction());
                    Log.d("HISTORY_userid",": " + userAction.getUserId());
                    Log.d("HISTORY_userAction", ": " +userAction.getUserAction());

                        userActionList.add(userActionFull);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load FreeService List");
            }
        });
    }
    public void checkCurrentUser(ArrayList<String> userInfo) {
        // [START check_current_user]
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in

//            String email = user.getEmail();
            email = user.getEmail();
            String uid = user.getUid();

            UserData currUser = new UserData(email);

            //String name = user.getFirstName();
            //emailAdd = email;
            userInfo.add(email);
            userInfo.add(uid);
            userInfo.add(currUser.getFirstName());


        } else {
            // No user is signed in

        }
        // [END check_current_user]
    }
}