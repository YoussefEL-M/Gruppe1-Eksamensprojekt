package com.example.gruppe1eksamensprojekt.model;

public class DataUser extends User {

    public DataUser(String name, String username, String password, String email, String type){
        super(name, username, password, email, type);
    }

    public DataUser(){}
    public String getFrontPage(){
        return "/rental";
    }
}
