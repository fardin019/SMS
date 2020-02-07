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
    List<Staffs> staffs;
    staffAdapter staffAdapter;

    FloatingActionButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_staffs);

        db = new DatabaseHelper (this);
        staffs=new ArrayList<>();

        staffs.addAll (db.viewAllStaffs ());
        RecyclerView recyclerView = this.findViewById (R.id.staff_cycle);
        recyclerView.setLayoutManager (new LinearLayoutManager(this));
        staffAdapter = new staffAdapter (this,staffs);
        recyclerView.setAdapter (staffAdapter);

        add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayStaffs.this,AddStaff.class);
                startActivity(intent);
            }
        });
    }
}
