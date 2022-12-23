package com.example.management.controller;

import com.example.management.MainApplication;
import com.example.management.model.Days;
import com.example.management.model.Section;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSectionController implements Initializable {

    @FXML
    private Label txtError;


    @FXML
    private Button btnBack;

    @FXML
    private ChoiceBox<String> choiceDay;
    private String[]days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private String dayTypeSelect="";
    public void getDayType(ActionEvent event){
        dayTypeSelect = choiceDay.getValue();
    }

    @FXML
    private ChoiceBox<String> choiceHour;
    private String[]hours = {"6-8","8-10","10-12","12-14","14-16","16-18"};
    private String hourTypeSelect="";
    public void getHourType(ActionEvent event){
        hourTypeSelect = choiceHour.getValue();
    }

    @FXML
    void btnSubmit(ActionEvent event) {

        txtError.setTextAlignment(TextAlignment.CENTER);

        if (hourTypeSelect.equals("") || dayTypeSelect.equals("")){
            txtError.setText("day and hour must choose");
        }else {


            Days d = switch (dayTypeSelect) {
                case "Monday" -> Days.Monday;
                case "Tuesday" -> Days.Tuesday;
                case "Wednesday" -> Days.Wednesday;
                case "Thursday" -> Days.Thursday;
                case "Friday" -> Days.Friday;
                case "Saturday" -> Days.Saturday;
                default -> Days.Sunday;
            };


            Section section = new Section(d,hourTypeSelect);

            if (!existInSectionlist(section)){

                MainApplication.sectionArrayList.add(section);

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/section.txt",
                            true));
                    writer.append(String.valueOf(d)).append(" ").append(hourTypeSelect).append("\n");
                    writer.close();

                }catch (Exception e){
                    e.printStackTrace();
                }

                txtError.setText("section added");

            }else {
                txtError.setText("this section was exist");
            }





        }

    }

    private boolean existInSectionlist(Section section) {

        for (Section s:MainApplication.sectionArrayList) {

            if (s.getDays().equals(section.getDays()) &&
                    s.getHour().equals(section.getHour())){
                return true;
            }
        }

        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceHour.getItems().addAll(hours);
        choiceHour.setOnAction(this::getHourType);

        choiceDay.getItems().addAll(days);
        choiceDay.setOnAction(this::getDayType);
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        MainApplication.change_page(btnBack,"/com/example/management/adminPage.fxml","admin page");

    }

}
