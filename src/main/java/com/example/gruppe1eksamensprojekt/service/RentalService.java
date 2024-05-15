package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Rental getRentalById(int id){
        return rentalRepo.getRentalById(id);
    }

    public void updateRental(Rental rental){

        rentalRepo.update(rental);
    }

    public void deleteRental(int id){
        rentalRepo.delete(id);
    }

    public String calcEndDate(String startDate, String type) {

        if (type.equals("3")) return LocalDate.parse(startDate).plusMonths(3).toString();
        else return LocalDate.parse(startDate).plusMonths(5).toString();
    }
}
