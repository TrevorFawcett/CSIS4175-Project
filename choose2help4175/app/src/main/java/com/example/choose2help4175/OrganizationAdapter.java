package com.example.choose2help4175;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.organizationViewHolder> {

    private Context context;

    ArrayList<Organization> ozArrayList = new ArrayList<>();

    public OrganizationAdapter(Context context,ArrayList<Organization> ozArrayList) {
        this.context = context;
        this.ozArrayList = ozArrayList;
    }

    @NonNull
    @Override
    public organizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_donation_item,parent,false);

        return new  organizationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  organizationViewHolder holder, int position) {

//        Organization organization = ozArrayList.get(holder.getBindingAdapterPosition());

        holder.imageView.setImageResource(ozArrayList.get(position).getOzImages());
        holder.textView.setText(ozArrayList.get(position).getOzName());
    }

    @Override
    public int getItemCount() {
        return ozArrayList.size();
    }

    class organizationViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public  organizationViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgDonationItem);
            textView = itemView.findViewById(R.id.txtDonationNameItem);

        }
    }
}
