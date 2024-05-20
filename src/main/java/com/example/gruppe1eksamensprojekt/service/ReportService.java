package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.model.CarStatus;
import com.example.gruppe1eksamensprojekt.model.Report;
import com.example.gruppe1eksamensprojekt.repository.RentalRepo;
import com.example.gruppe1eksamensprojekt.repository.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService { // Severin

    @Autowired
    private ReportRepo reportRepo;
    @Autowired
    private RentalRepo rentalRepo;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private CarService carService;

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

    public void createDamages(Report report) {
        for (var entry : report.getDamages().entrySet()){
            reportRepo.createDamage(report.getId(), entry.getKey(), entry.getValue());

        }

    }

    public int lastId(){return reportRepo.lastId(); }


    public String submitReport(String rental, String reportTitle, LocalDate reportDate, String treatment, String comment, String report0damage, String report1damage, String report2damage, String report3damage, String report4damage,  Double report0price,   Double report1price,  Double report2price,  Double report3price,  Double report4price, String status, RedirectAttributes redirectAttributes, Model model){
        boolean error = false;
        int rentalId = 0;

        try{
            rentalId = Integer.parseInt(rental.split("\\.")[0]);
            rentalRepo.getRentalById(rentalId);
        } catch (EmptyResultDataAccessException | NumberFormatException E){
            redirectAttributes.addFlashAttribute("rentalNotFound", true);
            error = true;
        }



        if(error) {
            redirectAttributes.addFlashAttribute("rental", rental);
            redirectAttributes.addFlashAttribute("reportTitle", reportTitle);
            redirectAttributes.addFlashAttribute("reportDate", reportDate);
            redirectAttributes.addFlashAttribute("treatment", treatment);
            redirectAttributes.addFlashAttribute("comment", comment);
            redirectAttributes.addFlashAttribute("report0damage", report0damage);
            redirectAttributes.addFlashAttribute("report1damage", report1damage);
            redirectAttributes.addFlashAttribute("report2damage", report2damage);
            redirectAttributes.addFlashAttribute("report3damage", report3damage);
            redirectAttributes.addFlashAttribute("report4damage", report4damage);
            redirectAttributes.addFlashAttribute("report0price", report0price);
            redirectAttributes.addFlashAttribute("report1price", report1price);
            redirectAttributes.addFlashAttribute("report2price", report2price);
            redirectAttributes.addFlashAttribute("report3price", report3price);
            redirectAttributes.addFlashAttribute("report4price", report4price);
        } else{
            Map<String, Double> damages = new HashMap<>();
            if (!report0damage.isEmpty()) damages.put(report0damage,report0price);
            if (!report1damage.isEmpty()) damages.put(report1damage,report1price);
            if (!report2damage.isEmpty()) damages.put(report2damage,report2price);
            if (!report3damage.isEmpty()) damages.put(report3damage,report3price);
            if (!report4damage.isEmpty()) damages.put(report4damage,report4price);

            Report report = new Report(rentalId, reportTitle, reportDate, treatment, comment, damages);

            createReport(report);

            report.setId(lastId());
            createDamages(report);

            int carid=rentalService.getRentalById(rentalId,model).getCarId();
            Car newcar =carService.getCarById(carid,model);
            newcar.setStatus(CarStatus.valueOf(status));
            carService.updateCar(newcar);
            return "redirect:/damage";
        }

        return "redirect:/create";
    }


}
