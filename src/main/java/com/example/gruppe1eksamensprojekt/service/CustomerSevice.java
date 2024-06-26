package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Customer;
import com.example.gruppe1eksamensprojekt.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    //Clara
    //Fejlhåndtering i tilfælde af at den give kunde ikke findes i databasen
    public Customer getCustomerById(int id){

        Customer customer;
        try {
            customer = customerRepo.getCustomerById(id);
        } catch (EmptyResultDataAccessException ERDA) {
            return null;
        }
        if(customer.getId() == id) {
            return customer;
        }

        return null;
    }

    public void updateCustomer(Customer customer){
        customerRepo.update(customer);
    }

    public void deleteCustomer(int id){
        customerRepo.delete(id);
    }
}
