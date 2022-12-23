package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.Student;

import com.example.management.model.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private Button btn_login;

    @FXML
    private Text text_login_msg;

    @FXML
    private TextField text_login_password;

    @FXML
    private TextField text_login_username;

    @FXML
    void btn_login_action(ActionEvent event) {

       String username= "admin";
       String password= "1234";

       if (text_login_username.getText().equals(username) &&
               text_login_password.getText().equals(password)){

           MainApplication.change_page(btn_login,"/com/example/management/adminPage.fxml","admin page");


       } else if (isInStudentList(text_login_username.getText(),text_login_password.getText())) {

           MainApplication.studentLogin = isInStudent(text_login_username.getText(),text_login_password.getText());
           MainApplication.change_page(btn_login,"/com/example/management/studentPage.fxml","student page");

       } else if (isInTeacherList(text_login_username.getText(),text_login_password.getText())) {

           MainApplication.teacherLogin = isInTeacher(text_login_username.getText(),text_login_password.getText());
           MainApplication.change_page(btn_login,"/com/example/management/teacherPage.fxml","teacher page");

       } else {

           text_login_msg.setText("username or password wrong!");
       }

    }

    private boolean isInTeacherList(String username, String password) {
        for (Teacher s:MainApplication.teacherArrayList) {
            if (s.getTeacherCode().equals(username) && s.getNationalCode().equals(password)){
                return true;
            }
        }
        return false;
    }

    private Teacher isInTeacher(String username, String password) {
        for (Teacher s:MainApplication.teacherArrayList) {
            if (s.getTeacherCode().equals(username) && s.getNationalCode().equals(password)){
                return s;
            }
        }
        return null;
    }

    private boolean isInStudentList(String username, String password) {

        for (Student s:MainApplication.studentArrayList) {
            if (s.getStudentNumber().equals(username) && s.getNationalCode().equals(password)){
                return true;
            }
        }
        return false;
    }

    private Student isInStudent(String username, String password) {

        for (Student s:MainApplication.studentArrayList) {
            if (s.getStudentNumber().equals(username) && s.getNationalCode().equals(password)){
                return s;
            }
        }
        return null;
    }

}
