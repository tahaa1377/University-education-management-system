package com.example.management.model;

import com.example.management.model.Person;

public class Student extends Person {

    private String studentNumber;

    public Student(String name, String family, String nationalCode, String studentNumber) {
        super(name, family, nationalCode);
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber='" + studentNumber + '\'' +
                '}';
    }
}
