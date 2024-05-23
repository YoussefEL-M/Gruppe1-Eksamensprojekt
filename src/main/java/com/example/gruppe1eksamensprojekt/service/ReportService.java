package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.model.CarStatus;
import com.example.gruppe1eksamensprojekt.model.Damages;
import com.example.gruppe1eksamensprojekt.model.Rental;
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

        List<Report> reports = reportRepo.getAll();
        populateDamages(reports);
        return reports;
    }

    public List<Report> getYourReports(int userId) {
        List<Report> reports = reportRepo.getYourReports(userId);
        populateDamages(reports);
        return reports;
    }

    public void createReport(Report report){
        reportRepo.create(report);
    }

    //Opdater i klassediagram
    public Report getReportById(int id){

        Report report;

        try {
            report = reportRepo.getReportById(id);
        } catch (EmptyResultDataAccessException ERDA) {

            return null;
        }
        if(report.getId() == id) {
            return report;
        }
        return null;
    }

    public void updateReport(Report report){
        reportRepo.update(report);
        reportRepo.deleteDamagesByReportId(report.getId());
        createDamages(report);



    }

    public void deleteReport(int id){
        reportRepo.delete(id);
    }


    //Bjarke
    public void createDamages(Report report) {
        //looper over damages som gemmes i repo
        for (var entry : report.getDamages().entrySet()){
            reportRepo.createDamage(report.getId(), entry.getKey(), entry.getValue());

        }

    }

    //Bjarke
    public int lastId(){return reportRepo.lastId(); }

    //Bjarke
    public String submitReport(String rental, int user_id, String reportTitle, LocalDate reportDate, String comment, String treatment, String report0damage, String report1damage, String report2damage, String report3damage, String report4damage,  String report0price,   String report1price,  String report2price,  String report3price,  String report4price, String status, RedirectAttributes redirectAttributes){
        boolean error = false;
        int rentalId = 0;

        try{
            rentalId = Integer.parseInt(rental.split("\\.")[0]);
            rentalRepo.getRentalById(rentalId);
        } catch (EmptyResultDataAccessException | NumberFormatException E){
            redirectAttributes.addFlashAttribute("rentalNotFound", true);
            error = true;
        }
        //Hvis der blev givet et forket rental input, puttes værdierne i flash attributer og man redirectes tilbage til formen
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
            if (!report0damage.isEmpty() && !report0price.isEmpty()) damages.put(report0damage,Double.valueOf(report0price));
            if (!report1damage.isEmpty() && !report1price.isEmpty()) damages.put(report1damage,Double.valueOf(report1price));
            if (!report2damage.isEmpty() && !report2price.isEmpty()) damages.put(report2damage,Double.valueOf(report2price));
            if (!report3damage.isEmpty() && !report3price.isEmpty()) damages.put(report3damage,Double.valueOf(report3price));
            if (!report4damage.isEmpty() && !report4price.isEmpty()) damages.put(report4damage,Double.valueOf(report4price));

            Report report = new Report(rentalId, user_id, reportTitle, reportDate, treatment, comment, damages);

            createReport(report);

            report.setId(lastId());
            createDamages(report);

            int carid=rentalService.getRentalById(rentalId).getCarId();
            Car newcar =carService.getCarById(carid);
            newcar.setStatus(CarStatus.valueOf(status));
            newcar.setLastUpdated(LocalDate.now());
            carService.updateCar(newcar);

            Rental newRental = rentalService.getRentalById(rentalId);
            newRental.setStatus("FINISHED");
            rentalService.updateRental(newRental);

            return "redirect:/damage";
        }

        return "redirect:/create";
    }

    public List<Damages> getDamagesByReportID(int id){
        return reportRepo.getDamagesByReportId(id);
    }

    public void populateDamages(List<Report> reports) {
        for (Report report : reports) {
            List<Damages> damagesList = reportRepo.getDamagesByReportId(report.getId());
            Map<String, Double> damagesMap = new HashMap<>();
            for (Damages damage : damagesList) {
                damagesMap.put(damage.getDamage(), damage.getPrice());
            }
            report.setDamages(damagesMap);
        }
    }

    public void populateDamages(Report report) {

            List<Damages> damagesList = reportRepo.getDamagesByReportId(report.getId());
            Map<String, Double> damagesMap = new HashMap<>();
            for (Damages damage : damagesList) {
                damagesMap.put(damage.getDamage(), damage.getPrice());
            }
            report.setDamages(damagesMap);

    }

}
