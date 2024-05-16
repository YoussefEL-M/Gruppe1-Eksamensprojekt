package com.example.gruppe1eksamensprojekt.model;


//Youssef
public class User {

    private String name;
    private String username;
    private String password;
    private String email;
    private int id;
    private String type;


    public User() {}
    public User(String name, String username, String password, String email, String type) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
