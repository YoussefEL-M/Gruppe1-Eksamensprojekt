package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Report;
import com.example.gruppe1eksamensprojekt.repository.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

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

    //Opdater i klassediagram
    public Report getReportById(int id, Model model){

        Report report;

        try {
            report = reportRepo.getReportById(id);
        } catch (EmptyResultDataAccessException ERDA) {

            model.addAttribute("unableToFindReport", true);
            return null;
        }
        if(report.getId() == id) {
            return report;
        }
        return null;
    }

    public void updateReport(Report report){
        reportRepo.update(report);
    }

    public void deleteReport(int id){
        reportRepo.delete(id);
    }

}
