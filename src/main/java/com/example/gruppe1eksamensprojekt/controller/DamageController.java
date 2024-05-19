package com.example.gruppe1eksamensprojekt.controller;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.model.CarStatus;
import com.example.gruppe1eksamensprojekt.model.Report;
import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.service.CarService;
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
import java.util.List;

// Clara

@Controller
public class DamageController {


    @Autowired
    ReportService reportService;

    @Autowired
    CarService carService;



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

        return "damageform";
    }


    //Husk at opdaterer i klassediagram
    @PostMapping("/createReport")
    public String createAReport(@RequestParam("rentalId") int idForRental,
                                @RequestParam("title") String reportTitle,
                                @RequestParam("date")LocalDate reportDate,
                                @RequestParam("treatment") String treatment,
                                @RequestParam("comment") String description,
                                @RequestParam("lastUpdated") LocalDate updateDate,
                                RedirectAttributes redirectAttributes, HttpSession session) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        redirectAttributes.addAttribute("rentalId", idForRental);
        redirectAttributes.addAttribute("title", reportTitle);
        redirectAttributes.addAttribute("date", reportDate);

        Report report = new Report(idForRental, reportTitle, reportDate, description, treatment, updateDate);

        reportService.createReport(report);

        return "redirect:/overviewReports";
    }

    //Opdater i klassediagram
    @GetMapping("/updateForm/{id}")
    public String showUpdateReportForm(@PathVariable("id") int reportId, HttpSession session, Model model) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        Report report = reportService.getReportById(reportId, model);

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

        Report report = reportService.getReportById(reportId, model);

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
