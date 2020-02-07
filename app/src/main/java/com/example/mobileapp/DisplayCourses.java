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

public class DisplayCourses extends AppCompatActivity {

    DatabaseHelper db;
    List<Courses> courses;
    courseAdapter courseAdapter;

    FloatingActionButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_courses);

        db = new DatabaseHelper (this);
        courses = new ArrayList<>();

        courses.addAll (db.viewAllCourses ());
        RecyclerView recyclerView = this.findViewById (R.id.course_cycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter = new courseAdapter(this,courses);
        recyclerView.setAdapter(courseAdapter);

        add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayCourses.this,AddCourse.class);
                startActivity(intent);
            }
        });
    }
}
