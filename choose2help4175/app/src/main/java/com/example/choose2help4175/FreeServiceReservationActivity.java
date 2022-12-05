package com.example.choose2help4175;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.choose2help4175.databinding.ActivityFreeServiceReservationBinding;
import com.example.choose2help4175.model.ReservationServiceHelper;
import com.example.choose2help4175.ui.navigation.BaseActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FreeServiceReservationActivity extends BaseActivity {
    ActivityFreeServiceReservationBinding activityFreeServiceReservationBinding;

    EditText resFName, resLName, resPhone, resEmail;
    Button resBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_service_reservation);

        resBtn = findViewById(R.id.btnReservationREV);
        resFName = findViewById(R.id.txtFirstNameREV);
        resLName = findViewById(R.id.txtLastNameREV);
        resPhone = findViewById(R.id.txtPhoneNumberREV);
        resEmail = findViewById(R.id.txtEmailREV);

        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Reservation");

                String firstName = resFName.getText().toString();
                String lastName = resLName.getText().toString();;
                String phone = resPhone.getText().toString();;
                String email = resEmail.getText().toString();;

                resFName.getText().clear();
                resLName.getText().clear();
                resPhone.getText().clear();
                resEmail.getText().clear();

                showAlertDialog("Thank you for creating an Appointment with us, " + firstName +"!");

                ReservationServiceHelper serviceHelper = new ReservationServiceHelper(firstName, lastName, phone, email);

                reference.setValue(serviceHelper);

            }
        });


        //setContentView(R.layout.activity_free_service_reservation);
        activityFreeServiceReservationBinding = ActivityFreeServiceReservationBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_free_service_reservation, frameLayout);

        //data from FreeServiceList activity, When user click one item in the Free Service list
        SharedPreferences sp = getSharedPreferences("FreeServiceContents",0);

//        String fsId = sp.getString("FSID", "");
//        String fsName = sp.getString("FSNAME", "");
        int imgFsType = sp.getInt("FSIMGTYPE", 0);
        String fsAddress = sp.getString("FSADDRESS", "");
        String fsLocation = sp.getString("FSLOCATION", "");
        String fsDate = sp.getString("FSDATE", "");
        String fsTime = sp.getString("FSTIME", "");
        String fsDescription = sp.getString("FSDESCRIPTION", "");

        displayFragment();

    }
    public void displayFragment(){
        FreeServiceFragment freeServiceFragment = FreeServiceFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fregment_container_freeservice,freeServiceFragment).addToBackStack(null).commit();

        isFragmentDisplayed = true;

    }

    private void showAlertDialog(String message){
        AlertDialog dialog = new AlertDialog.Builder(FreeServiceReservationActivity.this)
                .setTitle("Appointment Created")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}