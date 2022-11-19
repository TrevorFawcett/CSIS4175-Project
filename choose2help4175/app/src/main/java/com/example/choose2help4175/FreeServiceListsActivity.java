package com.example.choose2help4175;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FreeServiceListsActivity extends AppCompatActivity implements FreeServiceAdapter.ListItemListener {

    private static final String TAG = FreeServiceListsActivity.class.getSimpleName();

    RecyclerView recyclerView;
    TextView txtFreeServiceTitle;
    Button btnRegisterService;
    FreeServiceAdapter adapter;
    FreeServiceDAO dao;

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
        setContentView(R.layout.activity_donation_service_lists);

//        recyclerView = findViewById(R.id.freeServiceRecyclerView);
//        txtFreeServiceTitle = findViewById(R.id.txtTitleFreeService);
//        btnRegisterService = findViewById(R.id.btnRegisterService);
//
//        dao = new FreeServiceDAO();
//
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(manager);
//
//        adapter = new FreeServiceAdapter(this, fsList);
//        adapter.setListener(this);
//        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onListItemClick(int position) {

    }
}