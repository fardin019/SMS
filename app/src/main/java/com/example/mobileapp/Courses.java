package com.example.mobileapp;

public class Courses {

    private String courseCode;
    private String courseName;
    private String courseCategory;
    private String courseCredits;
    private String courseYos;
    private String courseSemester;



    public Courses()
    {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCategory = courseCategory;
        this.courseCredits = courseCredits;
        this.courseYos = courseYos;
        this.courseSemester = courseSemester;

    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(String courseCredits) {
        this.courseCredits = courseCredits;
    }

    public String getCourseYos() {
        return courseYos;
    }

    public void setCourseYos(String courseYos) {
        this.courseYos = courseYos;
    }

    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
        this.courseSemester = courseSemester;
    }


}
