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

        List<Car> carList = carService.getRented();
        model.addAttribute("carlist", carList);

        List<Car> pendingCarList = carService.getDamagedCars();
        model.addAttribute("pendingcarlist", pendingCarList);

        return "damagehome";
    }

    @GetMapping("/reports")
    public String retrieveAllReports(HttpSession session, Model model) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        model.addAttribute("listOfReports", reportService.getAll());

        return "overviewReports";
    }

    //Husk at opdaterer i klassediagram
    @GetMapping("/create")
    public String createReport(HttpSession session, Model model) {
        if(session.getAttribute("user")==null) {
            return "frontpage";
        }
        List<Car> cars = carService.getDamagedCars();
        model.addAttribute("cars", cars);
        List<RentalCustomerJoin> rentalList = rentalCustomerJoinService.getAll();
        model.addAttribute("rentalList", rentalList);

        return "damageform";
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
                                HttpSession session, Model model,
                                RedirectAttributes redirectAttributes) {


        return reportService.submitReport(rental, reportTitle, reportDate, treatment, comment, report0damage, report1damage, report2damage, report3damage, report4damage, report0price, report1price,report2price, report3price, report4price, status, redirectAttributes, model);
    }

    //Opdater i klassediagram
    @GetMapping("/updateForm/{id}")
    public String showUpdateReportForm(@PathVariable("id") int reportId, HttpSession session, Model model) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        Report report = reportService.getReportById(reportId);

        model.addAttribute("report", report);

        return "reportUpdateForm";

    }
    // opdater i klassediagram
    @PostMapping("/updateReport")
    public String updateReport(@RequestParam("id") int reportId,
                               @RequestParam("title") String title,
                               @RequestParam("date") LocalDate date,
                               @RequestParam("comment") String description,
                               @RequestParam("treatment") String treatment,
                               HttpSession session, Model model) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        Report report = reportService.getReportById(reportId);

        if (report != null) {
            report.setTitle(title);
            report.setDate(date);
            report.setComment(description);
            report.setTreatment(treatment);

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

    @GetMapping("/ubehandledeBiler")
    public String showDamagedCars(HttpSession session, Model model) {

        if(session.getAttribute("user")== null) {
            return "frontpage";
        }

        model.addAttribute("listOfDamagedCars", carService.getDamagedCars());

        return "damageView";
    }





}
