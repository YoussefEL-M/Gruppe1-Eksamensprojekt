package com.example.gruppe1eksamensprojekt.model;

import java.time.LocalDate;

//Youssef
public class Customer {

    private String name;
    private String telnr;
    private String address;
    private LocalDate birthdate;
    private int id;

    public Customer(String name, String telnr, String address, LocalDate birthdate, int id) {
        this.name = name;
        this.telnr = telnr;
        this.address = address;
        this.birthdate = birthdate;
        this.id = id;
    }

    public Customer(){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTelnr() {
        return telnr;
    }
    public void setTelnr(String telnr) {
        this.telnr = telnr;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
