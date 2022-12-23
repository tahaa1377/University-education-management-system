package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.GeneralCourse;
import com.example.management.model.OptionalCourse;
import com.example.management.model.SpecializedCourse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCourseController implements Initializable{

    @FXML
    private Button btnAddCourse;

    @FXML
    private Button btnBack;

    @FXML
    private TextField courseCode;

    @FXML
    private TextField courseName;

    @FXML
    private Text txtError;

    @FXML
    private ChoiceBox<String> choiceBox;

    private String[]courseType = {"Optional","General","Specialized"};

    private String courseTypeSelect="";

    @FXML
    void btnAddCourseAction(ActionEvent event) {

        if (courseCode.getText().equals("") ||
            courseName.getText().equals("") ||
            courseTypeSelect.equals("")
        ){

            txtError.setText("all fileds must complete");

        } else if (courseCode.getText().length() != 4 ||
                !courseCode.getText().matches("[0-9]+")) {

            txtError.setText("course Code len must 4");

        }else {

            if (!courseCodeExist(courseCode.getText())){

                try {

                    String str =  courseCode.getText() +" " + courseName.getText()+" "+courseTypeSelect+"\n";

                    if (courseTypeSelect.equals("Specialized")){
                        MainApplication.courseArrayList.add(new SpecializedCourse(
                                Integer.parseInt(courseCode.getText()),courseName.getText()));
                    } else if (courseTypeSelect.equals("General")) {
                        MainApplication.courseArrayList.add(new GeneralCourse(
                                Integer.parseInt(courseCode.getText()),courseName.getText()));
                    }else {
                        MainApplication.courseArrayList.add(new OptionalCourse(
                                Integer.parseInt(courseCode.getText()),courseName.getText()));
                    }


                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/course.txt", true));
                    writer.append(str);
                    writer.close();

                    txtError.setFill(Color.GREEN);
                    txtError.setText("course added");
                    courseCode.setText("");
                    courseName.setText("");
                    courseTypeSelect = "";


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else {
                txtError.setText("this course code was exist!");
            }

        }

    }

    private boolean courseCodeExist(String text) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/course.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[]s = line.split(" ");
                if (s[0].equals(text)){
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBox.getItems().addAll(courseType);
        choiceBox.setOnAction(this::getCourseType);

    }

    public void getCourseType(ActionEvent event){
         courseTypeSelect = choiceBox.getValue();
    }
}
