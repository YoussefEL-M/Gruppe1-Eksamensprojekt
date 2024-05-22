package com.example.gruppe1eksamensprojekt.unittest;

import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.service.RentalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
