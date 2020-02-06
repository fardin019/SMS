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

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.studentsHolder> {

    private Context context;
    private List<Student> students;

    public studentAdapter(Context context,List<Student> students){
        this.context=context;
        this.students=students;
    }

    public class studentsHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView username;
        public TextView programme;
        public TextView year;

        public studentsHolder(@NonNull View itemView) {
            super (itemView);

            name = itemView.findViewById(R.id.s_name);
            username = itemView.findViewById(R.id.s_username);
            programme = itemView.findViewById(R.id.s_programme);
            year = itemView.findViewById(R.id.s_yos);

        }
    }

    @NonNull
    @Override
    public studentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from (context);
        View view=inflater.inflate (R.layout.students_adapter,parent,false);
        return new studentsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull studentsHolder holder, int position) {

        Student student=students.get(position);

        String space = " ";
        String full_name = student.getFirst_name() + space + student.getMiddle_name() + space + student.getLast_name();
        String programme = "BSc in Computer Science";
        String year = "2020";

        holder.name.setText(full_name);
        holder.username.setText(student.getUsername());
        holder.programme.setText(programme);
        holder.year.setText(year);

    }

    @Override
    public int getItemCount() {
        return students.size ();
    }


}
