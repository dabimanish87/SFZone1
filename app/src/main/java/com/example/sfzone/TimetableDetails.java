package com.example.sfzone;

public class TimetableDetails {
    private int id;
    private String file_name, file_url, file_icon;

    TimetableDetails(int id, String file_name, String file_url, String file_icon){
        this.id=id;
        this.file_name=file_name;
        this.file_url=file_url;
        this.file_icon=file_icon;
    }

    TimetableDetails() {

    }

    public int getId(){
        return  id;
    }

    public String getFile_Name(){
        return  file_name;
    }

    public String getFile_URL(){
        return  file_url;
    }

    public String getFile_Icon(){
        return  file_icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public void setFile_icon(String file_icon) {
        this.file_icon = file_icon;
    }
}
