package com.example.choose2help4175.DAO;

import com.example.choose2help4175.model.UserData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class UserDataDAO {
    private DatabaseReference dbReference;

    public UserDataDAO(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(UserData.class.getSimpleName());

    }



    public Task<Void> createUserData(UserData userData, String uid){
        //return dbReference.push().setValue(userData);
        return dbReference.child(uid).setValue(userData);
    }

    public Query get(){
        return dbReference;
    }

    public Query getUser(String uid){
        return dbReference.child(uid);
    }

    public Task<Void> remove() {
        return dbReference.removeValue();
    }
}