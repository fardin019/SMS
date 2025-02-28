package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputEditText Username, Password;
    Button login_btn;

    DatabaseHelper databaseHelper;
//    SharedPreferences sharedPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        Username = findViewById(R.id.login_username_tiet);
        Password = findViewById(R.id.login_pass_tiet);

        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = Objects.requireNonNull(Username.getText()).toString();
                String pass = Objects.requireNonNull(Password.getText()).toString();

                Intent loginToAdmin = new Intent(MainActivity.this,AdminDash.class);
                Intent loginToStaff = new Intent(MainActivity.this,StaffDash.class);
                Intent loginToStudent = new Intent(MainActivity.this,StudentDash.class);

//                sharedPre = getSharedPreferences("loginDetails",MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPre.edit();



                int result;
                result = databaseHelper.authUser(user,pass);


                if (result == 1){

//                    sharedPre.edit().putString("username",user).apply();
//                    sharedPre.edit().putInt("role",result).apply();


                    Toast.makeText(getApplicationContext(),"Welcome Admin",Toast.LENGTH_LONG).show();
                    startActivity(loginToAdmin);
                    finish();
                }
                else if (result == 2){

//                    sharedPre.edit().putString("username",user).apply();
//                    sharedPre.edit().putInt("role",result).apply();

                    Toast.makeText(getApplicationContext(),"Welcome Staff",Toast.LENGTH_LONG).show();
                    startActivity(loginToStaff);
                    finish();
                }
                else if (result == 3){

//                    sharedPre.edit().putString("username",user).apply();
//                    sharedPre.edit().putInt("role",result).apply();

                    Toast.makeText(getApplicationContext(),"Welcome Student",Toast.LENGTH_LONG).show();
                    startActivity(loginToStudent);
                    finish();
                }
                else Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
            }
        });
    }



}
