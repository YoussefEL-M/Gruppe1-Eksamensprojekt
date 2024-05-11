package com.example.gruppe1eksamensprojekt.controller;

import com.example.gruppe1eksamensprojekt.model.Report;
import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

// Clara

@Controller
public class DamageController {


    //Noter i klassediagram
    @Autowired
    private ReportService reportService;



    //Ny metode noter i klassediagram
    @GetMapping("/skadeOgUdbedring")
    public String skadeUdbedringsforside(HttpSession session) {
        if(session.getAttribute("user")!= null)
            return "sideForSkadeOgUdbedring";
        return "frontpage";
    }

    @GetMapping("/rapporter")
    public String retrieveAllReports(Model model) {

        model.addAttribute("listOfReports", reportService.getAll());

        return "rapportside";
    }

    @GetMapping("/opret")
    public String createReport() {

        return "opretRapport";
    }


    //Ny metode noter i klassediagram
    @PostMapping("/opretRapport")
    public String createAReport(@RequestParam("rentalId") int idForRental,
                                @RequestParam("title") String reportTitle,
                                @RequestParam("date")LocalDate reportDate,
                                RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("rentalId", idForRental);
        redirectAttributes.addAttribute("title", reportTitle);
        redirectAttributes.addAttribute("date", reportDate);

        Report report = new Report(idForRental, reportTitle, reportDate);

        reportService.createReport(report);

        return "redirect:/rapportside";
    }






}
