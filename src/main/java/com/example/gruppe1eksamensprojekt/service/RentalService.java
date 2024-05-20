package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.model.CarStatus;
import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.repository.CarRepo;
import com.example.gruppe1eksamensprojekt.repository.CustomerRepo;
import com.example.gruppe1eksamensprojekt.repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService { //Severin
    // Metoder oprettet inden Repo er færdig.
    // Todo: test at alle metoderne virker efter Repo er pushet.

    @Autowired
    private RentalRepo rentalRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CarRepo carRepo;

    public List<Rental> getAll(){
        return rentalRepo.getAll();
    }

    public void createRental(Rental rental){
        rentalRepo.create(rental);
    }

    public String submitRental(String customer, String startDate, String pickuppoint, String car, String type, String dropoffpoint, String unlimitedMonth, RedirectAttributes redirectAttributes){
        boolean error = false;
        int customerId = 0;
        int carId = 0;

        try{
            customerId = Integer.parseInt(customer.split("\\.")[0]);
            customerRepo.getCustomerById(customerId);
        } catch (EmptyResultDataAccessException | NumberFormatException E){
            redirectAttributes.addFlashAttribute("customerNotFound", true);
            error = true;
        }
        // Kan der være behov for at registrere en kontrakt efter lejeperioden begynder?
        if(LocalDate.parse(startDate).isBefore(LocalDate.now())) {
            redirectAttributes.addFlashAttribute("timeTravelException", true);
            error = true;
        }


        try{
            carId = Integer.parseInt(car.split("\\.")[0]);
            if(!carRepo.getCarById(carId).getStatus().equals(CarStatus.AVAILABLE)){
                redirectAttributes.addFlashAttribute("carAlreadyRented", true);
                error = true;
            }
        } catch (EmptyResultDataAccessException | NumberFormatException E){
            redirectAttributes.addFlashAttribute("carNotFound", true);
            error = true;
        }

        // Hvis type ikke er Limited, sæt type lig antal måneder valgt til Unlimited.
        if(!type.equals("5")) type = unlimitedMonth;
        if(type.isEmpty()) type = "0";
        if(3>Integer.parseInt(type)||Integer.parseInt(type)>36) {
            redirectAttributes.addFlashAttribute("typeError", true);
            error = true;
        }


        if(error) {
            redirectAttributes.addFlashAttribute("customer", customer);
            redirectAttributes.addFlashAttribute("startDate", startDate);
            redirectAttributes.addFlashAttribute("pickuppoint", pickuppoint);
            redirectAttributes.addFlashAttribute("car", car);
            redirectAttributes.addFlashAttribute("type", Integer.parseInt(type));
            redirectAttributes.addFlashAttribute("dropoffpoint", dropoffpoint);
            redirectAttributes.addFlashAttribute("unlimitedMonth", unlimitedMonth);
        } else{
            String endDate = calcEndDate(startDate,type);
            Car carToUpdate = carRepo.getCarById(carId);
            carToUpdate.setStatus(CarStatus.RENTED);
            carRepo.update(carToUpdate);
            Rental rental = new Rental(pickuppoint, dropoffpoint, type, customerId, startDate, endDate, carId, false);
            rentalRepo.create(rental);
            return "redirect:/rental";
        }

        return "redirect:/createRental";
    }

    //Opdater i klassediagram
    public Rental getRentalById(int id){

        Rental rental;
        try {
            rental = rentalRepo.getRentalById(id);
        } catch (EmptyResultDataAccessException ERDA) {

            return null;
        }
        if(rental.getId() == id) {

            return rental;
        }
        return null;
    }

    public void updateRental(Rental rental){
        rentalRepo.update(rental);
    }

    public String validateAndUpdate(int id, String startDate, String pickUpLocation, String car, String endDate, String returnLocation, RedirectAttributes redirectAttributes){
        boolean error = false;
        int carId = 0;

        if(LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))){
            redirectAttributes.addFlashAttribute("timeTravelException", true);
            error = true;
        }

        try{
            carId = Integer.parseInt(car.split("\\.")[0]);
            // Hvis bilen ikke er den samme som den i report (er blevet ændret) og ikke er tilgængelig, set error flag.
            if(!carRepo.getCarById(carId).getStatus().equals(CarStatus.AVAILABLE) && carId != rentalRepo.getRentalById(id).getCarId()){
                redirectAttributes.addFlashAttribute("carAlreadyRented", true);
                error = true;
            }
        } catch (EmptyResultDataAccessException | NumberFormatException E){
            redirectAttributes.addFlashAttribute("carNotFound", true);
            error = true;
        }

        if(error){
            redirectAttributes.addFlashAttribute("startDate", startDate);
            redirectAttributes.addFlashAttribute("endDate", endDate);
            redirectAttributes.addFlashAttribute("pickUpLocation", pickUpLocation);
            redirectAttributes.addFlashAttribute("returnLocation", returnLocation);
            redirectAttributes.addFlashAttribute("car", car);
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/editRental?id=" + id;
        }

        Rental rental = rentalRepo.getRentalById(id);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setPickUpLocation(pickUpLocation);
        rental.setReturnLocation(returnLocation);
        rental.setCarId(carId);
        rentalRepo.update(rental);
        return "redirect:/findRental";
    }

    public void deleteRental(int id){
        rentalRepo.delete(id);
    }

    public String calcEndDate(String startDate, String type) {

        return LocalDate.parse(startDate).plusMonths(Integer.parseInt(type)).toString();
    }

    public Rental getRentalsByUserID(int id){
        return rentalRepo.getRentalsByUserID(id);
    }

}
