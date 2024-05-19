package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.RentalCustomerJoin;
import com.example.gruppe1eksamensprojekt.repository.RentalCustomerJoinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalCustomerJoinService {


    @Autowired
    private RentalCustomerJoinRepo rentalCustomerJoinRepo;

    public List<RentalCustomerJoin> getAll(){
        return rentalCustomerJoinRepo.getAll();
    }

    public List<RentalCustomerJoin> getAllByUserId(int id){
        return rentalCustomerJoinRepo.getAllByUserId(id);
    }

}
