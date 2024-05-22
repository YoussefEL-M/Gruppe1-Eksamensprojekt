package com.example.gruppe1eksamensprojekt.model;

public class Damages {
    private int id;
    private int reportId;
    private String damage;
    private double price;

    public Damages() {}

    public Damages(int id, int reportId, String damage, double price) {
        this.id = id;
        this.reportId = reportId;
        this.damage = damage;
        this.price = price;
    }
    public String getDamage() {
        return damage;
    }
    public void setDamage(String damage) {
        this.damage = damage;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getReportId() {
        return reportId;
    }
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

}
