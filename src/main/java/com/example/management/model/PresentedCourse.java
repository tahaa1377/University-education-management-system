package com.example.management.model;

import java.util.ArrayList;

public class PresentedCourse {

    private Section section1;
    private Section section2;
    private Course course;
    private Teacher teacher;
    private int capacity;

    public ArrayList<Student>studentList = new ArrayList<>();

    public PresentedCourse(Section section1, Course course, Teacher teacher, int capacity) {
        this.section1 = section1;
        this.course = course;
        this.teacher = teacher;
        this.capacity = capacity;
    }

    public PresentedCourse(Section section1, Section section2, Course course, Teacher teacher, int capacity) {
        this.section1 = section1;
        this.section2 = section2;
        this.course = course;
        this.teacher = teacher;
        this.capacity = capacity;
    }

    public PresentedCourse() {
    }

    public Section getSection1() {
        return section1;
    }

    public void setSection1(Section section1) {
        this.section1 = section1;
    }

    public Section getSection2() {
        return section2;
    }

    public void setSection2(Section section2) {
        this.section2 = section2;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "PresentedCourse{" +
                "section1=" + section1.toString() +
//                section2 != null ? ", section2=" + section2.toString():"" +
                ", course=" + course.toString() +
                ", teacher=" + teacher.toString() +
                ", capacity=" + capacity +
                '}';
    }


    public String toString1() {
        return "PresentedCourse{" +
                "section1=" + section1.toString() +
                ", section2=" + section2.toString() +
                ", course=" + course.toString() +
                ", teacher=" + teacher.toString() +
                ", capacity=" + capacity +
                '}';
    }
}
