package com.example.choose2help4175;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class RegisterFreeServiceActivity extends AppCompatActivity {

    private static final String TAG = RegisterFreeServiceActivity.class.getSimpleName();
    FreeServiceDAO dao;

    EditText SName;
    RadioButton rdbSTypeFood;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioButton rdbSTypeAmenities;
    RadioButton rdbSTypeCultural;
    RadioButton rdbSTypeEducational;
    EditText SShopName;
    EditText SDate;
    EditText STime;
    EditText SAddress;
    EditText SDescription;
    Button btnRESave;
    String fsType;
    int fsImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_free_service_2);

        SName = findViewById(R.id.txtEventNameRE);
        SShopName = findViewById(R.id.txtEventShopNameRE);
        SDate = findViewById(R.id.txtEventDateRE);
        STime = findViewById(R.id.txtEventNameRE);
        SAddress = findViewById(R.id.txtEventLocationRE);
        SDescription = findViewById(R.id.txtEventDescriptionRE);

        radioGroup1 = findViewById(R.id.rdbServiceType1);
        radioGroup2 = findViewById(R.id.rdbServiceType2);
        rdbSTypeFood = findViewById(R.id.rdbSTypeFood);
        rdbSTypeAmenities = findViewById(R.id.rdbTypeAmenities);
        rdbSTypeCultural = findViewById(R.id.rdbSTypeCultural);
        rdbSTypeEducational = findViewById(R.id.rdbSTypeEducational);

        btnRESave = findViewById(R.id.btnServiceRegister);
        dao = new FreeServiceDAO();

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(rdbSTypeFood.isChecked()){
                    fsType = "Food";
                    fsImg = R.drawable.food;
                }

                if(rdbSTypeAmenities.isChecked()){
                    fsType = "Amenities";
                    fsImg = R.drawable.amenities;

                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(rdbSTypeCultural.isChecked()){
                    fsType = "Cultural";
                    fsImg = R.drawable.cultural;
                }

                if(rdbSTypeEducational.isChecked()){
                    fsType = "Educational";
                    fsImg = R.drawable.educational;
                }
            }
        });

//        this.fServiceName = fServiceName;
//        this.imgFServiceType = imgFServiceType;
//        this.fServiceAddress = fServiceAddress;
//        this.fServiceLocation = fServiceLocation;
//        this.fServiceDate = fServiceDate;
//        this.fServiceTime = fServiceTime;
//        this.fServiceDescription = fServiceDescription;
        btnRESave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                createFreeService();

                String fsName = SName.getText().toString();
                String fsShopName = SShopName.getText().toString();
                String fsDate = SDate.getText().toString();
                String fsTime = STime.getText().toString();
                String fsAddress = SAddress.getText().toString();
                String fsDescription = SDescription.getText().toString();

                Intent intent = new Intent(RegisterFreeServiceActivity.this, FreeServiceListsActivity.class);

                intent.putExtra("FSName", fsName);
                intent.putExtra("FSTYPEIMAGE", fsImg);
                intent.putExtra("FSSHOPNAME", fsShopName);
                intent.putExtra("FSDATE", fsDate);
                intent.putExtra("FSTIME", fsTime);
                intent.putExtra("FSADDRESS", fsAddress);
                intent.putExtra("FSDESCRIPTION", fsDescription);

                startActivity(intent);
            }
        });
    }

    private void createFreeService(){

            String fsName = SName.getText().toString();
            String fsShopName = SShopName.getText().toString();
            String fsDate = SDate.getText().toString();
            String fsTime = STime.getText().toString();
            String fsAddress = SAddress.getText().toString();
            String fsDescription = SDescription.getText().toString();

            FreeService freeService = new FreeService(fsName, fsImg, fsAddress, fsShopName, fsDate, fsTime, fsDescription);

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
}


