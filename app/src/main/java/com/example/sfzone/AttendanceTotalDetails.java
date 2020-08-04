package com.example.sfzone;

public class AttendanceTotalDetails {

    private String sub_name;
    private int total_days;

    AttendanceTotalDetails(String sub_name, int total_days){
        this.total_days = total_days;
        this.sub_name = sub_name;
    }

    AttendanceTotalDetails(){}

    public int getTotal_Days(){
        return  total_days;
    }

    public String getSub_Name(){
        return  sub_name;
    }

}
