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
    private String fuelType;
    private int kmTravelled;
    private double fuelefficiency;
    private double price;
    private String gearType;
    private String features;
    private String status;

    public Car() {}

    public Car(int id, String brand, String serialNumber, String model, String color, String trimLevel, int steelPrice, double registrationTax, double emission, boolean damaged, boolean ds, String licensePlate, String fuelType, int kmTravelled, double fuelefficiency, double price, String gearType, String features, String status) {
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
        this.fuelType = fuelType;
        this.kmTravelled = kmTravelled;
        this.fuelefficiency = fuelefficiency;
        this.price = price;
        this.gearType = gearType;
        this.features = features;
        this.status = status;
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
    public String getFuelType() {
        return fuelType;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    public int getKmTravelled() {
        return kmTravelled;
    }
    public void setKmTravelled(int kmTravelled) {
        this.kmTravelled = kmTravelled;
    }
    public double getFuelefficiency() {
        return fuelefficiency;
    }
    public void setFuelefficiency(double fuelefficiency) {
        this.fuelefficiency = fuelefficiency;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getGearType() {
        return gearType;
    }
    public void setGearType(String gearType) {
        this.gearType = gearType;
    }
    public String getFeatures() {
        return features;
    }
    public void setFeatures(String features) {
        this.features = features;
    }
}
