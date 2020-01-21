package com.example.mobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;
import android.widget.Toast;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "school_database";
    private static final String REG_TABLE = "user_reg";
    private static final String LOGIN_TABLE = "login_cred";
    private static final String COURSE_TABLE = "courses";
    private static final String COURSE_STUDENT_TABLE = "course_student";
    private static final String COURSE_STAFF_TABLE = "course_staff";

    private static final String FirstName = "FirstName";
    private static final String REG_Number = "REG_Number";
    private static final String MiddleName = "MiddleName";
    private static final String LastName = "LastName";
    private static final String Gender = "Gender";
    private static final String Bdate = "Bdate";
    private static final String Email = "Email";
    private static final String PhoneNumber = "PhoneNumber";
    private static final String Username = "Username";
    private static final String Password = "Password";
    private static final String Role = "Role";
    private static final String Region = "Region";
    private static final String District = "District";
    private static final String Ward = "Ward";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_reg = "CREATE TABLE " + REG_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,REG_Number TEXT UNIQUE," +
                "FirstName TEXT,MiddleName TEXT,LastName TEXT,Gender TEXT,Bdate TEXT,Email TEXT,PhoneNumber INTEGER," +
                "Region TEXT,District TEXT,Ward TEXT)";

        String login_cred = "CREATE TABLE " + LOGIN_TABLE + "(ID INTEGER PRIMARY KEY,Username TEXT, Role TEXT, Password TEXT," +
                "FOREIGN KEY(Username) REFERENCES REG_TABLE(REG_Number))";

        String courses = "CREATE TABLE " + COURSE_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,CourseCode TEXT UNIQUE,CourseName TEXT," +
                "CourseCredits INTEGER,YearOfStudy INTEGER,Semester INTEGER)";

        String course_student = "CREATE TABLE " + COURSE_STUDENT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CourseCode TEXT,Student_REG TEXT," +
                "CourseWork INTEGER,UniExam INTEGER,FOREIGN KEY(Student_REG) REFERENCES REG_TABLE(REG_Number)," +
                "FOREIGN KEY(CourseCode) REFERENCES COURSE_TABLE(CourseCode))";

        String course_staff = "CREATE TABLE " + COURSE_STAFF_TABLE + "(ID INTEGER PRIMARY KEY," +
                "CourseCode TEXT,Staff_REG TEXT," +
                "FOREIGN KEY(Staff_REG) REFERENCES REG_TABLE(REG_Number),FOREIGN KEY(CourseCode) REFERENCES COURSE_TABLE(CourseCode))";

        db.execSQL("PRAGMA foreign_keys=ON;");
        db.execSQL(course_staff);
        db.execSQL(courses);
        db.execSQL(course_student);
        db.execSQL(user_reg);
        db.execSQL(login_cred);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + REG_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_STAFF_TABLE);
        onCreate(db);
    }

    //    Insert Role and Password
//    public boolean addRnP() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//
//    }

    //    Insert User
    public boolean addUser(String reg_no, String fname, String mname, String lname, String gen,
                           String b_date, String email, String phone, String region,
                           String district, String ward, String username, String role, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ContentValues value2 = new ContentValues();

        values.put(REG_Number, reg_no);
        values.put(FirstName, fname);
        values.put(MiddleName, mname);
        values.put(LastName, lname);
        values.put(Gender, gen);
        values.put(Bdate, b_date);
        values.put(Email, email);
        values.put(PhoneNumber, phone);
        values.put(Region, region);
        values.put(District, district);
        values.put(Ward, ward);


        long result = db.insert(REG_TABLE, null, values);

        value2.put(Username, username);
        value2.put(Role, role);
        value2.put(Password, pass);

        long result2 = db.insert(LOGIN_TABLE, null, value2);

        db.close();

        if (result != -1 && result2 != -1) return true;
        else return false;
    }

    //    Authenticate User
    public int authUser(String username, String password) {
        String[] ID = {"ID"};
        SQLiteDatabase db = getReadableDatabase();
        String Selection = Username + " = ?" + " AND " + Password + " = ?";
        String[] SelectionArgs = {username, password};
        String[] SelectionArgs2 = {username};

        Cursor cursor = db.query(LOGIN_TABLE, ID, Selection, SelectionArgs, null, null, null);

        Cursor cursor2 = db.rawQuery("SELECT * FROM login_cred WHERE Username=?",SelectionArgs2);



        String cursorText = "";
        int cursorCount = cursor.getCount();

        if (cursor2.moveToFirst()){
            do {
                cursorText = cursor2.getString(2);
            }
            while (cursor2.moveToNext());
        }


        cursor.close();
        cursor2.close();
        db.close();

        if (cursorCount > 0) {
            switch (cursorText) {
                case "Admin":
                    return 1;
                case "Staff":
                    return 2;
                case "Student":
                    return 3;
                default:
                    return 0;
            }

        } else return 0;
    }
}

