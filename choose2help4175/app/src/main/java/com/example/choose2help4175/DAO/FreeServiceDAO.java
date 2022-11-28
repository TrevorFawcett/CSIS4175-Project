package com.example.choose2help4175.DAO;

import com.example.choose2help4175.model.FreeService;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FreeServiceDAO {
    private DatabaseReference dbReference;

    public FreeServiceDAO(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(FreeService.class.getSimpleName());
    }

    public Task<Void> createFreeService(FreeService freeService){
        return dbReference.push().setValue(freeService);
    }

    public Query get(){
        return dbReference;
    }

    public Task<Void> remove() {
        return dbReference.removeValue();
    }
}
