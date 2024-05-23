package com.example.gruppe1eksamensprojekt.unittest;

import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.service.RentalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentalTest {

    //Arrange
    @Autowired
    RentalService rentalService;

    @BeforeEach
    void setup(){}

    @AfterEach
    void teardown(){}

    //public String calcEndDate(String startDate, String type)
    @Test
    void calcEndDateTest(){
        //arrange
        String startDate = "2020-01-01";
        String type ="3";

        //Act
        String testResult=rentalService.calcEndDate(startDate, type, false);

        //assert
        assertEquals("2020-04-01",testResult);


    }



    @Test
    void getRentalById(){

        int id = 1;

        int testResult=rentalService.getRentalById(id).getId();

        assertEquals(1,testResult);

    }


    @Test
    void createRental(){
        Rental newRental = new Rental();
        newRental.setPickUpLocation("KBH");
        newRental.setReturnLocation("Roskilde");
        newRental.setType("5");
        newRental.setCustomerId(1);
        newRental.setStartDate("2024-01-01");
        newRental.setEndDate("2024-06-01");
        newRental.setCarId(1);
        newRental.setStatus("FRESH");
        newRental.setUserID(1);

        rentalService.createRental(newRental);

        Rental getRental = rentalService.getRentalById(1);

        assertNotNull(getRental);
        int id = 1;

        int testResult=rentalService.getRentalById(id).getId();

        assertEquals(1,testResult);

    }



}
