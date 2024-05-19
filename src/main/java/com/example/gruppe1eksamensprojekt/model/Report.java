package com.example.gruppe1eksamensprojekt.model;

import java.time.LocalDate;

public class Report {
    private int id;
    private int rentalId;
    private String title;
    private LocalDate date;
    private String comment;
    private String treatment;
    private LocalDate lastUpdated;

    public Report() {}

    public Report(int rentalId, String title, LocalDate date, String comment, String treatment, LocalDate lastUpdated) {
        this.rentalId = rentalId;
        this.title = title;
        this.date = date;
        this.comment = comment;
        this.treatment = treatment;
        this.lastUpdated = lastUpdated;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public String getTreatment() {
        return treatment;
    }
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
