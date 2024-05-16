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
    private CarStatus status;
    private boolean ds;
    private String licensePlate;
    private FuelType fuelType;
    private double kmTraveled;
    private String fuelEfficiency;
    private double price;
    private boolean manual;


    public Car() {}

    public Car(int id, String brand, String serialNumber, String model, String color, String trimLevel,
               int steelPrice, double registrationTax, double emission, CarStatus status, boolean ds,
               String licensePlate, FuelType fuelType, double kmTraveled, String fuelEfficiency, double price,
               boolean manual) {

        this.id = id;
        this.brand = brand;
        this.serialNumber = serialNumber;
        this.model = model;
        this.color = color;
        this.trimLevel = trimLevel;
        this.steelPrice = steelPrice;
        this.registrationTax = registrationTax;
        this.emission = emission;
        this.status = status;
        this.ds = ds;
        this.licensePlate = licensePlate;
        this.fuelType = fuelType;
        this.kmTraveled = kmTraveled;
        this.fuelEfficiency = fuelEfficiency;
        this.price = price;
        this.manual = manual;
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

    public void setSteelPrice(int steelPrice) {
        this.steelPrice = steelPrice;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
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

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getKmTraveled() {
        return kmTraveled;
    }

    public void setKmTraveled(double kmTraveled) {
        this.kmTraveled = kmTraveled;
    }

    public String getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(String fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }
}
