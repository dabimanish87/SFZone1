package com.example.sfzone;

public class AttendancePresentDetails {
    private String sub_name;
    private int total_present_days;


    AttendancePresentDetails(int total_present_days, String sub_name){
        this.total_present_days = total_present_days;
        this.sub_name = sub_name;

    }

    AttendancePresentDetails(){}

    public int getTotal_Present_Days(){
        return  total_present_days;
    }

    public String getSub_Name(){
        return  sub_name;
    }


}
