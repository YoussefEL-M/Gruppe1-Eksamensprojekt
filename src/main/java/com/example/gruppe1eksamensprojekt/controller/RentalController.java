package com.example.gruppe1eksamensprojekt.controller;

import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.model.RentalCustomerJoin;
import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    RentalCustomerJoinService rentalCustomerJoinService;

    @GetMapping("/")
    public String login(HttpSession session, Model model){

        if(session.getAttribute("user")==null)
            return "frontpage";
        User user = (User) session.getAttribute("user");
        return "redirect:" + user.getFrontPage();
    }

    @PostMapping("/loggingIn")
    public String loggingIn(HttpSession session, Model model, @RequestParam("username") String username, @RequestParam("password") String password){
        User user = userService.login(username, password, model);
        // Todo: evt. tilføj funktionalitet der sender brugeren til en bestemt side alt efter brugertype.
        if(user != null) {
            session.setAttribute("user", user);
            return "redirect:" + user.getFrontPage();
        }
        else return "redirect:/";
    }

    @GetMapping("/rental")
    public String rental(HttpSession session, Model model){
        // Todo: evt. rettigheder pr. bruger type.
        if(session.getAttribute("user")==null)
            return "frontpage";
        return "dataregistration";
    }

    @GetMapping("/findRental")
    public String findRental(HttpSession session, Model model){
        if(session.getAttribute("user")==null)
            return "frontpage";
        model.addAttribute("rentalList", rentalCustomerJoinService.getAll());
        return "overviewRentals";
    }

    @GetMapping("/editRental")
    public String editRental(@RequestParam("id") int id, HttpSession session, Model model){
        if(session.getAttribute("user")==null)
            return "frontpage";
        model.addAttribute("rental",rentalService.getRentalById(id));

        return "rentalUpdateForm";
    }
    @PostMapping("/updateRental")
    public String updateRental(@RequestParam("id") int id, @RequestParam("endDate") String endDate, @RequestParam("returnLocation") String returnLocation, @RequestParam("carId") int carId, HttpSession session){
        if(session.getAttribute("user")==null)
            return "frontpage";

        Rental rental = rentalService.getRentalById(id);
        rental.setEndDate(endDate);
        rental.setReturnLocation(returnLocation);
        rental.setCarId(carId);
        rentalService.updateRental(rental);
        return "redirect:/findRental";

    }

    @GetMapping("/deleteRental")
    public String deleteRental(@RequestParam("id") int id, HttpSession session){
        if(session.getAttribute("user")==null)
            return "frontpage";
        rentalService.deleteRental(id);
        return "redirect:/findRental";
    }


    @GetMapping("/findBooking")
    public String findBooking(HttpSession session, Model model){
        if(session.getAttribute("user")==null)
            return "frontpage";

        return "overviewBookings";
    }

    @GetMapping("/createRental")
    public String createRental(HttpSession session, Model model){
        if(session.getAttribute("user")==null)
            return "frontpage";

        return "createRental";
    }

    @PostMapping("/submitRental")
    public String submitRental(HttpSession session, Model model, @RequestParam("customer") int customer, @RequestParam("startDate") String startDate, @RequestParam("pickuppoint") String pickuppoint, @RequestParam("car") int car, @RequestParam("type") String type, @RequestParam("dropoffpoint") String dropoffpoint){
        if(session.getAttribute("user")==null)
            return "frontpage";
        String endDate=rentalService.calcEndDate(startDate,type);
        Rental rental = new Rental(pickuppoint, dropoffpoint, type, customer, startDate, endDate, car, false);
        rentalService.createRental(rental);
        return "redirect:/rental";
    }

    @GetMapping("/createUser")
    public String register(HttpSession session, Model model){

        return "register";
    }

    @PostMapping("/register")
    public String createAnAccount(@RequestParam("name")String name,
                                  @RequestParam("email") String email,
                                  @RequestParam("username")String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("type") String type,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        // Opdateret for at rykke logikken fra Controlleren ned i Service.
        return userService.createUser(name, username, email, password, type, model, redirectAttributes);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/editRentalStatus")
    public String editRentalStatus(@RequestParam("id") int id){
        Rental rental = rentalService.getRentalById(id);
        rental.setStatus(true);
        rentalService.updateRental(rental);
        return "redirect:/findRental";
    }

}
