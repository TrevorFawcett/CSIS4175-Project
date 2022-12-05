package com.example.choose2help4175;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.choose2help4175.databinding.ActivityFreeServiceReservationBinding;
import com.example.choose2help4175.ui.navigation.BaseActivity;

public class FreeServiceReservationActivity extends BaseActivity {
    ActivityFreeServiceReservationBinding activityFreeServiceReservationBinding;

    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}