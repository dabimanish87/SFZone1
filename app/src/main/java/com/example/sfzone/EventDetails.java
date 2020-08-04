package com.example.sfzone;

public class EventDetails {
    private int id;
    private String name, place, date, time;

    EventDetails(int id, String name, String place, String date, String time){
        this.id=id;
        this.name=name;
        this.place=place;
        this.date=date;
        this.time=time;
    }

    EventDetails() {

    }

    public int getId(){
        return  id;
    }

    public String getName(){
        return  name;
    }

    public String getPlace(){
        return  place;
    }

    public String getDate(){
        return  date;
    }

    public String getTime(){
        return  time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPlace(String place){
        this.place = place;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setTime(String time){
        this.time = time;
    }

}
