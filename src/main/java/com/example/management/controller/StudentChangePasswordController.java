package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudentChangePasswordController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField confrimNewPassword;

    @FXML
    private TextField currentPassword;

    @FXML
    private TextField newPassword;

    @FXML
    private Label txtError;

    @FXML
    void btnBackAction(ActionEvent event) {
        MainApplication.change_page(btnBack, "/com/example/management/studentPage.fxml", "student page");

    }

    @FXML
    void btnSubmitAction(ActionEvent event) {

        Student student = MainApplication.studentLogin;

        if (! currentPassword.getText().equals(student.getNationalCode())){
            txtError.setText("your password not this");

        }else if (! newPassword.getText().equals(confrimNewPassword.getText())){
            txtError.setText("new Password and confrim Password was not equals");
        }else {

            for (Student s:MainApplication.studentArrayList) {
                if (
                        s.getStudentNumber().equals(student.getStudentNumber()) &&
                        s.getNationalCode().equals(student.getNationalCode())
                ){

                    s.setNationalCode(confrimNewPassword.getText());
                    break;
                }
            }

            txtError.setText("password changed");
            currentPassword.setText("");
            confrimNewPassword.setText("");
            newPassword.setText("");

            writeToFile();

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


}
