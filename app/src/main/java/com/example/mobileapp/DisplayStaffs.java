package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DisplayStaffs extends AppCompatActivity {

    DatabaseHelper db;
    List<Student> students;
    studentAdapter studentAdapter;

    FloatingActionButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_staffs);

        db = new DatabaseHelper (this);
        students=new ArrayList<>();

        students.addAll (db.viewAllStudents ());
        RecyclerView recyclerView = this.findViewById (R.id.student_cycle);
        recyclerView.setLayoutManager (new LinearLayoutManager(this));
        studentAdapter = new studentAdapter (this,students);
        recyclerView.setAdapter (studentAdapter);

        add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayStaffs.this,AddStudents.class);
                startActivity(intent);
            }
        });
    }
}
