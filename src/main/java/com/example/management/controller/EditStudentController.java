package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.EditStudent;
import com.example.management.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EditStudentController implements Initializable {

    @FXML
    private Button btnAddStudent;

    @FXML
    private Button btnBack;

    @FXML
    private TextField textFamily;

    @FXML
    private TextField textName;

    @FXML
    private TextField textNationalCode;

    @FXML
    private TextField textStudentNumber;

    @FXML
    private Text txtError;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        EditStudent student = MainApplication.editStudent;

        textName.setText(student.student.getName());
        textFamily.setText(student.student.getFamily());
        textNationalCode.setText(student.student.getNationalCode());
        textStudentNumber.setText(student.student.getStudentNumber());

    }

    @FXML
    void btnEditStudentAction(ActionEvent event) {

        if (    textName.getText().equals("") ||
                textFamily.getText().equals("") ||
                textStudentNumber.getText().equals("") ||
                textNationalCode.getText().equals("")){

            txtError.setText("all fileds must complete");

        } else if (textStudentNumber.getText().length() != 7 ||
                !textStudentNumber.getText().matches("[0-9]+")
        ) {
            txtError.setText("Student Number len must 7 and contains just number");
        }else {


            try {
                MainApplication.studentArrayList.set(MainApplication.editStudent.index,
                        new Student(textName.getText(),textFamily.getText()
                                ,textNationalCode.getText(),textStudentNumber.getText()));

                txtError.setFill(Color.GREEN);
                txtError.setText("Student edited");
//                    textName.setText("");
//                    textFamily.setText("");
//                    textNationalCode.setText("");
//                    textStudentNumber.setText("");



            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            writeToFile();

            MainApplication.change_page(btnBack, "/com/example/management/studentList.fxml","student list");


        }

    }


    private void writeToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/student.txt"));
            writer.append("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/student.txt", true));
            for (Student s:MainApplication.studentArrayList) {
                String str =  s.getName() +" " + s.getFamily() +" " +s.getNationalCode() +" " +s.getStudentNumber() +"\n";
                writer.append(str);
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnBackAction(ActionEvent event) {
        MainApplication.change_page(btnBack, "/com/example/management/studentList.fxml","student list");

    }


}
