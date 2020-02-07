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
public class courseAdapter extends RecyclerView.Adapter<courseAdapter.coursesHolder> {

    private Context context;
    private List<Courses> courses;

    public courseAdapter(Context context,List<Courses> courses){
        this.context=context;
        this.courses=courses;
    }

    public class coursesHolder extends RecyclerView.ViewHolder{

        public TextView c_name;
        public TextView c_code;
        public TextView c_category;
        public TextView year;

        public coursesHolder(@NonNull View itemView) {
            super (itemView);

            c_name = itemView.findViewById(R.id.c_name);
            c_code = itemView.findViewById(R.id.c_code);
            c_category = itemView.findViewById(R.id.c_category);


        }
    }

    @NonNull
    @Override
    public coursesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate (R.layout.courses_adapter,parent,false);
        return new coursesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull coursesHolder holder, int position) {

        Courses course = courses.get(position);

        holder.c_name.setText(course.getCourseName());
        holder.c_code.setText(course.getCourseCode());
        holder.c_category.setText(course.getCourseCategory());

    }

    @Override
    public int getItemCount() {
        return courses.size ();
    }
}
