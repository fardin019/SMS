package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import java.util.Calendar;
import java.util.Objects;

public class AddStudents extends AppCompatActivity {
    private static final String TAG = "Registation";
    private TextInputEditText bDisplayDate;
    private DatePickerDialog.OnDateSetListener bDateSetListener;

    TextInputEditText student_fname, student_mname, student_lname, student_email, student_mobile, student_bdate,
            student_reg_number, student_username, student_pass;

    Spinner student_gen_spinner, student_reg_spinner, student_dist_spinner, student_ward_spinner;

    Button send_btn;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);
        bDisplayDate = findViewById(R.id.student_bdate);
        bDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddStudents.this,
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

        final TextInputLayout student_pass_til = findViewById(R.id.student_pass_til);
        final TextInputEditText student_pass_tiet = findViewById(R.id.student_pass);
        student_pass_tiet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 8){
                    student_pass_til.setError("Minimum Character length: 8");
                }
                else{
                    student_pass_til.setError(null);
                }
            }
        });




        Spinner genSpinner = findViewById(R.id.student_gen_spinner);
        ArrayAdapter<CharSequence> genAdapter = ArrayAdapter.createFromResource(this,R.array.genders, android.R.layout.simple_spinner_item);
        genAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genSpinner.setAdapter(genAdapter);



        databaseHelper = new DatabaseHelper(this);

        student_fname = findViewById(R.id.student_fname);
        student_mname = findViewById(R.id.student_mname);
        student_lname = findViewById(R.id.student_lname);
        student_email = findViewById(R.id.student_email);
        student_mobile = findViewById(R.id.student_mobile);
        student_bdate = findViewById(R.id.student_bdate);
        student_reg_number = findViewById(R.id.student_reg_number);
        student_username = findViewById(R.id.student_username);
        student_pass = findViewById(R.id.student_pass);

        student_gen_spinner = findViewById(R.id.student_gen_spinner);
        student_reg_spinner = findViewById(R.id.student_reg_spinner);
        student_dist_spinner = findViewById(R.id.student_dist_spinner);
        student_ward_spinner = findViewById(R.id.student_ward_spinner);






        send_btn = findViewById(R.id.submit_student_form);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String fname = Objects.requireNonNull(student_fname.getText()).toString();
                String mname = Objects.requireNonNull(student_mname.getText()).toString();
                String lname = Objects.requireNonNull(student_lname.getText()).toString();
                String email = Objects.requireNonNull(student_email.getText()).toString();
                String mobile = Objects.requireNonNull(student_mobile.getText()).toString();
                String bdate = Objects.requireNonNull(student_bdate.getText()).toString();
                String reg_no = Objects.requireNonNull(student_reg_number.getText()).toString();
                String pass = Objects.requireNonNull(student_pass.getText()).toString();
                String username = Objects.requireNonNull(student_username.getText()).toString();

                String role = "Student";

                String gen_value = student_gen_spinner.getSelectedItem().toString();
                String reg_value = student_reg_spinner.getSelectedItem().toString();
                String dist_value = student_dist_spinner.getSelectedItem().toString();
                String ward_value = student_ward_spinner.getSelectedItem().toString();


                if (!(student_fname.getText().toString().isEmpty() && student_mname.getText().toString().isEmpty()
                        && student_lname.getText().toString().isEmpty() && student_email.getText().toString().isEmpty()
                        && student_mobile.getText().toString().isEmpty() && student_bdate.getText().toString().isEmpty()
                        && student_reg_number.getText().toString().isEmpty() && student_pass.getText().toString().isEmpty()
                        && student_username.getText().toString().isEmpty())) {

                    boolean res = databaseHelper.addUser(reg_no,fname,mname,lname,gen_value,bdate,email,mobile,reg_value
                            ,dist_value,ward_value,username,role,pass);

                    if (res == true){
                        Toast.makeText(getApplicationContext(),"A new student has added successfully.",Toast.LENGTH_LONG).show();

                        Objects.requireNonNull(student_fname.getText()).clear();
                        Objects.requireNonNull(student_mname.getText()).clear();
                        Objects.requireNonNull(student_lname.getText()).clear();
                        Objects.requireNonNull(student_email.getText()).clear();
                        Objects.requireNonNull(student_mobile.getText()).clear();
                        Objects.requireNonNull(student_bdate.getText()).clear();
                        Objects.requireNonNull(student_reg_number.getText()).clear();
                        Objects.requireNonNull(student_pass.getText()).clear();
                        Objects.requireNonNull(student_username.getText()).clear();
                    }
                    else Toast.makeText(getApplicationContext(),"Failed to add new student.",Toast.LENGTH_LONG).show();

                }
                else Toast.makeText(getApplicationContext(),"Please fill all the fields.",Toast.LENGTH_LONG).show();




            }
        });
    }
}
