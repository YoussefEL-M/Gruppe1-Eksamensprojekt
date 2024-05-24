package com.example.gruppe1eksamensprojekt.unittest;

import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.model.RentalCustomerJoin;
import com.example.gruppe1eksamensprojekt.service.RentalCustomerJoinService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentalCustomerJoinServiceTest {

    @Autowired
    RentalCustomerJoinService rentalCustomerJoinService;

    @BeforeEach
    void setup() {

    }

    @AfterEach
    void teardown() {

    }

    @Test
    public void getAllTest() {

        //Arrange / Act
        List<RentalCustomerJoin> rentalCustomerJoinList = rentalCustomerJoinService.getAll();

        //Assert
        assertFalse(rentalCustomerJoinList.isEmpty());
    }

    @Test
    public void getAllByUserIdTest() {

        //Arrange / Act
        List<RentalCustomerJoin> listOfRentalCustomerJoin = rentalCustomerJoinService.getAllByUserId(3);

        //Assert
        assertFalse(listOfRentalCustomerJoin.isEmpty());

    }

    @Test
    public void getRentalByCarTest() {

        //Arrange
        List<RentalCustomerJoin> list = new ArrayList<>();

        // Act
        RentalCustomerJoin rental = rentalCustomerJoinService.getRentalByCar(4);
        list.add(rental);

        //Assert
        assertTrue(list.contains(rental));
    }

}
