package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class DonationListsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtOrganizationTitle;
    Button btnBringData;
    OrganizationAdapter adapter;
    OrganizationDAO dao;

    ArrayList<Organization> ozList = new ArrayList<>();
    //String key = "";

    List<String> ozCode = new ArrayList<>(Arrays.asList("oz01", "oz02", "oz03", "oz04", "oz05"));
    List<String> ozNames = new ArrayList<>(Arrays.asList("BC Children’s Hospital","Big Brothers", "Downtown Eastside Women’s Centre", "Mom2mom", "Seva Canada"));
    List<String> ozDescription = new ArrayList<>(Arrays.asList("It is BC's only dedicated hospital for children. It supports the health, hopes and dreams of all children, especially those who are sick or seriously injured.",
            "An institution that works with the community to revitalize impactful mentoring relationships where children and youth can discover their power and potential.",
            "DEWC provides a safe, non-judgmental environment for all women, including cis, trans and 2S, who live or work on the Downtown East Side. It supports the environment with diet, counselling, advocacy and various programs.",
            "It supports an egalitarian society where mothers and children are not constrained by poverty. It provides the connection with resources and communities to assist mothers and children in poverty.",
            "It aims for a world where no one is unnecessarily blind or visually impaired. With a mission to prevent blindness, it helps restore sight in low- and middle-income countries."));
    List<String> ozPhoneNumber = new ArrayList<>(Arrays.asList("604-449-6333", "604-876-2447", "604-681-8480", "604-343-6514", "604-713-6622"));
    List<String> ozEmail = new ArrayList<>(Arrays.asList("info@bcchf.ca", "avega@bbgvf.com", "admin@dewc.ca", "info@m2mcharity.ca", "admin@seva.ca"));
    List<String> ozAddress = new ArrayList<>(Arrays.asList("938 West 28th Avenue", "102 – 1193 Kingsway Vancouver", "302 Columbia St", "500 - 610 Main St", "100 - 2000 West 12th Avenue"));
    List<String> ozPostalCode = new ArrayList<>(Arrays.asList("V5Z 4H4", "V5V 3C9", "V6A 2T2", "V6A 2V3", "V6J 2G2"));
    String ozCity = "Vancouver";
    String ozProvince = "BC";
    String ozCountry = "Canada";

    List<Integer> ozImages = new ArrayList<>(Arrays.asList(R.drawable.childhospital, R.drawable.bigbrothers, R.drawable.dewc, R.drawable.momtomom,R.drawable.childblindness));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_service_lists);

        txtOrganizationTitle = findViewById(R.id.txtTitleFreeService);
        recyclerView = findViewById(R.id.freeServiceRecyclerView);
        btnBringData = findViewById(R.id.btnRegisterService);

        txtOrganizationTitle.setText("Organization List");
        btnBringData.setText("Bring the List");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new OrganizationAdapter(this, ozList);

        recyclerView.setAdapter(adapter);

        //initialize the DB
        dao = new OrganizationDAO();

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
                Toast.makeText(DonationListsActivity.this, "Success Remove data", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DonationListsActivity.this, "Fail Remove data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createOrganization(){

        for(int i = 0; i < ozNames.size(); i++){
//           Organization organization = new Organization(ozNames.get(i), ozImages.get(i));
            Organization organization = new Organization(ozCode.get(i), ozNames.get(i), ozDescription.get(i), ozPhoneNumber.get(i), ozEmail.get(i), ozAddress.get(i),
                    ozCity, ozProvince, ozCountry, ozPostalCode.get(i), ozImages.get(i));

           dao.createOrganization(organization).addOnSuccessListener(new OnSuccessListener<Void>() {
               @Override
               public void onSuccess(Void unused) {
                   Toast.makeText(DonationListsActivity.this, "Success add data", Toast.LENGTH_SHORT).show();
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(DonationListsActivity.this, "Fail to add data", Toast.LENGTH_SHORT).show();
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

                    Organization ozNameImage = new Organization(organization.getOzName(), organization.getOzImages());

                    //key = data.getKey();
                    ozList.add(ozNameImage);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
