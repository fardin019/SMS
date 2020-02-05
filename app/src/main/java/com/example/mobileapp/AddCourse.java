package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class AddCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final TextInputEditText course_name, course_code, course_credits, course_yos;

        final Spinner course_cat_spin, course_sem_spin;

        Button send_btn;

        final DatabaseHelper databaseHelper;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        databaseHelper = new DatabaseHelper(this);

        course_name = findViewById(R.id.course_name);
        course_code = findViewById(R.id.course_code);
        course_credits = findViewById(R.id.course_credits);
        course_yos = findViewById(R.id.course_yos);


        course_cat_spin = findViewById(R.id.course_cat_spin);
        course_sem_spin = findViewById(R.id.course_sem_spin);

        send_btn = findViewById(R.id.send_btn);





        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c_name = Objects.requireNonNull(course_name.getText()).toString().trim();
                String c_code = Objects.requireNonNull(course_code.getText()).toString().trim();
                String c_creds = Objects.requireNonNull(course_credits.getText()).toString().trim();
                String c_yos = Objects.requireNonNull(course_yos.getText()).toString().trim();


                String cat_value = course_cat_spin.getSelectedItem().toString().trim();
                String sem_value = course_sem_spin.getSelectedItem().toString().trim();

                if (!(c_name.isEmpty() && c_code.isEmpty() && c_creds.isEmpty() && c_yos.isEmpty())){

                    boolean res = databaseHelper.addCourse(c_code,c_name,c_creds,c_yos,sem_value,cat_value);

                    if (res == true){

                        Toast.makeText(getApplicationContext(),"A new Course has added successfully.",Toast.LENGTH_LONG).show();

                        course_name.getText().clear();
                        course_code.getText().clear();
                        course_credits.getText().clear();
                        course_yos.getText().clear();

                    }
                    else Toast.makeText(getApplicationContext(),"Failed to add new Course.",Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(getApplicationContext(),"Please fill all the fields.",Toast.LENGTH_LONG).show();



            }
        });

    }
}
