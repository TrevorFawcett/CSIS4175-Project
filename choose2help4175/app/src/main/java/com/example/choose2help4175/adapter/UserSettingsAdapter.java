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

import com.example.choose2help4175.model.UserData;

import java.util.ArrayList;

public class UserSettingsAdapter extends RecyclerView.Adapter<UserSettingsAdapter.userSettingViewHolder>  {

    private static final String TAG = UserSettingsAdapter.class.getSimpleName();

    private Context context;
    public UserSettingsAdapter.ListItemListener mListener;
    //ArrayList<String> usersData;
    ArrayList<String> userArrayList;

    public UserSettingsAdapter(Context context, ArrayList<String> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;

    }

    public void setListener(UserSettingsAdapter.ListItemListener listener) {
        mListener = listener;
    }


    @NonNull
    @Override
    public UserSettingsAdapter.userSettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_usersettings_item,parent,false);

        return new UserSettingsAdapter.userSettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserSettingsAdapter.userSettingViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String data = userArrayList.get(position);

        holder.txtUser.setText(data);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    class userSettingViewHolder extends RecyclerView.ViewHolder{


        TextView txtUser;


        public userSettingViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUser = itemView.findViewById(R.id.txtUserSettingItem);
        }
    }

    public interface ListItemListener {
        void onListItemClick(int position);
    }




}
