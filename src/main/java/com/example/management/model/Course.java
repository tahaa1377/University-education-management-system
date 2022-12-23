package com.example.management.model;

public class Course {

    private int CourseCode;
    private String CourseName;

    public Course(int courseCode, String courseName) {
        CourseCode = courseCode;
        CourseName = courseName;
    }

    public int getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(int courseCode) {
        CourseCode = courseCode;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CourseCode=" + CourseCode +
                ", CourseName='" + CourseName + '\'' +
                '}';
    }
}
