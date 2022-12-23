package com.example.management.model;

public class Section {

    private Days days;
    private String hour;

    public Section(Days days, String hour) {
        this.days = days;
        this.hour = hour;
    }

    public Days getDays() {
        return days;
    }

    public void setDays(Days days) {
        this.days = days;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Section{" +
                "days=" + days +
                ", hour='" + hour + '\'' +
                '}';
    }
}
