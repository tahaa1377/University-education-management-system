package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.Course;
import com.example.management.model.PresentedCourse;
import com.example.management.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ItemCourseController {

    @FXML
    private Label CoursInfo;

    @FXML
    private Button btnchoiceCourse;

    @FXML
    private Label id;

    @FXML
    void btnchoiceCourseAction(ActionEvent event) {

        int i = Integer.parseInt(id.getText());
        PresentedCourse presentedCourse = MainApplication.presentedCourseArrayList.get(i);

        if (presentedCourse.getCapacity() > 0){

            if (!studentChooseCourseBefore(presentedCourse.getCourse().getCourseCode()
                    ,presentedCourse.getTeacher().getTeacherCode(),MainApplication.studentLogin)){

                if (!timeChooseStudentCourseBeforeConflict(presentedCourse,MainApplication.studentLogin)){

                    presentedCourse.studentList.add(MainApplication.studentLogin);
                    presentedCourse.setCapacity(presentedCourse.getCapacity() - 1);
                    writeToFillePresentedCourse();

                    writeToFileStudentCourse(presentedCourse.getCourse().getCourseCode(),presentedCourse.getTeacher().getTeacherCode(),MainApplication.studentLogin);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("you sign up to this course");
                    alert.getDialogPane().setPrefSize(300,100);
                    alert.setHeaderText(null);
                    alert.showAndWait();

                    MainApplication.change_page(btnchoiceCourse,"/com/example/management/courseList.fxml","course List page");

                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("you choose another course that section conflicted to this course section");
                    alert.getDialogPane().setPrefSize(500,100);
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }


            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you choose this course before!");
                alert.getDialogPane().setPrefSize(300,100);
                alert.setHeaderText(null);
                alert.showAndWait();
            }

        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("this course has no capacity");
            alert.getDialogPane().setPrefSize(300,100);
            alert.setHeaderText(null);
            alert.showAndWait();

        }
    }

    private boolean studentChooseCourseBefore(int courseCode, String teacherCode, Student studentLogin) {

        for (PresentedCourse p:MainApplication.presentedCourseArrayList) {
//            System.out.println(p.getCourse().getCourseName());

            if (
                    p.getCourse().getCourseCode() == courseCode &&
                    p.getTeacher().getTeacherCode().equals(teacherCode)
            ){

                for (Student s:p.studentList) {

                    if (s.getStudentNumber().equals(studentLogin.getStudentNumber())){
                        return true;
                    }

                }

            }


        }

        return false;
    }

    private boolean timeChooseStudentCourseBeforeConflict(PresentedCourse presentedCourse, Student student) {

        for (PresentedCourse p:MainApplication.presentedCourseArrayList) {
            
                for (Student s:p.studentList) {

                    if (s.getStudentNumber().equals(student.getStudentNumber())){

                        for (Course c:MainApplication.courseArrayList) {
                            if (c.getCourseCode() == p.getCourse().getCourseCode()){

                                //which course this login student choose.
                                if (
                                    p.getSection1().getDays().toString().equals(presentedCourse.getSection1().getDays().toString()) &&
                                    p.getSection1().getHour().equals(presentedCourse.getSection1().getHour())
                                ){
                                    return true;
                                }

                                if (presentedCourse.getSection2() != null){
                                    if (
                                            p.getSection1().getDays().toString().equals(presentedCourse.getSection2().getDays().toString()) &&
                                            p.getSection1().getHour().equals(presentedCourse.getSection2().getHour())
                                    ){
                                        return true;
                                    }
                                }

                                if (p.getSection2() != null){
                                    if (
                                            p.getSection2().getDays().toString().equals(presentedCourse.getSection1().getDays().toString()) &&
                                            p.getSection2().getHour().equals(presentedCourse.getSection1().getHour())
                                    ){
                                        return true;
                                    }

                                    if (presentedCourse.getSection2() != null){
                                        if (
                                                p.getSection2().getDays().toString().equals(presentedCourse.getSection2().getDays().toString()) &&
                                                p.getSection2().getHour().equals(presentedCourse.getSection2().getHour())
                                        ){
                                            return true;
                                        }
                                    }

                                }

                            }
                        }
                        

                    }

                }
        }
        
        return false;
    }


    private void writeToFileStudentCourse(int courseCode, String teacherCode, Student student) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/studentListCourse.txt", true));
            String str=""+courseCode+" "+teacherCode+" "+ student.getName() + " "+student.getFamily() + " "+student.getNationalCode()+" "+student.getStudentNumber()+"\n";
            writer.append(str);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void writeToFillePresentedCourse() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/presentedCourse.txt"));
            writer.append("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/presentedCourse.txt", true));
            for (PresentedCourse p:MainApplication.presentedCourseArrayList) {
                String str;
                if (p.getSection2() == null){
                    str = p.getSection1().getDays().toString() + " " + p.getSection1().getHour() + " " + p.getCourse().getCourseCode() + " " + p.getCourse().getCourseName() + " " + p.getTeacher().getName() + " " + p.getTeacher().getFamily() + " " + p.getTeacher().getNationalCode() + " " + p.getTeacher().getTeacherCode() + " " + p.getCapacity() + "\n";
                }else {
                    str = p.getSection1().getDays().toString() + " " + p.getSection1().getHour() + " " + p.getSection2().getDays().toString() + " " + p.getSection2().getHour() + " " + p.getCourse().getCourseCode() + " " + p.getCourse().getCourseName() + " " + p.getTeacher().getName() + " " + p.getTeacher().getFamily() + " " + p.getTeacher().getNationalCode() + " " + p.getTeacher().getTeacherCode() + " " + p.getCapacity() + "\n";
                }
                writer.append(str);
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public void setData(PresentedCourse presentedCourse, int i){

        id.setText(""+i);

        if (presentedCourse.getSection2() == null){

            CoursInfo.setText(
                    "course name: " + presentedCourse.getCourse().getCourseName() + "\n" +
                            "course code: " + presentedCourse.getCourse().getCourseCode() + "\n" +
                            "teacher: " + presentedCourse.getTeacher().getName() + " " +
                            "" + presentedCourse.getTeacher().getFamily() + "\n" +
                            "Capacity: " + presentedCourse.getCapacity() + "\n" +
                            "section 1 day: " + presentedCourse.getSection1().getDays().toString()+ "\n"+
                            "section 1 hour: " + presentedCourse.getSection1().getHour()+ "\n"
            );

        }else {
            CoursInfo.setText(
                    "course name: " + presentedCourse.getCourse().getCourseName() + "\n" +
                            "course code: " + presentedCourse.getCourse().getCourseCode() + "\n" +
                            "teacher: " + presentedCourse.getTeacher().getName() + " " +
                            "" + presentedCourse.getTeacher().getFamily() + "\n" +
                            "Capacity: " + presentedCourse.getCapacity() + "\n" +
                            "section 1 day: " + presentedCourse.getSection1().getDays().toString()+ "\n"+
                            "section 1 hour: " + presentedCourse.getSection1().getHour()+ "\n" +
                            "section 2 day: " + presentedCourse.getSection2().getDays().toString()+ "\n"+
                            "section 2 hour: " + presentedCourse.getSection2().getHour()+ "\n"
            );
        }
    }


}
