package com.example.sfzone;

public class UserDetails {

    private static UserDetails userDetails;
    private int id, mobile;
    private String username, password, name;

//    public UserDetails(int id, int mobile, String username, String password, String name) {
//        this.id = id;
//        this.mobile = mobile;
//        this.username = username;
//        this.password = password;
//        this.name = name;
//    }

    private UserDetails(){}

    public static synchronized UserDetails getInstance(){
        if(userDetails==null){
            userDetails=new UserDetails();
        }
        return userDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
