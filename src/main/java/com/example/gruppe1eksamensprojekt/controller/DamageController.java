package com.example.gruppe1eksamensprojekt.controller;

import com.example.gruppe1eksamensprojekt.model.*;
import com.example.gruppe1eksamensprojekt.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Clara

@Controller
public class DamageController {


    @Autowired
    ReportService reportService;

    @Autowired
    CarService carService;
    @Autowired
    RentalService rentalService;
    @Autowired
    RentalCustomerJoinService rentalCustomerJoinService;



    @GetMapping("/damage")
    public String skadeUdbedringsForside(HttpSession session, Model model) {
        if(session.getAttribute("user")== null) {
            return "frontpage";
        }

        List<Rental> rentals = rentalService.getCurrentRentals();
        List<Car> carList = new ArrayList<>();

        for (Rental rental : rentals){
            carList.add(carService.getCarById(rental.getCarId()));
        }

        model.addAttribute("carlist", carList);
        List<Car> pendingCarList = carService.getNotUpdated();
        model.addAttribute("pendingcarlist", pendingCarList);

        return "damagehome";
    }

    @GetMapping("/reports")
    public String retrieveAllReports(HttpSession session, Model model) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        List<Report> reports = reportService.getAll();
        model.addAttribute("listOfReports", reports);

        return "overviewReports";
    }

    //Opdater klassediagram!!
    @GetMapping("/yourReports")
    public String retrieveYourReports(HttpSession session, Model model) {

        if(session.getAttribute("user") == null) {
            return "frontpage";
        }
        User user = (User) session.getAttribute("user");
        model.addAttribute("yourReportslist", reportService.getYourReports(user.getId()));

        return "yourReports";
    }

    //Husk at opdaterer i klassediagram
    @GetMapping("/create")
    public String createReport(HttpSession session, Model model) {
        if(session.getAttribute("user")==null) {
            return "frontpage";
        }

        List<RentalCustomerJoin> rentalList = rentalCustomerJoinService.getAll();
        model.addAttribute("rentalList", rentalList);

        return "damageform";
    }

    //Bjarke
    @GetMapping("/create/{id}")
    public String create(@PathVariable("id") int id, RedirectAttributes redirectAttributes){

        RentalCustomerJoin rental = rentalCustomerJoinService.getRentalByCar(id);
        String string = rental.getId()+ ". " + rental.getName()+ " - " + rental.getEndDate()+ " - " + carService.getCarById(id).getModel();
        redirectAttributes.addFlashAttribute("rental", string);
        return "redirect:/create";
    }

    //Husk at opdaterer i klassediagram
    @PostMapping("/createReport")
    public String createAReport(@RequestParam("rental") String rental,
                                @RequestParam("title") String reportTitle,
                                @RequestParam("date")LocalDate reportDate,
                                @RequestParam("treatment") String treatment,
                                @RequestParam("comment") String comment,
                                @RequestParam("report0damage") String report0damage,
                                @RequestParam("report0price") String report0price,
                                @RequestParam("report1damage") String report1damage,
                                @RequestParam("report1price") String report1price,
                                @RequestParam("report2damage") String report2damage,
                                @RequestParam("report2price") String report2price,
                                @RequestParam("report3damage") String report3damage,
                                @RequestParam("report3price") String report3price,
                                @RequestParam("report4damage") String report4damage,
                                @RequestParam("report4price") String report4price,
                                @RequestParam("status") String status,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        int user_id = user.getId();

        return reportService.submitReport(rental, user_id, reportTitle, reportDate, treatment, comment, report0damage, report1damage, report2damage, report3damage, report4damage, report0price, report1price,report2price, report3price, report4price, status, redirectAttributes);
    }



    //Opdater i klassediagram
    @GetMapping("/updateForm/{reportId}/{rentalId}")
    public String showUpdateReportForm(@PathVariable("reportId") int reportId, @PathVariable("rentalId") int rentalId, HttpSession session, Model model) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        Report report = reportService.getReportById(reportId);
        reportService.populateDamages(report);
        List<Damages> damages = reportService.getDamagesByReportID(reportId);
        String[] damageKeys = {null, null, null, null, null};
        Double[] damageValues = {null, null, null, null, null};
        int i = 0;
        for (Damages d : damages){
            damageKeys[i]=(d.getDamage());
            damageValues[i]=(d.getPrice());
            i++;
        }


        model.addAttribute("report", report);
        model.addAttribute("rentalId",rentalId);
        model.addAttribute("damageKeys", damageKeys);
        model.addAttribute("damageValues", damageValues);


        return "reportUpdateForm";

    }


    // opdater i klassediagram
    @PostMapping("/updateReport")
    public String updateReport(@RequestParam("rentalId") int rentalId,
                                @RequestParam("reportId") int reportId,
                               @RequestParam("title") String title,
                               @RequestParam("date") LocalDate date,
                               @RequestParam("comment") String description,
                               @RequestParam("treatment") String treatment,
                               @RequestParam("report0damage") String report0damage,
                               @RequestParam("report0price") String report0price,
                               @RequestParam("report1damage") String report1damage,
                               @RequestParam("report1price") String report1price,
                               @RequestParam("report2damage") String report2damage,
                               @RequestParam("report2price") String report2price,
                               @RequestParam("report3damage") String report3damage,
                               @RequestParam("report3price") String report3price,
                               @RequestParam("report4damage") String report4damage,
                               @RequestParam("report4price") String report4price,
                               @RequestParam("status") String status,
                               HttpSession session, Model model) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        Report report = reportService.getReportById(reportId);

        if (report != null) {
            report.setTitle(title);
            report.setDate(date);
            report.setComment(description);
            report.setTreatment(treatment);



            int carid=rentalService.getRentalById(rentalId).getCarId();
            Car newcar =carService.getCarById(carid);
            newcar.setStatus(CarStatus.valueOf(status));
            newcar.setLastUpdated(LocalDate.now());
            carService.updateCar(newcar);

            Map<String, Double> damages = new HashMap<>();
            if (!report0damage.isEmpty() && !report0price.isEmpty()) damages.put(report0damage,Double.valueOf(report0price));
            if (!report1damage.isEmpty() && !report1price.isEmpty()) damages.put(report1damage,Double.valueOf(report1price));
            if (!report2damage.isEmpty() && !report2price.isEmpty()) damages.put(report2damage,Double.valueOf(report2price));
            if (!report3damage.isEmpty() && !report3price.isEmpty()) damages.put(report3damage,Double.valueOf(report3price));
            if (!report4damage.isEmpty() && !report4price.isEmpty()) damages.put(report4damage,Double.valueOf(report4price));

            report.setDamages(damages);


            reportService.updateReport(report);

        }



        return "redirect:/reports";

    }





    @GetMapping("/delete/{id}")
    public String deleteReport(@PathVariable("id") int reportId, HttpSession session) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        reportService.deleteReport(reportId);

        return "redirect:/reports";
    }

    //Bjarke
    @GetMapping("/updateStatus/{id}/{status}")
    public String updateStatus(@PathVariable("id") int id, @PathVariable("status") String status){

        Car car =carService.getCarById(id);
        car.setStatus(CarStatus.valueOf(status));
        car.setLastUpdated(LocalDate.now());

        carService.updateCar(car);

      return "redirect:/";
    }



}
