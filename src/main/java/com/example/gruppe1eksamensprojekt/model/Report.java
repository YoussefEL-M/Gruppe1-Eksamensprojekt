package com.example.gruppe1eksamensprojekt.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
    private int id;
    private int rentalId;
    private String title;
    private LocalDate date;
    private String comment;
    private String treatment;
    private LocalDate lastUpdated;
    private Map<String, Double> damages;


    public Report(){
    }
    public Report(int id, int rentalId, String title, LocalDate date, String comment, String treatment) {
        this.id=id;
        this.rentalId = rentalId;
        this.title = title;
        this.date = date;
        this.comment = comment;
        this.treatment = treatment;

    }
    public Report(int rentalId, String title, LocalDate date, String comment, String treatment, Map<String, Double> damages) {
        this.rentalId = rentalId;
        this.title = title;
        this.date = date;
        this.comment = comment;
        this.treatment = treatment;
        this.damages = damages;

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
    public Map<String, Double> getDamages() {
        return damages;
    }
    public void setDamages(Map<String, Double> damages) {
        this.damages = damages;
    }
}
