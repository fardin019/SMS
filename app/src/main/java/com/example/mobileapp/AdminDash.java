package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminDash extends AppCompatActivity implements View.OnClickListener {
    private CardView admin_students, admin_staffs, admin_courses, admin_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);

        admin_students = findViewById(R.id.admin_students);
        admin_staffs = findViewById(R.id.admin_staffs);
        admin_courses = findViewById(R.id.admin_courses);
        admin_logout = findViewById(R.id.admin_logout);

        admin_students.setOnClickListener(this);
        admin_staffs.setOnClickListener(this);
        admin_courses.setOnClickListener(this);
        admin_logout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.admin_students : intent = new Intent(this,AddStudents.class); startActivity(intent); break;
            case R.id.admin_staffs : intent = new Intent(this,AddStaff.class); startActivity(intent); break;
            case R.id.admin_courses : intent = new Intent(this,AddCourse.class); startActivity(intent); break;
            case R.id.admin_logout : intent = new Intent(this,MainActivity.class); startActivity(intent); break;
            default: break;
        }

    }
}
