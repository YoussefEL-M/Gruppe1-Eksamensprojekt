package com.example.gruppe1eksamensprojekt.controller;

import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.model.UserTypes;
import com.example.gruppe1eksamensprojekt.service.CarService;
import com.example.gruppe1eksamensprojekt.service.CustomerSevice;
import com.example.gruppe1eksamensprojekt.service.RentalService;
import com.example.gruppe1eksamensprojekt.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RentalController { // Severin
    // Todo: gennemgå metodernes Models og sørg for at deres attributter er navngivet korrekt.

    @Autowired
    UserService userService;

    @Autowired
    RentalService rentalService;

    @Autowired
    CustomerSevice customerSevice;

    @Autowired
    CarService carService;

    @GetMapping("/")
    public String login(){
        // Burde måske omdirigere til /rental, hvis brugeren er logget ind.
        return "frontpage";
    }

    @PostMapping("/loggingIn")
    public String loggingIn(HttpSession session, Model model, @RequestParam("username") String username, @RequestParam("password") String password){

        User user = userService.login(username, password, model);
        // Todo: evt. tilføj funktionalitet der sender brugeren til en bestemt side alt efter brugertype.
        if(user.getType() == UserTypes.DATAREGISTRERING)
            return "redirect:/rental";
        else if (user.getType() == UserTypes.SKADE_OG_UDBEDRING)
            return "redirect:/skadeOgUdbedring";
        else if(user.getType() == UserTypes.FORRETNINGSUDVIKLER)
            return "redirect:/forretningsudvikler";
        session.setAttribute("user", user);
        return "login";
    }

    @GetMapping("/rental")
    public String rental(HttpSession session, Model model){
        // Todo: evt. rettigheder pr. bruger type.
        if(session.getAttribute("user")!=null)
            return "dataregistration";
        return "frontpage";
    }

    @GetMapping("/findRental")
    public String findRental(HttpSession session, Model model){
        model.addAttribute("rentalList", rentalService.getAll());
        return "overviewRentals";
    }

    @GetMapping("/update")
    public String updateRental(@RequestParam("id") int id, HttpSession session){
        // Todo: mangler parametre; afventer frontend.
        return "redirect:/findRental";
    }

    @PostMapping("/delete")
    public String deleteRental(@RequestParam("id") int id, HttpSession session){
        // Jeg antager at metoden skal have en id som parameter; er ikke med på klassediagrammet.
        rentalService.deleteRental(id);
        return "redirect:/findRental";
    }
}
