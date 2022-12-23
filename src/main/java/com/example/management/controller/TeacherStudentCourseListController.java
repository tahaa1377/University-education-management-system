package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.PresentedCourse;
import com.example.management.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherStudentCourseListController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private VBox vbox;

    @FXML
    void btnBackAction(ActionEvent event) {
        MainApplication.change_page(btnBack,"/com/example/management/showTeacherPresentedCourse.fxml","teacher page");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//2
        Node[] nodes = new Node[MainApplication.presentedCourseTeacher.studentList.size()];

        for (int i = 0; i < MainApplication.presentedCourseTeacher.studentList.size(); i++) {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/management/itemTeacherStudentCourseList.fxml"));

                nodes[i] = fxmlLoader.load();

                ItemTeacherStudentCourseListController item = fxmlLoader.getController();
                item.setData(MainApplication.presentedCourseTeacher.studentList.get(i),i);

                vbox.getChildren().add(nodes[i]);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
