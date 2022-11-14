package com.example.choose2help4175;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OrganizationDAO {

    private DatabaseReference databaseReference;

    public OrganizationDAO(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Organization.class.getSimpleName());
    }

    public Task<Void> createOrganization(Organization organization){
        return databaseReference.push().setValue(organization);
    }

    public Query get(){
        return databaseReference;
    }

    public Task<Void> remove() {
        return databaseReference.removeValue();
    }
}
