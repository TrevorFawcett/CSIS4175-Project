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
import android.widget.Toast;

import com.example.choose2help4175.DAO.FreeServiceDAO;
import com.example.choose2help4175.adapter.FreeServiceAdapter;
import com.example.choose2help4175.databinding.ActivityFreeserviceListsBinding;
import com.example.choose2help4175.model.FreeService;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FreeServiceListsActivity extends BaseActivity implements FreeServiceAdapter.ListItemListener {

    private static final String TAG = FreeServiceListsActivity.class.getSimpleName();
    ActivityFreeserviceListsBinding activityFreeserviceListsBinding;

    RecyclerView recyclerView;
    TextView txtFreeServiceTitle;
    Button btnFSBringList;
    Button btnRegisterService;
    FreeServiceAdapter adapter;
    FreeServiceDAO dao;
    String fsName;
    int fsImg;
    String fsDate;

    ArrayList<FreeService> fsList = new ArrayList<>();
    ArrayList<FreeService> fsAllList = new ArrayList<>();

    List<String> FScodes = new ArrayList<>(Arrays.asList("fs01", "fs02"));
    List<String> FSNames = new ArrayList<>(Arrays.asList("Free Coffee", "Haircuts for Dignity"));
    List<Integer> FSImageTypes = new ArrayList<>(Arrays.asList(R.drawable.food, R.drawable.amenities, R.drawable.cultural, R.drawable.educational));
    List<String> FSAddress = new ArrayList<>(Arrays.asList("59 Powell St, Vancouver, BC V6A 1E9", "North Vancouver"));
    List<String> FSLocation = new ArrayList<>(Arrays.asList("The Dugout", "Hair Shop"));
    List<String> FSDate = new ArrayList<>(Arrays.asList("2022-10-10", "2022-12-25"));
    List<String> FSTime = new ArrayList<>(Arrays.asList("2 PM", "10 am"));
    List<String> FSDescription = new ArrayList<>(Arrays.asList("Delicious coffee makes for a beautiful day.", "Start your day with a great haircut."));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_freeservice_lists);
        activityFreeserviceListsBinding = ActivityFreeserviceListsBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_freeservice_lists, frameLayout);

//        recyclerView = findViewById(R.id.freeServiceRecyclerView);
//        txtFreeServiceTitle = findViewById(R.id.txtTitleFreeService);
//        btnFSBringList = findViewById(R.id.btnFSBringList);
//        btnRegisterService = findViewById(R.id.btnRegisterService);

        recyclerView = rootView.findViewById(R.id.freeServiceRecyclerView);
        txtFreeServiceTitle = rootView.findViewById(R.id.txtTitleFreeService);
        btnFSBringList = rootView.findViewById(R.id.btnFSBringList);
        btnRegisterService = rootView.findViewById(R.id.btnRegisterService);

        dao = new FreeServiceDAO();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);


        adapter = new FreeServiceAdapter(this, fsList);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);

        btnRegisterService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FreeServiceListsActivity.this, RegisterFreeServiceActivity.class);
                startActivity(intent);
            }
        });

//        btnFSBringList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(FreeServiceListsActivity.this, "Clicking bring list" , Toast.LENGTH_SHORT).show();
//                removeExistingData();
//                createFreeService();
//
//                loadData();
//            }
//        });

        btnFSBringList.setOnClickListener((View view)->{
            Toast.makeText(FreeServiceListsActivity.this, "Clicking bring list" , Toast.LENGTH_SHORT).show();

                removeExistingData();
                createFreeService();
                loadData();
        });

    }

    private void removeExistingData(){
        dao.remove().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Success Remove FreeService Table");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Failed to remove FreeService");
            }
        });
    }

    private void createFreeService(){

        for(int i = 0; i < FSNames.size(); i++){
            FreeService freeService = new FreeService(FScodes.get(i), FSNames.get(i), FSImageTypes.get(i), FSAddress.get(i), FSLocation.get(i), FSDate.get(i), FSTime.get(i), FSDescription.get(i));

            dao.createFreeService(freeService).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Success add FreeService");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Failed to create FreeService");
                }
            });
        }

        if(getIntent().getExtras() != null){

            fsName = getIntent().getExtras().getString("FSName");
            fsImg = getIntent().getExtras().getInt("FSTYPEIMAGE");
            fsDate = getIntent().getExtras().getString("FSDATE");

            FreeService eventfs = new FreeService(fsImg, fsName, fsDate);
            dao.createFreeService(eventfs).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Success add FreeService Register");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Failed to Register FreeService");
                }
            });
        }
    }

    private void loadData(){

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                fsList.clear();

                for(DataSnapshot data : snapshot.getChildren()){

                    FreeService freeService = data.getValue(FreeService.class);
                    fsAllList.add(freeService);

                    FreeService fsImgNameDate = new FreeService(freeService.getImgFServiceType(), freeService.getfServiceName(), freeService.getfServiceDate());

                    fsList.add(fsImgNameDate);
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
                Log.e(TAG, "Failed to load FreeService List");
            }
        });
    }

    @Override
    public void onListItemClick(int position) {

    }
}