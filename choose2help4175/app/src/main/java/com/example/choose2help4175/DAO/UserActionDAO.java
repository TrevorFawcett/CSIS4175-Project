package com.example.choose2help4175.DAO;

import com.example.choose2help4175.model.UserAction;
import com.example.choose2help4175.model.UserData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class UserActionDAO {
    private DatabaseReference dbReference;

    public UserActionDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(UserAction.class.getSimpleName());
    }

    public Task<Void> createUserAction(UserAction userAction){
        //return dbReference.push().setValue(userData);
        return dbReference.push().setValue(userAction);
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
