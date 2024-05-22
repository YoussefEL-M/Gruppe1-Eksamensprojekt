package com.example.gruppe1eksamensprojekt.model;

import java.time.LocalDate;

//Bjarke
public class RentalCustomerJoin {
    private String pickUpLocation;
    private String returnLocation;
    private String type;
    private int id;
    private int customerId;
    private String startDate;
    private String endDate;
    private int carId;
    private String name;
    private String telnr;
    private String address;
    private LocalDate birthdate;

    private String status;

    private int userId;


    public RentalCustomerJoin(int id, String pickUpLocation, String returnLocation, String type, int customerId, String startDate, String endDate, int carId,String name, String telnr, String address, LocalDate birthdate, String status, int userId) {
        this.id=id;
        this.pickUpLocation = pickUpLocation;
        this.returnLocation = returnLocation;
        this.type=type;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.carId = carId;
        this.name = name;
        this.telnr = telnr;
        this.address = address;
        this.birthdate = birthdate;
        this.status = status;
        this.userId = userId;

    }

    public RentalCustomerJoin(){}


    public String getPickUpLocation() {
        return pickUpLocation;
    }
    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }
    public String getReturnLocation() {
        return returnLocation;
    }
    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
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
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public int getCarId() {
        return carId;
    }
    public void setCarId(int carID) {
        this.carId = carID;
    }

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

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserID() {
        return userId;
    }
    public void setUserID(int userId) {
        this.userId = userId;
    }




}
