package com.example.management;

import com.example.management.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;


public class MainApplication extends Application {

    public static int indexStudent=-1;
    public static PresentedCourse presentedCourseTeacher = new PresentedCourse();
    public static Student studentLogin = new Student("","","","");
    public static Teacher teacherLogin = new Teacher("","","","");
    public static EditStudent editStudent = new EditStudent();
    public static EditTeacher editTeacher = new EditTeacher();
    public static ArrayList<Student>studentArrayList = new ArrayList<>();
    public static ArrayList<Teacher>teacherArrayList = new ArrayList<>();
    public static ArrayList<Course>courseArrayList = new ArrayList<>();

    public static ArrayList<Section>sectionArrayList = new ArrayList<>();

    public static ArrayList<PresentedCourse>presentedCourseArrayList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {

        readFromFile();

//        for (PresentedCourse p:presentedCourseArrayList) {
//            if (p.getSection2() == null){
//                System.out.println(p.toString());
//            }else {
//                System.out.println(p.toString1());
//            }
//
//        }
//
//        for (PresentedCourse p:presentedCourseArrayList) {
//            System.out.println(p.getCourse().getCourseName());
//            for (Student s:p.studentList) {
//                System.out.println(s.toString());
//            }
//        }

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static void change_page(Button button,String fxml_path,String title){
        try {
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            AnchorPane root = FXMLLoader.load(MainApplication.class.getResource(fxml_path));
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void readFromFile(){
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/presentedCourse.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[]s = line.split(" ");

                if (s.length == 11){

                    Days d1 = switch (s[0]) {
                        case "Monday" -> Days.Monday;
                        case "Tuesday" -> Days.Tuesday;
                        case "Wednesday" -> Days.Wednesday;
                        case "Thursday" -> Days.Thursday;
                        case "Friday" -> Days.Friday;
                        case "Saturday" -> Days.Saturday;
                        default -> Days.Sunday;
                    };
                    Section section1 = new Section(d1,s[1]);

                    Days d2 = switch (s[2]) {
                        case "Monday" -> Days.Monday;
                        case "Tuesday" -> Days.Tuesday;
                        case "Wednesday" -> Days.Wednesday;
                        case "Thursday" -> Days.Thursday;
                        case "Friday" -> Days.Friday;
                        case "Saturday" -> Days.Saturday;
                        default -> Days.Sunday;
                    };
                    Section section2 = new Section(d2,s[3]);

                    Course course = new Course(Integer.parseInt(s[4]),s[5]);
                    Teacher teacher = new Teacher(s[6],s[7],s[8],s[9]);
                    int capacity = Integer.parseInt(s[10]);

                    PresentedCourse presentedCourse = new PresentedCourse(section1,section2,course,teacher,capacity);
                    presentedCourseArrayList.add(presentedCourse);

                }else if (s.length == 9){

                    Days d1 = switch (s[0]) {
                        case "Monday" -> Days.Monday;
                        case "Tuesday" -> Days.Tuesday;
                        case "Wednesday" -> Days.Wednesday;
                        case "Thursday" -> Days.Thursday;
                        case "Friday" -> Days.Friday;
                        case "Saturday" -> Days.Saturday;
                        default -> Days.Sunday;
                    };
                    Section section1 = new Section(d1,s[1]);
                    Course course = new Course(Integer.parseInt(s[2]),s[3]);
                    Teacher teacher = new Teacher(s[4],s[5],s[6],s[7]);
                    int capacity = Integer.parseInt(s[8]);

                    presentedCourseArrayList.add(new PresentedCourse(section1,course,teacher,capacity));
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/studentListCourse.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[]s = line.split(" ");

                for (PresentedCourse p:presentedCourseArrayList) {
                    if (
                            p.getCourse().getCourseCode() == Integer.parseInt(s[0]) &&
                            p.getTeacher().getTeacherCode().equals(s[1])
                    ){
                        p.studentList.add(new Student(s[2],s[3],s[4],s[5]));
                    }

                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/section.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[]s = line.split(" ");

                Days d = switch (s[0]) {
                    case "Monday" -> Days.Monday;
                    case "Tuesday" -> Days.Tuesday;
                    case "Wednesday" -> Days.Wednesday;
                    case "Thursday" -> Days.Thursday;
                    case "Friday" -> Days.Friday;
                    case "Saturday" -> Days.Saturday;
                    default -> Days.Sunday;
                };
                Section section = new Section(d,s[1]);
                sectionArrayList.add(section);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/student.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[]s = line.split(" ");

                Student student = new Student(s[0],s[1],s[2],s[3]);
                studentArrayList.add(student);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            reader = new BufferedReader(new FileReader("src/main/resources/teacher.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[]s = line.split(" ");
                Teacher student = new Teacher(s[0],s[1],s[2],s[3]);
                teacherArrayList.add(student);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/course.txt"));
            String line = reader.readLine();

            while (line != null) {

                String[]s = line.split(" ");
                Course course;
                switch (s[2]) {
                    case "Specialized" -> course = new SpecializedCourse(Integer.parseInt(s[0]), s[1]);
                    case "General" -> course = new GeneralCourse(Integer.parseInt(s[0]), s[1]);
                    default -> course = new OptionalCourse(Integer.parseInt(s[0]), s[1]);
                }

                courseArrayList.add(course);

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        for (Student s:studentArrayList
//             ) {
//            System.out.println(s.toString());
//        }
//
//        for (Teacher t:teacherArrayList
//             ) {
//            System.out.println(t.toString());
//        }
//
//        for (Course t:courseArrayList
//        ) {
//            System.out.println(t.toString());
//        }

    }

}