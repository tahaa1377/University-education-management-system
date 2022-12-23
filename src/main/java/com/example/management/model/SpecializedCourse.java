package com.example.management.model;

public class SpecializedCourse extends Course{

    public final String courseType="Specialized";
    public final int NumberOfUnits=3;

    public SpecializedCourse(int courseCode, String courseName) {
        super(courseCode, courseName);
    }

}
