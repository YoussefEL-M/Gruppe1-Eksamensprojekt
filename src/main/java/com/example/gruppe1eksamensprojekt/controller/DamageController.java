package com.example.gruppe1eksamensprojekt.controller;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.model.CarStatus;
import com.example.gruppe1eksamensprojekt.model.Report;
import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.service.CarService;
import com.example.gruppe1eksamensprojekt.service.RentalService;
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
                                @RequestParam("comment") String comment,
                                @RequestParam("report0damage") String report0damage,
                                @RequestParam("report0price") double report0price,
                                @RequestParam("report1damage") String report1damage,
                                @RequestParam("report1price") double report1price,
                                @RequestParam("report2damage") String report2damage,
                                @RequestParam("report2price") double report2price,
                                @RequestParam("report3damage") String report3damage,
                                @RequestParam("report3price") double report3price,
                                @RequestParam("report4damage") String report4damage,
                                @RequestParam("report4price") double report4price,
                                @RequestParam("status") String status,
                                HttpSession session) {

        if(session.getAttribute("user")==null)
            return "frontpage";

        Map<String, Double> damages = new HashMap<>();
        if (!report0damage.isEmpty()) damages.put(report0damage,report0price);
        if (!report1damage.isEmpty()) damages.put(report1damage,report1price);
        if (!report2damage.isEmpty()) damages.put(report2damage,report2price);
        if (!report3damage.isEmpty()) damages.put(report3damage,report3price);
        if (!report4damage.isEmpty()) damages.put(report4damage,report4price);

       Report report = new Report(idForRental, reportTitle, reportDate, treatment, comment, damages);

       reportService.createReport(report);

       report.setId(reportService.lastId());
       reportService.createDamages(report);

        Car newcar = carService.getCarById(rentalService.getRentalById(idForRental).getCarId());
        newcar.setStatus(CarStatus.valueOf(status));
        carService.updateCar(newcar);
        return "redirect:/reports";
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
