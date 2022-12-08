package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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
    boolean bringData = false;

    ArrayList<FreeService> fsList = new ArrayList<>();
    ArrayList<FreeService> fsAllList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_freeservice_lists);
        activityFreeserviceListsBinding = ActivityFreeserviceListsBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_freeservice_lists, frameLayout);

        recyclerView = rootView.findViewById(R.id.freeServiceRecyclerView);
        txtFreeServiceTitle = rootView.findViewById(R.id.txtTitleFreeService);
        btnFSBringList = rootView.findViewById(R.id.btnFSBringList);
        btnRegisterService = rootView.findViewById(R.id.btnRegisterService);

        if(bringData == false){
            btnFSBringList.setText("Bring the List");
            btnFSBringList.setEnabled(true);
            bringData = true;
        }

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

        btnFSBringList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
//                btnFSBringList.setText("Reload the list");
                btnFSBringList.setEnabled(false);
                bringData = false;
            }
        });
    }

    private void loadData(){

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                fsList.clear();

                for(DataSnapshot data : snapshot.getChildren()){

                    FreeService freeService = data.getValue(FreeService.class);
                    fsAllList.add(freeService);

                    FreeService fsImgNameDate = new FreeService(freeService.getImgFServiceType(), freeService.getfServiceName(), freeService.getfServiceDate());

                    fsList.add(fsImgNameDate);
                }

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

        FreeService freeService = fsAllList.get(position);

//        String fsId = freeService.getfServiceId();
//        String fsName = freeService.getfServiceName();
        int imgFsType = freeService.getImgFServiceType();
        String fsAddress = freeService.getfServiceAddress();
        String fsLocation = freeService.getfServiceLocation();
        String fsDate = freeService.getfServiceDate();
        String fsTime = freeService.getfServiceTime();
        String fsDescription = freeService.getfServiceDescription();

        SharedPreferences sharedPreferences = getSharedPreferences("FreeServiceContents",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
//        edit.putString("FSID", fsId);
//        edit.putString("FSNAME", fsName);
        edit.putInt("FSIMGTYPE", imgFsType);
        edit.putString("FSADDRESS", fsAddress);
        edit.putString("FSLOCATION", fsLocation);
        edit.putString("FSDATE", fsDate);
        edit.putString("FSTIME", fsTime);
        edit.putString("FSDESCRIPTION", fsDescription);

        edit.commit();

        Intent intent = new Intent(this, FreeServiceReservationActivity.class);

        startActivity(intent);

    }
}