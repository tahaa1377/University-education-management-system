package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.PresentedCourse;
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

public class ShowTeacherPresentedCourseController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private VBox vbox;

    @FXML
    void btnBackAction(ActionEvent event) {
        MainApplication.change_page(btnBack,"/com/example/management/teacherPage.fxml","teacher page");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<PresentedCourse>presentedCourses = new ArrayList<>();

        for (PresentedCourse p:MainApplication.presentedCourseArrayList) {
            if (p.getTeacher().getTeacherCode().equals(MainApplication.teacherLogin.getTeacherCode())){
                presentedCourses.add(p);
            }
        }

        Node[] nodes = new Node[presentedCourses.size()];

        for (int i = 0; i < presentedCourses.size(); i++) {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/management/ItemPresentedTeacherCourse.fxml"));

                nodes[i] = fxmlLoader.load();

                ItemPresentedTeacherCourseContoller item = fxmlLoader.getController();
                item.setData(presentedCourses.get(i),i);

                vbox.getChildren().add(nodes[i]);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }


}
