package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.choose2help4175.DAO.FreeServiceDAO;
import com.example.choose2help4175.DAO.HistoryDAO;
import com.example.choose2help4175.R;
import com.example.choose2help4175.adapter.FreeServiceAdapter;
import com.example.choose2help4175.adapter.HistoryAdapter;
import com.example.choose2help4175.databinding.ActivityHistoryBinding;
import com.example.choose2help4175.model.FreeService;
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

public class HistoryActivity extends BaseActivity {
    ActivityHistoryBinding activityHistoryBinding;
    RecyclerView recyclerView;
    HistoryDAO dao;
    HistoryAdapter adapter;

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
        //setContentView(R.layout.activity_history);

        recyclerView = rootView.findViewById(R.id.recyclerViewHistory);
        dao = new HistoryDAO();



        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new HistoryAdapter(this, userActionList);
        recyclerView.setAdapter(adapter);
        removeExistingData();
        createFreeService();
        loadData();



    }

    private void removeExistingData(){
        dao.remove().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Success Remove History Table");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Failed to remove HistoryTable");
            }
        });
    }

    private void createFreeService(){
        for(int i = 0; i < userActions.size(); i++){
            UserAction userAction = new UserAction(userActions.get(i),userDates.get(i));
            dao.createUserAction(userAction).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Success add User Action");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Failed to create User Action");
                }
            });
        }
    }

    private void loadData(){

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data : snapshot.getChildren()){

                    UserAction userAction = data.getValue(UserAction.class);
                    allUserActionList.add(userAction);
                    UserAction userActionDummy = new UserAction(userAction.getUserAction(), userAction.getActionDate());
                    userActionList.add(userActionDummy);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load FreeService List");
            }
        });
    }
}