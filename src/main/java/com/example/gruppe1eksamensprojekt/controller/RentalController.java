package com.example.gruppe1eksamensprojekt.controller;

import com.example.gruppe1eksamensprojekt.model.*;
import com.example.gruppe1eksamensprojekt.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class RentalController { // Severin
    // Todo: gennemgå metodernes Models og sørg for at deres attributter er navngivet korrekt.

    //@Value("${adminPass}") //Todo: sæt op til Azure Key Vault.
    private String adminPass = "test";

    @Autowired
    UserService userService;

    @Autowired
    RentalService rentalService;

    @Autowired
    CustomerSevice customerService;

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
    public String loggingIn(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam("username") String username, @RequestParam("password") String password){
        User user = userService.login(username, password, redirectAttributes);
        // Todo: evt. tilføj funktionalitet der sender brugeren til en bestemt side alt efter brugertype.
        if(user != null) {
            session.setAttribute("user", user);
            return "redirect:" + user.getFrontPage();
        }
        else return "redirect:/";
    }

    @GetMapping("/rental")
    public String rental(HttpSession session, Model model){

        if(session.getAttribute("user")==null)
            return "frontpage";

        model.addAttribute("carList",carService.getAvailable());
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
        Rental rental = rentalService.getRentalById(id);
        model.addAttribute("rental", rental);
        model.addAttribute("cars", carService.getAvailable());
        model.addAttribute("rentalCar", carService.getCarById(rental.getCarId()));

        return "rentalUpdateForm";
    }


    //Opdater klassediagram
    @PostMapping("/updateRental")
    public String updateRental(@RequestParam("id") int id, @RequestParam("endDate") String endDate,
                               @RequestParam("pickUpLocation") String pickUpLocation,
                               @RequestParam("startDate") String startDate,
                               @RequestParam("car") String car,
                               @RequestParam("returnLocation") String returnLocation,
                               RedirectAttributes redirectAttributes,
                               HttpSession session){
        if(session.getAttribute("user")==null)
            return "frontpage";

        // Ændret, så validateAndUpdate returnerer en redirect String.
        return rentalService.validateAndUpdate(id, startDate, pickUpLocation, car, endDate, returnLocation, redirectAttributes);
    }

    @GetMapping("/deleteRental")
    public String deleteRental(@RequestParam("id") int id, @RequestParam("page") String page, HttpSession session){
        if(session.getAttribute("user")==null)
            return "frontpage";
        Car car = carService.getCarById(rentalService.getRentalById(id).getCarId());
        car.setStatus(CarStatus.AVAILABLE);
        car.setLastUpdated(LocalDate.now());
        carService.updateCar(car);
        rentalService.deleteRental(id);
        if (page.equals("your")) return "redirect:/yourRentals";
        return "redirect:/findRental";
    }

    @GetMapping("/createRental")
    public String createRental(HttpSession session, Model model){

        if(session.getAttribute("user")==null)
            return "frontpage";

        model.addAttribute("customerList", customerService.getAll());
        model.addAttribute("carList", carService.getAvailable());
        return "createRental";
    }

    @PostMapping("/submitRental")
    public String submitRental(HttpSession session, RedirectAttributes redirectAttributes,
                               @RequestParam("customer") String customer,
                               @RequestParam("startDate") String startDate,
                               @RequestParam("pickuppoint") String pickuppoint,
                               @RequestParam("car") String car,
                               @RequestParam("type") String type,
                               @RequestParam("dropoffpoint") String dropoffpoint,
                               @RequestParam("unlimitedMonth") String unlimitedMonth){
        if(session.getAttribute("user")==null)
            return "frontpage";

        User user = (User) session.getAttribute("user");
        int userID =user.getId();
        // Opdateret, så submitRental() returnerer et redirect.
        return rentalService.submitRental(customer, startDate, pickuppoint, car, type, dropoffpoint, unlimitedMonth, userID, redirectAttributes);

    }

    //Bjarke
    @GetMapping("/createUser")
    public String register(HttpSession session, Model model){

        return "register";
    }

    @PostMapping("/register")
    public String createAnAccount(@RequestParam("name")String name,
                                  @RequestParam("email") String email,
                                  @RequestParam("username")String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  @RequestParam("type") String type, @RequestParam("adminPassword") String adminPassword,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        if(!password.equals(confirmPassword)){
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("type", type);
            redirectAttributes.addFlashAttribute("passwordMismatch", true);
            return "redirect:/createUser";
        }

        if(!adminPassword.equals(adminPass)){
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("type", type);
            redirectAttributes.addFlashAttribute("adminPasswordMismatch", true);
            return "redirect:/createUser";
        }

        // Opdateret for at rykke logikken fra Controlleren ned i Service.
        // Servicemetoden createUser() har nu returnværdi, der omdiregerer alt efter metodens udfald.
        return userService.createUser(name, username, password, email, type, model, redirectAttributes);
    }

    //Bjarke
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }


    //Bjarke
    @GetMapping("/editRentalStatus")
    public String editRentalStatus(@RequestParam("id") int id, @RequestParam("page") String page, Model model){
        Rental rental = rentalService.getRentalById(id);
        rental.setStatus("CURRENT");
        rentalService.updateRental(rental);

        Car car = carService.getCarById(rental.getCarId());
        car.setStatus(CarStatus.PENDING);
        car.setLastUpdated(LocalDate.now());
        carService.updateCar(car);

        if (page.equals("your")) return "redirect:/yourRentals";
        return "redirect:/findRental";
    }

    //Bjarke
    @GetMapping("/yourRentals")
    public String yourRentals(Model model, HttpSession session){

        if(session.getAttribute("user")==null)
            return "frontpage";

        User user = (User) session.getAttribute("user");
        model.addAttribute("rentalList",rentalCustomerJoinService.getAllByUserId(user.getId()));


        return "yourRentals";
    }

}
