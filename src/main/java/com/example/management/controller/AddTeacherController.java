package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;

public class AddTeacherController {

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

            if (!studentNumberExist(textStudentNumber.getText())){

                try {
                    String str =  textName.getText() +" " + textFamily.getText() +" " +textNationalCode.getText() +" " +textStudentNumber.getText() +"\n";

                    MainApplication.teacherArrayList.add(new Teacher(
                            textName.getText(),textFamily.getText()
                            ,textNationalCode.getText(),textStudentNumber.getText()
                    ));

                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/teacher.txt", true));
                    writer.append(str);
                    writer.close();

                    textError.setFill(Color.GREEN);
                    textError.setText("teacher added");
                    textName.setText("");
                    textFamily.setText("");
                    textNationalCode.setText("");
                    textStudentNumber.setText("");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else {
                textError.setText("this teacher code was exist!");
            }

        }

    }

    private boolean studentNumberExist(String text) {

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/teacher.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[]s = line.split(" ");
                if (s[3].equals(text)){
                    return true;
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    @FXML
    void btnBackAction(ActionEvent event) {
        MainApplication.change_page(btnBack,"/com/example/management/adminPage.fxml","admin page");

    }

}
