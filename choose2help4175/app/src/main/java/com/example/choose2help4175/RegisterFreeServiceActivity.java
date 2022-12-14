package com.example.choose2help4175;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.choose2help4175.DAO.FreeServiceDAO;
import com.example.choose2help4175.databinding.ActivityRegisterFreeServiceBinding;
import com.example.choose2help4175.model.FreeService;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class RegisterFreeServiceActivity extends BaseActivity {

    ActivityRegisterFreeServiceBinding activityRegisterFreeServiceBinding;

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

       // setContentView(R.layout.activity_register_free_service);
        activityRegisterFreeServiceBinding = ActivityRegisterFreeServiceBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_register_free_service, frameLayout);

        SName = rootView.findViewById(R.id.txtEventNameRE);
        SShopName = rootView.findViewById(R.id.txtEventShopNameRE);
        SDate = rootView.findViewById(R.id.txtEventDateRE);
        STime = rootView.findViewById(R.id.txtEventTimeRE);
        SAddress = rootView.findViewById(R.id.txtEventLocationRE);
        SDescription = rootView.findViewById(R.id.txtEventDescriptionRE);

        radioGroup1 = rootView.findViewById(R.id.rdbServiceType1);
        radioGroup2 = rootView.findViewById(R.id.rdbServiceType2);
        rdbSTypeFood = rootView.findViewById(R.id.rdbSTypeFood);
        rdbSTypeAmenities = rootView.findViewById(R.id.rdbTypeAmenities);
        rdbSTypeCultural = rootView.findViewById(R.id.rdbSTypeCultural);
        rdbSTypeEducational = rootView.findViewById(R.id.rdbSTypeEducational);

        btnRESave = rootView.findViewById(R.id.btnServiceRegister);
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

        btnRESave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createFreeService();

                Intent intent = new Intent(RegisterFreeServiceActivity.this, FreeServiceListsActivity.class);

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

            FreeService freeService = new FreeService(fsName, fsImg, fsShopName, fsDate, fsTime, fsAddress, fsDescription);

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


