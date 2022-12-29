package com.myproject.app.Classes;

import java.util.ArrayList;

public class Course{
    private String subjectName;
    private String subjectCode;
    private ArrayList<TimePeriod> schedule;
    private double price;
    private int maxSeats;
    private int availableSeats;

    public Course(){
        
    }

    public Course(String subjectName, String subjectCode, ArrayList<TimePeriod> schedule, double price, int maxSeats) {
        this.subjectName = subjectName;
        if (checkSubjectCode(subjectCode)){
            this.subjectCode = subjectCode;
        } else {
            System.out.println("ERROR: Invalid subject code, set to null");
        }
        this.schedule = schedule;
        this.price = price;
        this.maxSeats = maxSeats;
        availableSeats = maxSeats;
    }

    private boolean checkSubjectCode(String code){
        boolean bol = true;
        if (code.length() == 6){
            char[] c = code.toCharArray();
            for (int i = 0; i < c.length; i++){
                if (i <= 2){
                    if (Character.isDigit(c[i])){
                        bol = false;
                        break;
                    }
                } else {
                    if (!Character.isDigit(c[i])){
                        bol = false;
                        break;
                    }
                }
            }
        } else {
            bol = false;
        }
        return bol;
    }

    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public String getSubjectCode() {
        return subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    public ArrayList<TimePeriod> getSchedule() {
        return schedule;
    }
    public void setSchedule(ArrayList<TimePeriod> schedule) {
        this.schedule = schedule;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getMaxSeats() {
        return maxSeats;
    }
    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Course [subjectName=" + subjectName + ", subjectCode=" + subjectCode + ", schedule=" + schedule
                + ", price=" + price + ", availableSeats=" + availableSeats + "]";
    }

    
}



