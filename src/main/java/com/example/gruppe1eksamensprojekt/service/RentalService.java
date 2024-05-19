package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService { //Severin
    // Metoder oprettet inden Repo er færdig.
    // Todo: test at alle metoderne virker efter Repo er pushet.

    @Autowired
    private RentalRepo rentalRepo;

    public List<Rental> getAll(){
        return rentalRepo.getAll();
    }

    public void createRental(Rental rental){
        rentalRepo.create(rental);
    }

    //Opdater i klassediagram
    public Rental getRentalById(int id, Model model){

        Rental rental;
        try {
            rental = rentalRepo.getRentalById(id);
        } catch (EmptyResultDataAccessException ERDA) {

            model.addAttribute("unableToFindRental", true);
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

    public void deleteRental(int id){
        rentalRepo.delete(id);
    }

    public String calcEndDate(String startDate, String type) {

        return LocalDate.parse(startDate).plusMonths(Integer.parseInt(type)).toString();
    }
}
