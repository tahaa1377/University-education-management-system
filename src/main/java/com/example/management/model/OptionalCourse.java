package com.example.management.model;

public class OptionalCourse extends Course{

    public static final String courseType="Optional";
    public static final int NumberOfUnits=1;

    public OptionalCourse(int courseCode, String courseName) {
        super(courseCode, courseName);
    }

    @Override
    public String toString() {
        return "OptionalCourse{" +
                "courseType='" + courseType + '\'' +
                ", NumberOfUnits=" + NumberOfUnits +
                '}';
    }
}
