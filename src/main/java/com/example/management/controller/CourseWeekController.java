package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.PresentedCourse;
import com.example.management.model.Student;
import com.example.management.model.StudentListCourse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseWeekController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Label scoreList;

    @FXML
    void btnBackAction(ActionEvent event) {
        MainApplication.change_page(btnBack, "/com/example/management/studentPage.fxml", "student page");

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        readFromFileStudentListCourse();

        for (StudentListCourse s:list) {

            for (PresentedCourse presentedCourse:MainApplication.presentedCourseArrayList) {
                if (presentedCourse.getCourse().getCourseCode() == Integer.parseInt(s.getCourseCode())){

                    if (s.getStudent().getStudentNumber().equals(MainApplication.studentLogin.getStudentNumber())){


                        if (s.getScore() != -1) {

                            if (presentedCourse.getSection2() == null){

                                scoreList.setText(scoreList.getText()+
                                        "course name: " + presentedCourse.getCourse().getCourseName() + "\n" +
                                        "teacher: " + presentedCourse.getTeacher().getName() + " " +
                                        "" + presentedCourse.getTeacher().getFamily() + "\n" +
                                        "-------------------------------------\n"
                                );

                            }else {
                                scoreList.setText(scoreList.getText()+
                                        "course name: " + presentedCourse.getCourse().getCourseName() + "\n" +
                                        "teacher: " + presentedCourse.getTeacher().getName() + " " +
                                        "" + presentedCourse.getTeacher().getFamily() + "\n" +
                                        "-------------------------------------\n"
                                );
                            }

                        }else {

                            if (presentedCourse.getSection2() == null){

                                scoreList.setText(scoreList.getText()+
                                        "course name: " + presentedCourse.getCourse().getCourseName() + "\n" +
                                        "teacher: " + presentedCourse.getTeacher().getName() + " " +
                                        "" + presentedCourse.getTeacher().getFamily() + "\n" +
                                        "-------------------------------------\n"
                                );

                            }else {
                                scoreList.setText(scoreList.getText()+
                                        "course name: " + presentedCourse.getCourse().getCourseName() + "\n" +
                                        "teacher: " + presentedCourse.getTeacher().getName() + " " +
                                        "" + presentedCourse.getTeacher().getFamily() + "\n" +
                                        "Capacity: " + presentedCourse.getCapacity() + "\n" +
                                        "-------------------------------------\n"
                                );
                            }

                        }

                    }

                }



            }



        }

    }

    public ArrayList<StudentListCourse> list = new ArrayList<>();

    private void readFromFileStudentListCourse() {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/studentListCourse.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[]s = line.split(" ");

                if (s.length == 6){

                    Student student = new Student(s[2],s[3],s[4],s[5]);
                    list.add(new StudentListCourse(s[0],s[1],student));

                }else if (s.length == 7){
                    Student student = new Student(s[2],s[3],s[4],s[5]);
                    list.add(new StudentListCourse(s[0],s[1],student,Double.parseDouble(s[6])));
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
