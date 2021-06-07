package com.moringaschool.luna;

public class User {
    public String fullname, username, email, password;

    public User (){

    }

    public User (String fullname, String username, String email, String password){
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
