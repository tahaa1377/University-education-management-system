package com.example.management.controller;

import com.example.management.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminPageController {

    @FXML
    private Button btnAddStudnt;

    @FXML
    private Button btnAddTeacher;

    @FXML
    private Button btnOptionalCourse;

    @FXML
    private Button btnADDSection;

    @FXML
    private Button btnListStudents;

    @FXML
    private Button btnListTeachers;

    @FXML
    void btnADDSectionAction(ActionEvent event) {
        MainApplication.change_page(btnADDSection,"/com/example/management/addSection.fxml","add section");

    }

    @FXML
    void btnAddStudntAction(ActionEvent event) {

        MainApplication.change_page(btnAddStudnt,"/com/example/management/addStudent.fxml","add student");
    }

    @FXML
    void btnAddTeacherAction(ActionEvent event) {

        MainApplication.change_page(btnAddTeacher,"/com/example/management/addTeacher.fxml","add teacher");
    }

    @FXML
    void btnOptionalCourseAction(ActionEvent event) {
        MainApplication.change_page(btnAddTeacher, "/com/example/management/addCourse.fxml","add course");
    }

    @FXML
    void btnListStudentsAction(ActionEvent event) {
        MainApplication.change_page(btnListStudents, "/com/example/management/studentList.fxml","student List");

    }

    @FXML
    void btnListTeachersAction(ActionEvent event) {
        MainApplication.change_page(btnListStudents, "/com/example/management/teacherList.fxml","teacher List");

    }

}
