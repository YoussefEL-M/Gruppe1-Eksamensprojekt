//Severin

package com.example.gruppe1eksamensprojekt.model;

public class BusinessUser extends User {

    public BusinessUser(){}

    public BusinessUser(String name, String username, String password, String email, String type){
        super(name, username, password, email, type);
    }
    public String getFrontPage(){
        return "/businesspage";
    }
}
