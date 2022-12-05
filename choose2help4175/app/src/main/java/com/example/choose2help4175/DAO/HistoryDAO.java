package com.example.choose2help4175.DAO;

import com.example.choose2help4175.model.Review;
import com.example.choose2help4175.model.UserAction;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HistoryDAO {
    private DatabaseReference dbReference;

    public HistoryDAO(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(UserAction.class.getSimpleName());
    }

    public Task<Void> createUserAction(UserAction userAction){
        return dbReference.push().setValue(userAction);
    }

    public Query get(){
        return dbReference;
    }

    public Task<Void> remove() {
        return dbReference.removeValue();
    }
}
