package com.example.gruppe1eksamensprojekt.model;


//Youssef
public class Car {
    private int id;
    private String brand;
    private String serialNumber;
    private String model;
    private String color;
    private String trimLevel;
    private int steelPrice;
    private double registrationTax;
    private double emission;
    private boolean damaged;
    private boolean ds;
    private String licensePlate;

    public Car() {}

    public Car(int id, String brand, String serialNumber, String model, String color, String trimLevel, int steelPrice, double registrationTax, double emission, boolean damaged, boolean ds, String licensePlate) {
        this.id = id;
        this.brand = brand;
        this.serialNumber = serialNumber;
        this.model = model;
        this.color = color;
        this.trimLevel = trimLevel;
        this.steelPrice = steelPrice;
        this.registrationTax = registrationTax;
        this.emission = emission;
        this.damaged = damaged;
        this.ds = ds;
        this.licensePlate = licensePlate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getTrimLevel() {
        return trimLevel;
    }
    public void setTrimLevel(String trimLevel) {
        this.trimLevel = trimLevel;
    }
    public int getSteelPrice() {
        return steelPrice;
    }
    public double getRegistrationTax() {
        return registrationTax;
    }
    public void setRegistrationTax(double registrationTax) {
        this.registrationTax = registrationTax;
    }
    public double getEmission() {
        return emission;
    }
    public void setEmission(double emission) {
        this.emission = emission;
    }
    public boolean isDamaged() {
        return damaged;
    }
    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }
    public boolean isDs() {
        return ds;
    }
    public void setDs(boolean ds) {
        this.ds = ds;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}
