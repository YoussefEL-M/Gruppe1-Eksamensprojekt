package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Report;
import com.example.gruppe1eksamensprojekt.repository.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportService { // Severin

    @Autowired
    private ReportRepo reportRepo;

    public List<Report> getAll(){
        return reportRepo.getAll();
    }

    public void createReport(Report report){
        reportRepo.create(report);
    }

    public Report getReportById(int id){
        return reportRepo.getReportById(id);
    }

    public void updateReport(Report report){
        reportRepo.update(report);
    }

    public void deleteReport(int id){
        reportRepo.delete(id);
    }

    public void createDamages(Report report) {
        for (var entry : report.getDamages().entrySet()){
            reportRepo.createDamage(report.getId(), entry.getKey(), entry.getValue());

        }

    }

    public int lastId(){return reportRepo.lastId(); }

}
