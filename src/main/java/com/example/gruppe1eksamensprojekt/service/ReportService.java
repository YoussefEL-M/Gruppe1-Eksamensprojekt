package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Report;
import com.example.gruppe1eksamensprojekt.repository.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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


    public String submitReport(int idForRental, String reportTitle, LocalDate reportDate, String treatment, String comment, String report0damage, String report1damage, String report2damage, String report3damage, String report4damage,  int report0price,   int report1price,  int report2price,  int report3price,  int report4price, RedirectAttributes redirectAttributes){
        boolean error = false;
        int customerId = 0;
        int carId = 0;

        try{
            customerId = Integer.parseInt(customer.split("\\.")[0]);
            customerRepo.getCustomerById(customerId);
        } catch (EmptyResultDataAccessException | NumberFormatException E){
            redirectAttributes.addFlashAttribute("customerNotFound", true);
            error = true;
        }
        // Kan der være behov for at registrere en kontrakt efter lejeperioden begynder?
        if(LocalDate.parse(startDate).isBefore(LocalDate.now())) {
            redirectAttributes.addFlashAttribute("timeTravelException", true);
            error = true;
        }


        try{
            carId = Integer.parseInt(car.split("\\.")[0]);
            if(!carRepo.getCarById(carId).getStatus().equals(CarStatus.AVAILABLE)){
                redirectAttributes.addFlashAttribute("carAlreadyRented", true);
                error = true;
            }
        } catch (EmptyResultDataAccessException | NumberFormatException E){
            redirectAttributes.addFlashAttribute("carNotFound", true);
            error = true;
        }

        // Hvis type ikke er Limited, sæt type lig antal måneder valgt til Unlimited.
        if(!type.equals("5")) type = unlimitedMonth;
        if(type.isEmpty()) type = "0";
        if(3>Integer.parseInt(type)||Integer.parseInt(type)>36) {
            redirectAttributes.addFlashAttribute("typeError", true);
            error = true;
        }


        if(error) {
            redirectAttributes.addFlashAttribute("customer", customer);
            redirectAttributes.addFlashAttribute("startDate", startDate);
            redirectAttributes.addFlashAttribute("pickuppoint", pickuppoint);
            redirectAttributes.addFlashAttribute("car", car);
            redirectAttributes.addFlashAttribute("type", Integer.parseInt(type));
            redirectAttributes.addFlashAttribute("dropoffpoint", dropoffpoint);
            redirectAttributes.addFlashAttribute("unlimitedMonth", unlimitedMonth);
        } else{
            String endDate = calcEndDate(startDate,type);
            Car carToUpdate = carRepo.getCarById(carId);
            carToUpdate.setStatus(CarStatus.RENTED);
            carRepo.update(carToUpdate);
            Rental rental = new Rental(pickuppoint, dropoffpoint, type, customerId, startDate, endDate, carId, false);
            rentalRepo.create(rental);
            return "redirect:/rental";
        }

        return "redirect:/createRental";
    }


}
