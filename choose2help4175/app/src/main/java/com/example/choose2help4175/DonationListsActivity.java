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

import com.example.choose2help4175.DAO.OrganizationDAO;
import com.example.choose2help4175.adapter.OrganizationAdapter;
import com.example.choose2help4175.databinding.ActivityDashboardBinding;
import com.example.choose2help4175.databinding.ActivityDonationListsBinding;
import com.example.choose2help4175.model.Organization;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonationListsActivity extends BaseActivity implements OrganizationAdapter.ListItemListener{

    private static final String TAG = DonationListsActivity.class.getSimpleName();
    ActivityDonationListsBinding activityDonationListsBinding;

    RecyclerView recyclerView;
    TextView txtOrganizationTitle;
    Button btnBringData;
    OrganizationAdapter adapter;
    OrganizationDAO dao;

    ArrayList<Organization> ozList = new ArrayList<>();
    ArrayList<Organization> ozAllList = new ArrayList<>();
    boolean bringData = false;
    //String ozKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_donation_lists);
        activityDonationListsBinding = ActivityDonationListsBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_donation_lists, frameLayout);

//        txtOrganizationTitle = findViewById(R.id.txtTitleOZList);
//        recyclerView = findViewById(R.id.OZRecyclerView);
//        btnBringData = findViewById(R.id.btnOZBringList);

        txtOrganizationTitle = rootView.findViewById(R.id.txtTitleOZList);
        recyclerView = rootView.findViewById(R.id.OZRecyclerView);
        btnBringData = rootView.findViewById(R.id.btnOZBringList);

//        txtOrganizationTitle.setText("Organization List");

        if(bringData == false){
            btnBringData.setText("Bring the List");
            bringData = true;
        }


        //initialize the DB
        dao = new OrganizationDAO();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new OrganizationAdapter(this, ozList);

        adapter.setListener(this);

        recyclerView.setAdapter(adapter);

        btnBringData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadData();
                btnBringData.setText("Reload the list");
                bringData = false;
            }
        });

    }

    private void loadData() {

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ozList.clear();
                for(DataSnapshot data : snapshot.getChildren()){

                    Organization organization = data.getValue(Organization.class);
                    ozAllList.add(organization);

                    Organization ozNameImage = new Organization(organization.getOzName(), organization.getOzImages());

                    //ozKey = data.getKey();
                    ozList.add(ozNameImage);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load Organization List");
            }
        });
    }

    @Override
    public void onListItemClick(int position) {

        Organization organization = ozAllList.get(position);

        String ozTitle = organization.getOzName();
        String ozDescription = organization.getOzDescription();
        String ozAddress = organization.getOzAddress();
        String ozCity = organization.getOzCity();
        String ozProvince = organization.getOzProvince();
        String ozPostalCode = organization.getOzPostalCode();
        String ozEmail = organization.getOzEmail();
        String ozPhoneNumber = organization.getOzPhoneNumber();
        int ozImage = organization.getOzImages();
        String ozDonationURL = organization.getOzDonationURL();
        String ozVolunteerURL = organization.getOzVolunteerURL();

        Intent intent = new Intent(this, DonationDetailsActivity.class);

        intent.putExtra("OZTITLE", ozTitle);
        intent.putExtra("OZDESCRIPTON", ozDescription);
        intent.putExtra("OZADDRESS", ozAddress);
        intent.putExtra("OZCITY", ozCity);
        intent.putExtra("OZPROVINCE", ozProvince);
        intent.putExtra("OZPOSTALCODE", ozPostalCode);
        intent.putExtra("OZEMAIL", ozEmail);
        intent.putExtra("OZPHONENUMBER", ozPhoneNumber);
        intent.putExtra("OZIMAGE", ozImage);
        intent.putExtra("OZDONATIONURL", ozDonationURL);
        intent.putExtra("OZVOLUNTEERURL", ozVolunteerURL);

        startActivity(intent);

    }
}