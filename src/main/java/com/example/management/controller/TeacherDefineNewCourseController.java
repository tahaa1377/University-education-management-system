package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherDefineNewCourseController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSubmit;

    @FXML
    private ChoiceBox<String> courseChoiceBox;
    private ArrayList<String>courseList = new ArrayList<>();
    private String courseSelect="";

    @FXML
    private TextField inputCapacity;

    @FXML
    private ChoiceBox<String> section1ChoiceBox;
    private ArrayList<String>section1List = new ArrayList<>();
    private String section1Select="";
    @FXML
    private ChoiceBox<String> section2ChoiceBox;
    private ArrayList<String>section2List = new ArrayList<>();
    private String section2Select="";

    @FXML
    private Label txtError;


    @FXML
    void btnSubmitAction(ActionEvent event) {

        if (courseSelect.equals("") || section1Select.equals("") || inputCapacity.getText().equals("")
                || (!section2ChoiceBox.isDisable() && section2Select.equals(""))
        ){

            txtError.setText("all fields must complete");

        } else if (!section2ChoiceBox.isDisable() && section1Select.equals(section2Select)) {

            txtError.setText("wrong. section 1 and section 2 are same");

        } else if (Integer.parseInt(inputCapacity.getText()) < 10 || Integer.parseInt(inputCapacity.getText()) > 40) {

            txtError.setText("wrong. Capacity is between 10 and 40");

        }else {

            int courseCode= Integer.parseInt(courseSelect.split(" ")[0]);
            String courseName= courseSelect.split(" ")[1];

            Days d1 = switch (section1Select.split(" ")[0]) {
                case "Monday" -> Days.Monday;
                case "Tuesday" -> Days.Tuesday;
                case "Wednesday" -> Days.Wednesday;
                case "Thursday" -> Days.Thursday;
                case "Friday" -> Days.Friday;
                case "Saturday" -> Days.Saturday;
                default -> Days.Sunday;
            };

            String hour1 = section1Select.split(" ")[1];
            Teacher teacher = MainApplication.teacherLogin;
            int capacity = Integer.parseInt(inputCapacity.getText());

           if (!sectionAndTeacherConfiltcted(teacher,new Section(d1,hour1))) {

               try {

                   if (!section2ChoiceBox.isDisable()) {

                       Days d2 = switch (section2Select.split(" ")[0]) {
                           case "Monday" -> Days.Monday;
                           case "Tuesday" -> Days.Tuesday;
                           case "Wednesday" -> Days.Wednesday;
                           case "Thursday" -> Days.Thursday;
                           case "Friday" -> Days.Friday;
                           case "Saturday" -> Days.Saturday;
                           default -> Days.Sunday;
                       };
                       String hour2 = section2Select.split(" ")[1];

                       MainApplication.presentedCourseArrayList.add(
                               new PresentedCourse(new Section(d1, hour1),
                                       new Section(d2, hour2), new Course(courseCode, courseName),
                                       new Teacher(teacher.getName(), teacher.getFamily(), teacher.getNationalCode(), teacher.getTeacherCode()),
                                       capacity));

                       String str = "" + section1Select.split(" ")[0] + " " + hour1 + " " + section2Select.split(" ")[0] + " " + hour2 + " " + courseCode + " " + courseName + " " + teacher.getName() + " " + teacher.getFamily() + " " + teacher.getNationalCode() + " " + teacher.getTeacherCode() + " " + capacity + "\n";
                       BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/presentedCourse.txt", true));
                       writer.append(str);
                       writer.close();

                   } else {

                       MainApplication.presentedCourseArrayList.add(
                               new PresentedCourse(
                                       new Section(d1, hour1),
                                       new Course(courseCode, courseName),
                                       new Teacher(teacher.getName(), teacher.getFamily(), teacher.getNationalCode(), teacher.getTeacherCode()),
                                       capacity));

                       String str = "" + section1Select.split(" ")[0] + " " + hour1 + " " + courseCode + " " + courseName + " " + teacher.getName() + " " + teacher.getFamily() + " " + teacher.getNationalCode() + " " + teacher.getTeacherCode() + " " + capacity + "\n";
                       BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/presentedCourse.txt", true));
                       writer.append(str);
                       writer.close();
                   }

               } catch (IOException e) {
                   throw new RuntimeException(e);
               }

               courseChoiceBox.setValue("");
               section1ChoiceBox.setValue("");
               section2ChoiceBox.setValue("");
               inputCapacity.setText("");
               section2Select = "";
               section1Select = "";
               courseSelect = "";

               txtError.setText("new course added");

           }

        }


    }

    private boolean sectionAndTeacherConfiltcted(Teacher teacher, Section section1) {

        if (!section2ChoiceBox.isDisable()) {

            Days d2 = switch (section2Select.split(" ")[0]) {
                case "Monday" -> Days.Monday;
                case "Tuesday" -> Days.Tuesday;
                case "Wednesday" -> Days.Wednesday;
                case "Thursday" -> Days.Thursday;
                case "Friday" -> Days.Friday;
                case "Saturday" -> Days.Saturday;
                default -> Days.Sunday;
            };
            String hour2 = section2Select.split(" ")[1];

            Section section2 = new Section(d2, hour2);

            for (PresentedCourse p:MainApplication.presentedCourseArrayList) {
                if (p.getSection2() != null) {
                    if (
                           p.getTeacher().getTeacherCode().equals(teacher.getTeacherCode()) &&
                           p.getSection2().getDays().toString().equals(section2.getDays().toString()) &&
                           p.getSection2().getHour().equals(section2.getHour())
                    ) {
                        txtError.setText("dear teacher. section 2 was full. you choosed this time for another course.");
                        return true;
                    }
                }

                if (
                        p.getTeacher().getTeacherCode().equals(teacher.getTeacherCode()) &&
                        p.getSection1().getDays().toString().equals(section2.getDays().toString()) &&
                        p.getSection1().getHour().equals(section2.getHour())
                ){
                    txtError.setText("dear teacher. section 2 was full. you choosed this time for another course.");
                    return true;
                }

            }

        }

        for (PresentedCourse p:MainApplication.presentedCourseArrayList) {

            if (
                    p.getTeacher().getTeacherCode().equals(teacher.getTeacherCode()) &&
                    p.getSection1().getDays().toString().equals(section1.getDays().toString()) &&
                    p.getSection1().getHour().equals(section1.getHour())
            ){
                txtError.setText("dear teacher. section 1 was full. you choosed this time for another course.");
                return true;
            }

            if (p.getSection2() != null) {
                if (
                        p.getTeacher().getTeacherCode().equals(teacher.getTeacherCode()) &&
                                p.getSection2().getDays().toString().equals(section1.getDays().toString()) &&
                                p.getSection2().getHour().equals(section1.getHour())
                ) {
                    txtError.setText("dear teacher. section 1 was full. you choosed this time for another course.");
                    return true;
                }
            }

        }

        return false;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Course c:MainApplication.courseArrayList) {
            if (c instanceof SpecializedCourse){
                courseList.add(c.getCourseCode()+" "+c.getCourseName() +" ("+((SpecializedCourse) c).courseType+")");
            } else if (c instanceof GeneralCourse) {
                courseList.add(c.getCourseCode()+" "+c.getCourseName() +" ("+((GeneralCourse) c).courseType+")");
            } else if (c instanceof OptionalCourse) {
                courseList.add(c.getCourseCode()+" "+c.getCourseName() +" ("+ OptionalCourse.courseType +")");
            }
        }

        for (Section section:MainApplication.sectionArrayList) {
            section1List.add(section.getDays() +" "+section.getHour());
            section2List.add(section.getDays() +" "+section.getHour());
        }

        courseChoiceBox.getItems().addAll(courseList);
        section1ChoiceBox.getItems().addAll(section1List);
        section2ChoiceBox.getItems().addAll(section2List);

        courseChoiceBox.setOnAction(this::getCourseType);
        section1ChoiceBox.setOnAction(this::getSec1Type);
        section2ChoiceBox.setOnAction(this::getSec2Type);

    }

    public void getCourseType(ActionEvent event){
        courseSelect = courseChoiceBox.getValue();

        if (courseSelect.contains("Specialized")){
            section2ChoiceBox.setDisable(false);
        }else {
            section2ChoiceBox.setDisable(true);
            section2Select="";
            section2ChoiceBox.setValue("");
        }

    }

    public void getSec1Type(ActionEvent event){
        section1Select = section1ChoiceBox.getValue();
    }

    public void getSec2Type(ActionEvent event){
        section2Select = section2ChoiceBox.getValue();
    }


    @FXML
    void btnBackAction(ActionEvent event) {
        MainApplication.change_page(btnBack,"/com/example/management/teacherPage.fxml","teacher page");
    }



}
