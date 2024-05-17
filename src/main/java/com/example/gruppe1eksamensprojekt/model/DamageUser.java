package com.example.gruppe1eksamensprojekt.model;

public class DamageUser extends User {

    public DamageUser(String name, String username, String password, String email, String type){
        super(name, username, password, email, type);
    }

    public DamageUser(){}
    public String getFrontPage(){
        return "/damage";
    }
}
