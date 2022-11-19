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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonationListsActivity extends AppCompatActivity implements OrganizationAdapter.ListItemListener{

    private static final String TAG = DonationListsActivity.class.getSimpleName();

    RecyclerView recyclerView;
    TextView txtOrganizationTitle;
    Button btnBringData;
    OrganizationAdapter adapter;
    OrganizationDAO dao;

    ArrayList<Organization> ozList = new ArrayList<>();
    ArrayList<Organization> ozAllList = new ArrayList<>();
    //String ozKey = "";

    List<String> ozCode = new ArrayList<>(Arrays.asList("oz01", "oz02", "oz03", "oz04", "oz05"));
    List<String> ozNames = new ArrayList<>(Arrays.asList("BC Children’s Hospital","Big Brothers", "Downtown Eastside Women’s Centre", "Mom2mom", "Seva Canada"));
    List<String> ozDescription = new ArrayList<>(Arrays.asList("It is BC's only dedicated hospital for children. It supports the health, hopes and dreams of all children, especially those who are sick or seriously injured.",
            "An institution that works with the community to revitalize impactful mentoring relationships where children and youth can discover their power and potential.",
            "DEWC provides a safe, non-judgmental environment for all women who live or work on the Downtown East Side. It supports the environment with diet, counselling, advocacy and various programs.",
            "It supports an egalitarian society where mothers and children are not constrained by poverty. It provides the connection with resources and communities to assist mothers and children in poverty.",
            "It aims for a world where no one is unnecessarily blind or visually impaired. With a mission to prevent blindness, it helps restore sight in low- and middle-income countries."));
    List<String> ozPhoneNumber = new ArrayList<>(Arrays.asList("604-449-6333", "604-876-2447", "604-681-8480", "604-343-6514", "604-713-6622"));
    List<String> ozEmail = new ArrayList<>(Arrays.asList("info@bcchf.ca", "avega@bbgvf.com", "admin@dewc.ca", "info@m2mcharity.ca", "admin@seva.ca"));
    List<String> ozAddress = new ArrayList<>(Arrays.asList("938 West 28th Avenue", "102 – 1193 Kingsway", "302 Columbia St", "500 - 610 Main St", "100 - 2000 West 12th Avenue"));
    List<String> ozPostalCode = new ArrayList<>(Arrays.asList("V5Z 4H4", "V5V 3C9", "V6A 2T2", "V6A 2V3", "V6J 2G2"));
    String ozCity = "Vancouver";
    String ozProvince = "BC";
    String ozCountry = "Canada";
    List<Integer> ozImages = new ArrayList<>(Arrays.asList(R.drawable.childhospital, R.drawable.bigbrothers, R.drawable.dewc, R.drawable.momtomom,R.drawable.childblindness));
    List<String> ozDonationURL = new ArrayList<>(Arrays.asList("https://donate.bcchf.ca/site/Donation2?1760.donation=form1&df_id=1760&mfc_pref=T", "https://www.bigbrothersvancouver.com/donate-now/",
            "https://dewc.ca/donate-online", "https://www.m2mcharity.ca/donate", "https://donate.seva.ca/?_ga=2.136185009.1602636418.1667631363-291443264.1667631363"));
    List<String> ozVolunteerURL = new ArrayList<>(Arrays.asList("https://www.bcchf.ca/volunteer/", "https://www.bigbrothersvancouver.com/volunteer/", "https://dewc.ca/volunteer",
            "https://www.m2mcharity.ca/volunteer", "https://www.seva.ca/volunteer-seva"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_service_lists);

        txtOrganizationTitle = findViewById(R.id.txtTitleFreeService);
        recyclerView = findViewById(R.id.freeServiceRecyclerView);
        btnBringData = findViewById(R.id.btnRegisterService);

        txtOrganizationTitle.setText("Organization List");
        btnBringData.setText("Bring the List");

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

                removeExistingData();
                createOrganization();
                loadData();
            }
        });

    }

    private void removeExistingData(){
        dao.remove().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Success Remove Organization Table");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Failed to remove Organization");
            }
        });
    }

    private void createOrganization(){

        for(int i = 0; i < ozNames.size(); i++){
//           Organization organization = new Organization(ozNames.get(i), ozImages.get(i));
            Organization organization = new Organization(ozCode.get(i), ozNames.get(i), ozDescription.get(i), ozPhoneNumber.get(i), ozEmail.get(i), ozAddress.get(i),
                    ozCity, ozProvince, ozCountry, ozPostalCode.get(i), ozImages.get(i), ozDonationURL.get(i), ozVolunteerURL.get(i));

            dao.createOrganization(organization).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Success add Organization");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Failed to create Organization");
                }
            });
        }
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
        int ozImage = ozImages.get(position);
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