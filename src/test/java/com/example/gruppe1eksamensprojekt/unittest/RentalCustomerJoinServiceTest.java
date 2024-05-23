package com.example.gruppe1eksamensprojekt.unittest;

import com.example.gruppe1eksamensprojekt.model.RentalCustomerJoin;
import com.example.gruppe1eksamensprojekt.service.RentalCustomerJoinService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Youssef
@SpringBootTest
public class RentalCustomerJoinServiceTest {

    //Arrange
    @Autowired
    private RentalCustomerJoinService rentalCustomerJoinService;

    @BeforeEach
    void setup(){}

    @AfterEach
    void teardown(){}

    @Test
    void getAllTest() {
        //Act
        List<RentalCustomerJoin> rentalCustomerJoins = rentalCustomerJoinService.getAll();

        //Asset
        assertNotNull(rentalCustomerJoins);
        assertFalse(rentalCustomerJoins.isEmpty());
    }

    @Test
    void getAllByUserIdTest() {
        //Act
        List<RentalCustomerJoin> rentalCustomerJoins = rentalCustomerJoinService.getAllByUserId(1);

        //Assert
        assertNotNull(rentalCustomerJoins);
        assertFalse(rentalCustomerJoins.isEmpty());
        for (RentalCustomerJoin join : rentalCustomerJoins) {
            assertEquals(0, join.getUserID());
        }
    }

    @Test
    void getRentalByCarTest() {
        //Act
        RentalCustomerJoin join = rentalCustomerJoinService.getRentalByCar(1);

        //Assert
        assertNotNull(join);
        assertEquals(0, join.getCarId());
    }
}
