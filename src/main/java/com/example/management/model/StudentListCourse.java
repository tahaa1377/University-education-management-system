package com.example.management.model;

public class StudentListCourse {

    private String courseCode;
    private String teacherCode;
    private Student student;
    private double score=-1;

    public StudentListCourse(String courseCode, String teacherCode, Student student, double score) {
        this.courseCode = courseCode;
        this.teacherCode = teacherCode;
        this.student = student;
        this.score = score;
    }

    public StudentListCourse(String courseCode, String teacherCode, Student student) {
        this.courseCode = courseCode;
        this.teacherCode = teacherCode;
        this.student = student;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
