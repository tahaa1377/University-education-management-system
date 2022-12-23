package com.example.management.controller;

import com.example.management.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TeacherPageController {

    @FXML
    private Button defineNewCourse;

    @FXML
    private Button showMyPresentedCourse;

    @FXML
    void defineNewCourseActon(ActionEvent event) {

        MainApplication.change_page(defineNewCourse,"/com/example/management/teacherDefineNewCourse.fxml","define New Course");

    }

    @FXML
    void showMyPresentedCourseAction(ActionEvent event) {

        MainApplication.change_page(showMyPresentedCourse,"/com/example/management/showTeacherPresentedCourse.fxml","show My Presented Course");

    }

}
