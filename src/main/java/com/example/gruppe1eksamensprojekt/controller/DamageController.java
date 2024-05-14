package com.example.gruppe1eksamensprojekt.controller;

import com.example.gruppe1eksamensprojekt.model.Report;
import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.service.ReportService;
import com.example.gruppe1eksamensprojekt.service.UserService;
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

// Clara

@Controller
public class DamageController {


    @Autowired
    ReportService reportService;



    @GetMapping("/skadeOgUdbedring")
    public String skadeUdbedringsForside(HttpSession session) {
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


    @PostMapping("/opretRapport")
    public String createAReport(@RequestParam("rentalId") int idForRental,
                                @RequestParam("title") String reportTitle,
                                @RequestParam("date")LocalDate reportDate,
                                @RequestParam("comment") String description,
                                RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("rentalId", idForRental);
        redirectAttributes.addAttribute("title", reportTitle);
        redirectAttributes.addAttribute("date", reportDate);

        Report report = new Report(idForRental, reportTitle, reportDate, description);

        reportService.createReport(report);

        return "redirect:/rapportside";
    }


    @GetMapping("/rapportside/{id}")
    public String showUpdateReportForm(@PathVariable("id") int reportId, Model model) {

        Report report = reportService.getReportById(reportId);

        model.addAttribute("report", report);

        return "redirect:/reportUpdateForm";

    }

    @PostMapping("/updateAReport")
    public String updateReport(@RequestParam("id") int reportId,
                               @RequestParam("title") String title,
                               @RequestParam("date") LocalDate date,
                               @RequestParam("comment") String description) {

        Report report = reportService.getReportById(reportId);

        report.setTitle(title);
        report.setDate(date);
        report.setComment(description);

        reportService.updateReport(report);

        return "redirect:/rapportside?id="+reportId;

    }





    @GetMapping("/rapportside/{id}")
    public String deleteReport(@PathVariable("id") int reportId) {

        reportService.deleteReport(reportId);

        return "redirect:/rapportside?id= "+reportId;
    }






}
