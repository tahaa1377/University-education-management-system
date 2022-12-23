package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ItemTeacherStudentCourseListController {

    @FXML
    private Button btnstudentScore;

    @FXML
    private Label id;

    @FXML
    private Label studentInfo;


    @FXML
    void btnstudentScoreAction(ActionEvent event) {

        MainApplication.indexStudent = Integer.parseInt(id.getText());

        MainApplication.change_page(btnstudentScore,"/com/example/management/scoreToStudent.fxml","score To Student");
    }

    public void setData(Student student, int i) {
        id.setText(""+i);
        studentInfo.setText(
                "name: " + student.getName() + "\n" +
                        "family: " + student.getFamily() + "\n" +
                        "national Code: " + student.getNationalCode() + "\n" +
                        "student Number: " + student.getStudentNumber()+ "\n"
        );
    }


}
