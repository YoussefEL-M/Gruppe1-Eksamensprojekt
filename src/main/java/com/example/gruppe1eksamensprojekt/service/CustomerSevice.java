package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Customer;
import com.example.gruppe1eksamensprojekt.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSevice { // Severin
    // Metoder oprettet inden Repo er færdig.
    // Todo: test at alle metoderne virker efter Repo er pushet.

    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> getAll(){
        return customerRepo.getAll();
    }

    public void createCustomer(Customer customer){
        customerRepo.create(customer);
    }

    public Customer getCustomerById(int id){
        // Er ikke i klassediagrammet, men antager at vi skal bruge den.
        // Todo: tilføj til klassediagram.
        return customerRepo.getCustomerById(id);
    }

    public void updateCustomer(Customer customer){
        // Skal den ikke have et 'Customer' objekt som parameter?
        // Indtil videre gør jeg, som der står i klassediagrammet - Severin.
        customerRepo.update(customer);
    }

    public void deleteCustomer(int id){
        customerRepo.delete(id);
    }
}
