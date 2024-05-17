// Severin

package com.example.gruppe1eksamensprojekt.controller;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BusinessController {

    @Autowired
    CarService carService;

//    @GetMapping("/businesspage")
//    public String businessFrontPage(HttpSession session, Model model){
//        if(session.getAttribute("user")==null)
//            return "forside";
//        List<Car> rentedCars = carService.getRented();
//        model.addAttribute("rentedCars", rentedCars); // Skulle frontend bruge listen, eller kun antal?
//        model.addAttribute("noOfRentedCars", rentedCars.size());
//        model.addAttribute("rentedCarsTotalPrice", carService.getRentedCarsTotalPrice());
//
//        return "businessPage";
//    }
}
