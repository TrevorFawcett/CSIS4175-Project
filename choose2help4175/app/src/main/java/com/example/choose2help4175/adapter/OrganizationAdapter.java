package com.example.choose2help4175.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose2help4175.R;
import com.example.choose2help4175.model.Organization;

import java.util.ArrayList;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.organizationViewHolder> {

    private static final String TAG = OrganizationAdapter.class.getSimpleName();

    private Context context;
    public ListItemListener mListener;
    ArrayList<Organization> ozArrayList;

    public OrganizationAdapter(Context context,ArrayList<Organization> ozArrayList) {
        this.context = context;
        this.ozArrayList = ozArrayList;
    }

    public void setListener(ListItemListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public organizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_donation_item,parent,false);

        return new  organizationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  organizationViewHolder holder, @SuppressLint("RecyclerView") int position) {

//        Organization organization = ozArrayList.get(holder.getBindingAdapterPosition());

        holder.imageView.setImageResource(ozArrayList.get(position).getOzImages());
        holder.textView.setText(ozArrayList.get(position).getOzName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListItemClick(position);
            }
        });
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

    public interface ListItemListener {
        void onListItemClick(int position);
    }
}
