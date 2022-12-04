package com.example.choose2help4175.DAO;


import com.example.choose2help4175.model.Review;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ReviewDAO {
    private DatabaseReference dbReference;

    public ReviewDAO(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(Review.class.getSimpleName());
    }

    public Task<Void> createReview(Review review){
        return dbReference.push().setValue(review);
    }

    public Query get(){
        return dbReference;
    }

    public Task<Void> remove() {
        return dbReference.removeValue();
    }
}
