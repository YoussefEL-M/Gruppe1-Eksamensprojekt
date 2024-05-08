package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Costumer;
import com.example.gruppe1eksamensprojekt.repository.CostumerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostumerSevice { // Severin
    // Metoder oprettet inden Repo er færdig.
    // Og vi har stavet 'customer' forkert...
    // Nu gør jeg det konsistent; så ken vi rette det senere.
    // Todo: test at alle metoderne virker efter Repo er pushet.

    @Autowired
    CostumerRepo costumerRepo;

    public List<Costumer> getAll(){
        return costumerRepo.getAll();
    }

    public void createCostumer(Costumer costumer){
        costumerRepo.create(costumer);
    }

    public Costumer getCostumerById(int id){
        // Er ikke i klassediagrammet, men antager at vi skal bruge den.
        // Todo: tilføj til klassediagram.
        return costumerRepo.getCostumerById(id);
    }

    public void updateCostumer(int id){
        // Skal den ikke have et 'Costumer' objekt som parameter?
        // Indtil videre gør jeg, som der står i klassediagrammet - Severin.
        costumerRepo.update(id);
    }

    public void deleteCostumer(int id){
        costumerRepo.delete(id);
    }
}
