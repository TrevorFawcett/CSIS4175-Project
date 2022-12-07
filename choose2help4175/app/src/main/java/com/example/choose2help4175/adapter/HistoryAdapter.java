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
import com.example.choose2help4175.model.UserAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.historyViewHolder>{

    List<UserAction> userActionList = new ArrayList<>();
    private Context context;

    public HistoryAdapter(Context context, List<UserAction> userActionList) {
        this.userActionList = userActionList;
        this.context = context;
    }

    @NonNull
    @Override
    public historyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_history_element,parent,false);
        return new HistoryAdapter.historyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyViewHolder holder, int position) {
        holder.txtViewAction.setText(userActionList.get(position).getUserAction());
        holder.txtViewActionDate.setText(userActionList.get(position).getActionDate());
    }

    @Override
    public int getItemCount() {
        return userActionList.size();
    }

    public class historyViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewAction;
        TextView txtViewActionDate;

        public historyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewAction = itemView.findViewById(R.id.txtViewAction);
            txtViewActionDate = itemView.findViewById(R.id.txtViewActionDate);
        }
    }
}
