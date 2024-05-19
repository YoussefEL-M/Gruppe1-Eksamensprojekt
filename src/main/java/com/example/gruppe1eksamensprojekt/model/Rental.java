package com.example.gruppe1eksamensprojekt.model;


//Youssef
public class Rental {

    private String pickUpLocation;
    private String returnLocation;
    private String type;
    private int id;
    private int customerId;
    private String startDate;
    private String endDate;
    private int carId;
    private boolean status;
    private int userId;

    public Rental(String pickUpLocation, String returnLocation, String type, int customerId, String startDate, String endDate, int carId, boolean status, int userID) {
        this.pickUpLocation = pickUpLocation;
        this.returnLocation = returnLocation;
        this.type=type;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.carId = carId;
        this.status=status;
        this.userId=userID;

    }

    public Rental(){}

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

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUserID() {
        return userId;
    }
    public void setUserID(int userId) {
        this.userId = userId;
    }

}
