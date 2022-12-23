package com.example.management.controller;

import com.example.management.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StudentPageController {

    @FXML
    private Button scoreWeek;
    @FXML
    private Button changePassword;

    @FXML
    private Button courseList;

    @FXML
    private Button scoreList;

    @FXML
    void scoreWeekAction(ActionEvent event) {
        MainApplication.change_page(scoreWeek,"/com/example/management/courseWeek.fxml","course Week");

    }

    @FXML
    void scoreListAction(ActionEvent event) {
        MainApplication.change_page(scoreList,"/com/example/management/scoreList.fxml","score list");

    }

    @FXML
    void changePasswordAction(ActionEvent event) {
        MainApplication.change_page(changePassword,"/com/example/management/studentChangePassword.fxml","change password page");

    }

    @FXML
    void courseListAction(ActionEvent event) {
        MainApplication.change_page(courseList,"/com/example/management/courseList.fxml","course List page");

    }

}
