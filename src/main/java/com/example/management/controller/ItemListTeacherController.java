package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItemListTeacherController {

    @FXML
    private Button btnEditStudent;

    @FXML
    private Label id;

    @FXML
    private Label studentInfo;

    @FXML
    void btnEditStudentAction(ActionEvent event) {

        int i = Integer.parseInt(id.getText());

        MainApplication.editTeacher.teacher = MainApplication.teacherArrayList.get(i);
        MainApplication.editTeacher.index = i;
        MainApplication.change_page(btnEditStudent, "/com/example/management/editTeacher.fxml","edit teacher");

    }

    public void setDataTeacher(Teacher student, int i){
        id.setText(""+i);
        studentInfo.setText(
                "name: " + student.getName() + "\n" +
                        "family: " + student.getFamily() + "\n" +
                        "national Code: " + student.getNationalCode() + "\n" +
                        "Teacher Code: " + student.getTeacherCode()+ "\n"
        );

    }

}
