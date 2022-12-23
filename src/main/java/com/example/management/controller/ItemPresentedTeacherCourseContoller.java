package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.PresentedCourse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItemPresentedTeacherCourseContoller {

    @FXML
    private Label CoursInfo;

    @FXML
    private Button btnchoiceCourse;

    @FXML
    private Label id;

    @FXML
    void btnchoiceCourseAction(ActionEvent event) {

        String info = id.getText();
        String[] in = info.split("-");
        int courseCode = Integer.parseInt(in[0]);
        String teacherCode = in[1];

        for (PresentedCourse p: MainApplication.presentedCourseArrayList) {
            if (p.getCourse().getCourseCode() == courseCode && p.getTeacher().getTeacherCode().equals(teacherCode)){
                //1
                MainApplication.presentedCourseTeacher = p;
                break;
            }
        }

        MainApplication.change_page(btnchoiceCourse,"/com/example/management/teacherStudentCourseList.fxml","student List for course : "+MainApplication.presentedCourseTeacher.getCourse().getCourseName());

    }

    public void setData(PresentedCourse presentedCourse, int i){
        id.setText(presentedCourse.getCourse().getCourseCode() +"-"+presentedCourse.getTeacher().getTeacherCode());

        if (presentedCourse.getSection2() == null){

            CoursInfo.setText(
                             "course name: " + presentedCourse.getCourse().getCourseName() + "\n" +
                            "course code: " + presentedCourse.getCourse().getCourseCode() + "\n" +
                            "section 1 day: " + presentedCourse.getSection1().getDays().toString()+ "\n"+
                            "section 1 hour: " + presentedCourse.getSection1().getHour()+ "\n"
            );

        }else {
            CoursInfo.setText(
                    "course name: " + presentedCourse.getCourse().getCourseName() + "\n" +
                            "course code: " + presentedCourse.getCourse().getCourseCode() + "\n" +
                            "section 1 day: " + presentedCourse.getSection1().getDays().toString()+ "\n"+
                            "section 1 hour: " + presentedCourse.getSection1().getHour()+ "\n" +
                            "section 2 day: " + presentedCourse.getSection2().getDays().toString()+ "\n"+
                            "section 2 hour: " + presentedCourse.getSection2().getHour()+ "\n"
            );
        }

    }

}
