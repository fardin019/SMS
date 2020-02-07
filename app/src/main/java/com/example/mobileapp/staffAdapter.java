package com.example.mobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.Calendar;
import java.util.List;

public class staffAdapter extends RecyclerView.Adapter<staffAdapter.staffsHolder> {

    private Context context;
    private List<Staffs> staffs;

    public staffAdapter(Context context,List<Staffs> staffs){
        this.context = context;
        this.staffs = staffs;
    }

    public class staffsHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView username;
        public TextView course;

        public staffsHolder(@NonNull View itemView) {
            super (itemView);

            name = itemView.findViewById(R.id.s_name);
            username = itemView.findViewById(R.id.s_username);
            course = itemView.findViewById(R.id.s_course);


        }
    }

    @NonNull
    @Override
    public staffsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from (context);
        View view=inflater.inflate (R.layout.staffs_adapter,parent,false);
        return new staffsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull staffsHolder holder, int position) {

        Staffs staff = staffs.get(position);

        String space = " ";
        String full_name = staff.getFirst_name() + space + staff.getLast_name();


        holder.name.setText(full_name);
        holder.username.setText(staff.getUsername());
        holder.course.setText(staff.getCourse());


    }

    @Override
    public int getItemCount() {
        return staffs.size ();
    }


}
