package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void updateRental(int id){
        // Skal den ikke have et Rental objekt som parameter?
        // Indtil videre gør jeg, som der står i klassediagrammet - Severin.
        rentalRepo.update(id);
    }

    public void deleteRental(int id){
        rentalRepo.delete(id);
    }
}
