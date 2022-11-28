package com.example.choose2help4175.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose2help4175.R;
import com.example.choose2help4175.model.FreeService;
import com.example.choose2help4175.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    List<Review> ReviewList = new ArrayList<>();

    public List<Review> getReviewList() {
        return ReviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        ReviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate external layout
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_review,parent,false);
        //create tune view holder object
        ReviewViewHolder viewHolder = new ReviewViewHolder(itemView);

        viewHolder.txtViewReviewAuthor = itemView.findViewById(R.id.txtViewReviewAuthor);
        viewHolder.txtViewReviewText = itemView.findViewById(R.id.txtViewReviewText);
        viewHolder.txtViewReviewDate = itemView.findViewById(R.id.txtFreeServiceDate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        holder.txtViewReviewAuthor.setText(ReviewList.get(position).getReviewAuthor());
        holder.txtViewReviewText.setText(ReviewList.get(position).getReviewText());
        holder.txtViewReviewDate.setText(ReviewList.get(position).getReviewDate());
    }

    @Override
    public int getItemCount() {
        return ReviewList.size();
    }


    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewReviewAuthor;
        TextView txtViewReviewDate;
        TextView txtViewReviewText;
        View reviewItemView;


        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }




}
