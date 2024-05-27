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

    //Severin
    public String submitRental(String customer, String startDate, String pickuppoint, String car, String type, String dropoffpoint, String unlimitedMonth, int userID, RedirectAttributes redirectAttributes){
        boolean error = false; //Error flag; sættes til 'true', hvis der er fejl i brugerinput.
        int customerId = 0;
        int carId = 0;

        //Undersøger om valgt kunde er gyldig.
        try{
            customerId = Integer.parseInt(customer.split("\\.")[0]);
            customerRepo.getCustomerById(customerId);
        } catch (EmptyResultDataAccessException | NumberFormatException E){
            redirectAttributes.addFlashAttribute("customerNotFound", true);
            error = true;
        }
        //Undersøger om valgt dato er gyldig; ikke i fortiden.
        // Kan der være behov for at registrere en kontrakt efter lejeperioden begynder?
        if(LocalDate.parse(startDate).isBefore(LocalDate.now())) {
            redirectAttributes.addFlashAttribute("timeTravelException", true);
            error = true;
        }

        //Undersøger om valgt bil er gyldig.
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

        //Hvis error flag er sat, tilføj indtastet data til redirectAttributes, så brugeren ikke skal taste dem ind igen efter redirect.
        if(error) {
            redirectAttributes.addFlashAttribute("customer", customer);
            redirectAttributes.addFlashAttribute("startDate", startDate);
            redirectAttributes.addFlashAttribute("pickuppoint", pickuppoint);
            redirectAttributes.addFlashAttribute("car", car);
            redirectAttributes.addFlashAttribute("type", Integer.parseInt(type));
            redirectAttributes.addFlashAttribute("dropoffpoint", dropoffpoint);
            redirectAttributes.addFlashAttribute("unlimitedMonth", unlimitedMonth);
        } else{ //Hvis ikke, opret lejeaftalen.
            Car carToUpdate = carRepo.getCarById(carId);
            String endDate = calcEndDate(startDate,type, carToUpdate.isDs());
            carToUpdate.setStatus(CarStatus.RENTED);
            carToUpdate.setLastUpdated(LocalDate.now());
            carRepo.update(carToUpdate);
            Rental rental = new Rental(pickuppoint, dropoffpoint, type, customerId, startDate, endDate, carId, "FRESH", userID);
            rentalRepo.create(rental);
            return "redirect:/rental";
        }

        //Nås kun, hvis error flag er sat. Sender brugeren tilbage til oprettelsessiden.
        return "redirect:/createRental";
    }

    //Clara
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

    //Youssef
    //Metode der opdatere lejeaftaler i databasen
    public void updateRental(Rental rental){
        rentalRepo.update(rental);
    }

    //Severin
    //Validerer og opdaterer lejeaftale.
    public String validateAndUpdate(int id, String startDate, String pickUpLocation, String car, String endDate, String returnLocation, RedirectAttributes redirectAttributes){
        boolean error = false; //Error flag; sættes til 'true', hvis der er fejl i brugerens input.
        int carId = 0;

        //Undersøger om valgt dato er gyldig.
        //Startdato må ikke være efter slutdato.
        //Ingen undtagelser for tidsrejsende.
        if(LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))){
            redirectAttributes.addFlashAttribute("timeTravelException", true);
            error = true;
        }

        //Undersøger om valgt bil er gyldig.
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

        //Hvis error flag er sat, tilføj indtastet data til redirectAttributes, så brugeren ikke skal taste dem ind igen efter redirect.
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

        // Hvis bilen i lejeaftalen er blevet ændret, opdater bilernes status.
        if (rental.getCarId()!=carId){
            Car oldCar = carRepo.getCarById(rental.getCarId());
            Car newCar = carRepo.getCarById(carId);

            oldCar.setStatus(CarStatus.AVAILABLE);
            newCar.setStatus(CarStatus.RENTED);
            oldCar.setLastUpdated(LocalDate.now());
            newCar.setLastUpdated(LocalDate.now());

            carRepo.update(oldCar);
            carRepo.update(newCar);
        }

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

    // Bjarke og Severin
    // Beregner slutdato på lejeaftale ud fra startdato og antal måneder.
    public String calcEndDate(String startDate, String type, boolean isDS) {

        LocalDate endDate = LocalDate.parse(startDate).plusMonths(Integer.parseInt(type));

        // Hvis bilen er en DS bil, skal den returneres to dage før måneden slutter, for at undgå at betale en ekstra måneds nummerpladeafgift.
        if(isDS) {
            // Hvis slutdato ikke er en af de sidste to dage i måneden, returner slutdato.
            if (endDate.plusDays(2).getMonth().equals(endDate.getMonth())) {
                return endDate.toString();
            }

            // Hvis slutdato er den næstsidste dag i måneden, træk en dag fra og returner ny slutdato.
            else if (endDate.plusDays(1).getMonth().equals(endDate.getMonth())) {
                return endDate.minusDays(1).toString();
            }

            // Hvis slutdato er den sidste dag i måneden, træk to dage fra og returner ny slutdato.
            else return endDate.minusDays(2).toString();
        }

        return endDate.toString();
    }


    //Bjarke
    public List<Rental> getCurrentRentals(){return rentalRepo.getCurrentRentals();}

    //Bjarke
    public Rental getCurrentRentalByCarID(int id){return rentalRepo.getCurrentRentalByCarID(id);}


}
