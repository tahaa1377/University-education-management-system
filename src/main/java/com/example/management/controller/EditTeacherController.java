package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.EditStudent;
import com.example.management.model.EditTeacher;
import com.example.management.model.Student;
import com.example.management.model.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditTeacherController implements Initializable {

    @FXML
    private Button btnAddStudent;

    @FXML
    private Button btnBack;

    @FXML
    private Text textError;

    @FXML
    private TextField textFamily;

    @FXML
    private TextField textName;

    @FXML
    private TextField textNationalCode;

    @FXML
    private TextField textStudentNumber;

    @FXML
    void btnAddStudentAction(ActionEvent event) {


        if (    textName.getText().equals("") ||
                textFamily.getText().equals("") ||
                textStudentNumber.getText().equals("") ||
                textNationalCode.getText().equals("")){

            textError.setText("all fileds must complete");

        } else if (textStudentNumber.getText().length() != 7 ||
                !textStudentNumber.getText().matches("[0-9]+")
        ) {
            textError.setText("teacher Code len must 7 and contains just number");
        }else {

                try {
//                    String str =  textName.getText() +" " + textFamily.getText() +" " +textNationalCode.getText() +" " +textStudentNumber.getText() +"\n";

                    MainApplication.teacherArrayList.set(MainApplication.editTeacher.index,
                            new Teacher(textName.getText(),textFamily.getText()
                            ,textNationalCode.getText(),textStudentNumber.getText()
                    ));

//                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/teacher.txt", true));
//                    writer.append(str);
//                    writer.close();

                    textError.setFill(Color.GREEN);
                    textError.setText("teacher edited");
//                    textName.setText("");
//                    textFamily.setText("");
//                    textNationalCode.setText("");
//                    textStudentNumber.setText("");

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            writeToFile();

            MainApplication.change_page(btnBack, "/com/example/management/teacherList.fxml","teacher list");


        }

    }


    private void writeToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/teacher.txt"));
            writer.append("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/teacher.txt", true));
            for (Teacher s:MainApplication.teacherArrayList) {
                String str =  s.getName() +" " + s.getFamily() +" " +s.getNationalCode() +" " +s.getTeacherCode() +"\n";
                writer.append(str);
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        MainApplication.change_page(btnBack, "/com/example/management/teacherList.fxml","teacher list");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        EditTeacher student = MainApplication.editTeacher;

        textName.setText(student.teacher.getName());
        textFamily.setText(student.teacher.getFamily());
        textNationalCode.setText(student.teacher.getNationalCode());
        textStudentNumber.setText(student.teacher.getTeacherCode());
    }


}
