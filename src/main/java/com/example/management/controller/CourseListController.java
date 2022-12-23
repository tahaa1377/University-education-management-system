package com.example.management.controller;

import com.example.management.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseListController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private VBox vbox;

    @FXML
    void btnBackAction(ActionEvent event) {

        MainApplication.change_page(btnBack, "/com/example/management/studentPage.fxml", "student page");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Node[] nodes = new Node[MainApplication.presentedCourseArrayList.size()];

        for (int i = 0; i < MainApplication.presentedCourseArrayList.size(); i++) {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/management/itemCourse.fxml"));

                nodes[i] = fxmlLoader.load();

                ItemCourseController item = fxmlLoader.getController();
                item.setData(MainApplication.presentedCourseArrayList.get(i),i);

                vbox.getChildren().add(nodes[i]);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }


}
