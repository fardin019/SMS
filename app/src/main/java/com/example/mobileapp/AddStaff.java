package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class AddStaff extends AppCompatActivity {
    private static final String TAG = "Registation";
    private TextInputEditText bDisplayDate;
    private DatePickerDialog.OnDateSetListener bDateSetListener;

    TextInputEditText staff_fname, staff_mname, staff_lname, staff_email, staff_mobile, staff_bdate,
            staff_reg_number, staff_username, staff_pass;

    Spinner staff_gen_spinner, staff_reg_spinner, staff_dist_spinner, staff_ward_spinner, staff_course_spinner;

    Button send_btn;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        bDisplayDate = findViewById(R.id.staff_bdate);
        bDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddStaff.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        bDateSetListener,
                        year, month, day);
                dialog.show();
            }
        });
        bDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDataset: mm/dd/yyy: " + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                bDisplayDate.setText(date);
            }
        };

        final TextInputLayout staff_pass_til = findViewById(R.id.staff_pass_til);
        final TextInputEditText staff_pass_tiet = findViewById(R.id.staff_pass);
        staff_pass_tiet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 8){
                    staff_pass_til.setError("Minimum Character length: 8");
                }
                else{
                    staff_pass_til.setError(null);
                }
            }
        });




        Spinner genSpinner = findViewById(R.id.staff_gen_spinner);
        ArrayAdapter<CharSequence> genAdapter = ArrayAdapter.createFromResource(this,R.array.genders, android.R.layout.simple_spinner_item);
        genAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genSpinner.setAdapter(genAdapter);



        databaseHelper = new DatabaseHelper(this);

        staff_fname = findViewById(R.id.staff_fname);
        staff_mname = findViewById(R.id.staff_mname);
        staff_lname = findViewById(R.id.staff_lname);
        staff_email = findViewById(R.id.staff_email);
        staff_mobile = findViewById(R.id.staff_mobile);
        staff_bdate = findViewById(R.id.staff_bdate);
        staff_reg_number = findViewById(R.id.staff_reg_number);
        staff_username = findViewById(R.id.staff_username);
        staff_pass = findViewById(R.id.staff_pass);

        staff_gen_spinner = findViewById(R.id.staff_gen_spinner);
        staff_reg_spinner = findViewById(R.id.staff_reg_spinner);
        staff_dist_spinner = findViewById(R.id.staff_dist_spinner);
        staff_ward_spinner = findViewById(R.id.staff_ward_spinner);
        staff_course_spinner = findViewById(R.id.staff_course_spinner);


        staff_reg_number.setText(databaseHelper.staffRegNo());
        staff_username.setText(databaseHelper.staffRegNo());
        String Password = "@coictapp";
        staff_pass.setText(Password);

        ArrayList<String> listOfCourses = databaseHelper.getCourses();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,listOfCourses);
        staff_course_spinner.setAdapter(adapter);


        send_btn = findViewById(R.id.submit_staff_form);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String fname = Objects.requireNonNull(staff_fname.getText()).toString();
                String mname = Objects.requireNonNull(staff_mname.getText()).toString();
                String lname = Objects.requireNonNull(staff_lname.getText()).toString();
                String email = Objects.requireNonNull(staff_email.getText()).toString();
                String mobile = Objects.requireNonNull(staff_mobile.getText()).toString();
                String bdate = Objects.requireNonNull(staff_bdate.getText()).toString();
                String reg_no = Objects.requireNonNull(staff_reg_number.getText()).toString();
                String pass = Objects.requireNonNull(staff_pass.getText()).toString();
                String username = Objects.requireNonNull(staff_username.getText()).toString();

                String role = "Staff";

                String gen_value = staff_gen_spinner.getSelectedItem().toString();
                String reg_value = staff_reg_spinner.getSelectedItem().toString();
                String dist_value = staff_dist_spinner.getSelectedItem().toString();
                String ward_value = staff_ward_spinner.getSelectedItem().toString();


                if (!(staff_fname.getText().toString().isEmpty() && staff_mname.getText().toString().isEmpty()
                && staff_lname.getText().toString().isEmpty() && staff_email.getText().toString().isEmpty()
                && staff_mobile.getText().toString().isEmpty() && staff_bdate.getText().toString().isEmpty()
                && staff_reg_number.getText().toString().isEmpty() && staff_pass.getText().toString().isEmpty()
                && staff_username.getText().toString().isEmpty())) {

                    boolean res = databaseHelper.addUser(reg_no,fname,mname,lname,gen_value,bdate,email,mobile,reg_value
                            ,dist_value,ward_value,username,role,pass);

                    if (res == true){
                        Toast.makeText(getApplicationContext(),"A new Staff has added successfully.",Toast.LENGTH_LONG).show();

                        Objects.requireNonNull(staff_fname.getText()).clear();
                        Objects.requireNonNull(staff_mname.getText()).clear();
                        Objects.requireNonNull(staff_lname.getText()).clear();
                        Objects.requireNonNull(staff_email.getText()).clear();
                        Objects.requireNonNull(staff_mobile.getText()).clear();
                        Objects.requireNonNull(staff_bdate.getText()).clear();
                        Objects.requireNonNull(staff_reg_number.getText()).clear();
                        Objects.requireNonNull(staff_username.getText()).clear();

                        staff_reg_number.setText(databaseHelper.staffRegNo());
                        staff_username.setText(databaseHelper.staffRegNo());
                    }
                    else Toast.makeText(getApplicationContext(),"Failed to add new Staff.",Toast.LENGTH_LONG).show();

                }
                else Toast.makeText(getApplicationContext(),"Please fill all the fields.",Toast.LENGTH_LONG).show();




            }
        });

    }
}
