package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.Student;
import com.example.management.model.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItemListStudentController {

    @FXML
    private Button btnEditStudent;

    @FXML
    private Label id;

    @FXML
    private Label studentInfo;

    @FXML
    void btnEditStudentAction(ActionEvent event) {

        int i = Integer.parseInt(id.getText());

        MainApplication.editStudent.student = MainApplication.studentArrayList.get(i);
        MainApplication.editStudent.index = i;
        MainApplication.change_page(btnEditStudent, "/com/example/management/editStudent.fxml","edit student");
    }

    public void setData(Student student,int i){
        id.setText(""+i);
        studentInfo.setText(
                        "name: " + student.getName() + "\n" +
                        "family: " + student.getFamily() + "\n" +
                        "national Code: " + student.getNationalCode() + "\n" +
                        "student Number: " + student.getStudentNumber()+ "\n"
        );

    }



}
