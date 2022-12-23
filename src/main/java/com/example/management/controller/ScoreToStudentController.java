package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.Student;
import com.example.management.model.StudentListCourse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreToStudentController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Label txtError;

    @FXML
    private Button btnOk;

    @FXML
    private TextField score;

    @FXML
    void btnBackAction(ActionEvent event) {

        MainApplication.change_page(btnBack,"/com/example/management/teacherStudentCourseList.fxml","teacher page");

    }

    public ArrayList<StudentListCourse>list = new ArrayList<>();

    @FXML
    void btnOkAction(ActionEvent event) {

        double scores = Double.parseDouble(score.getText());

        writeToFileStudentCourse(scores);

        txtError.setText("score gived");

    }

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

    private void writeToFileStudentCourse(double score) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/studentListCourse.txt"));
            writer.append("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/studentListCourse.txt", true));

            for (StudentListCourse s:list) {

                if (s.getScore() == -1){

                    if (Integer.parseInt(s.getCourseCode()) ==  MainApplication.presentedCourseTeacher.getCourse().getCourseCode()
                            &&
                            s.getTeacherCode().equals(MainApplication.presentedCourseTeacher.getTeacher().getTeacherCode())
                            &&
                            s.getStudent().getStudentNumber().equals(MainApplication.presentedCourseTeacher.studentList.get(MainApplication.indexStudent).getStudentNumber())
                    ){

                        String str=""+s.getCourseCode()+" "+s.getTeacherCode()+" "+ s.getStudent().getName() + " "+s.getStudent().getFamily() + " "+s.getStudent().getNationalCode()+" "+s.getStudent().getStudentNumber()+" "+score+"\n";
                        writer.append(str);

                    }else {
                        String str=""+s.getCourseCode()+" "+s.getTeacherCode()+" "+ s.getStudent().getName() + " "+s.getStudent().getFamily() + " "+s.getStudent().getNationalCode()+" "+s.getStudent().getStudentNumber()+"\n";
                        writer.append(str);

                    }

                }else {

                    if (Integer.parseInt(s.getCourseCode()) ==  MainApplication.presentedCourseTeacher.getCourse().getCourseCode()
                            &&
                            s.getTeacherCode().equals(MainApplication.presentedCourseTeacher.getTeacher().getTeacherCode())
                            &&
                            s.getStudent().getStudentNumber().equals(MainApplication.presentedCourseTeacher.studentList.get(MainApplication.indexStudent).getStudentNumber())
                    ){

                        String str=""+s.getCourseCode()+" "+s.getTeacherCode()+" "+ s.getStudent().getName() + " "+s.getStudent().getFamily() + " "+s.getStudent().getNationalCode()+" "+s.getStudent().getStudentNumber()+" "+score+"\n";
                        writer.append(str);

                    }else {

                        String str=""+s.getCourseCode()+" "+s.getTeacherCode()+" "+ s.getStudent().getName() + " "+s.getStudent().getFamily() + " "+s.getStudent().getNationalCode()+" "+s.getStudent().getStudentNumber()+" "+s.getScore()+"\n";
                        writer.append(str);
                    }



                }

            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        readFromFileStudentListCourse();

        for (StudentListCourse s:list) {

                if (Integer.parseInt(s.getCourseCode()) == MainApplication.presentedCourseTeacher.getCourse().getCourseCode()
                        &&
                        s.getTeacherCode().equals(MainApplication.presentedCourseTeacher.getTeacher().getTeacherCode())
                        &&
                        s.getStudent().getStudentNumber().equals(MainApplication.presentedCourseTeacher.studentList.get(MainApplication.indexStudent).getStudentNumber())
                        &&
                        s.getScore() != -1
                ) {

                    score.setText(""+s.getScore());

                }

            }

    }

}
