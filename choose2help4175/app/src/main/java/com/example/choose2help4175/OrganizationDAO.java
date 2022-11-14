package com.example.choose2help4175;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OrganizationDAO {

    private DatabaseReference dbReference;

    public OrganizationDAO(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(Organization.class.getSimpleName());
    }

    public Task<Void> createOrganization(Organization organization){
        return dbReference.push().setValue(organization);
    }

    public Query get(){
        return dbReference;
    }

    public Task<Void> remove() {
        return dbReference.removeValue();
    }
}
