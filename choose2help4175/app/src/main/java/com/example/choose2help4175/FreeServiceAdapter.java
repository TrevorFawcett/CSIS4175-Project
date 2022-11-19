package com.example.choose2help4175;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FreeServiceAdapter extends RecyclerView.Adapter<FreeServiceAdapter.freeServiceViewHolder> {

    private static final String TAG = FreeServiceAdapter.class.getSimpleName();

    private Context context;
    public ListItemListener mListener;
    ArrayList<FreeService> fsArrayList;

    public FreeServiceAdapter(Context context, ArrayList<FreeService> fsArrayList) {
        this.context = context;
        this.fsArrayList = fsArrayList;
    }

    public void setListener(ListItemListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public freeServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_freeservice_item,parent,false);

        return new FreeServiceAdapter.freeServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull freeServiceViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.imgServiceType.setImageResource(fsArrayList.get(position).getImgFServiceType());
        holder.txtServiceTitle.setText(fsArrayList.get(position).getfServiceName());
        holder.txtServiceDate.setText(fsArrayList.get(position).getfServiceDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fsArrayList.size();
    }

    class freeServiceViewHolder extends RecyclerView.ViewHolder{

        ImageView imgServiceType;
        TextView txtServiceTitle;
        TextView txtServiceDate;

        public freeServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            imgServiceType = itemView.findViewById(R.id.imgFreeServiceItem);
            txtServiceTitle = itemView.findViewById(R.id.txtFreeServiceTitleItem);
            txtServiceDate = itemView.findViewById(R.id.txtFreeServiceLocationItem);
        }
    }

    public interface ListItemListener {
        void onListItemClick(int position);
    }
}
