package com.example.management.model;

import com.example.management.model.Person;

public class Teacher extends Person {

    public String teacherCode;

    public Teacher(String name, String family, String nationalCode, String teacherCode) {
        super(name, family, nationalCode);
        this.teacherCode = teacherCode;
    }



    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherCode='" + teacherCode + '\'' +
                '}';
    }
}
