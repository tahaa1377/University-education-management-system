package com.example.management.model;

public class GeneralCourse extends Course{

    public final String courseType="General";
    public final int NumberOfUnits=2;

    public GeneralCourse(int courseCode, String courseName) {
        super(courseCode, courseName);
    }

}
