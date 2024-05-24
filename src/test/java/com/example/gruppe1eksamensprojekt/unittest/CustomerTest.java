package com.example.gruppe1eksamensprojekt.unittest;

import com.example.gruppe1eksamensprojekt.model.Customer;
import com.example.gruppe1eksamensprojekt.service.CustomerSevice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class CustomerTest {

    @Autowired
    CustomerSevice customerService;

    @BeforeEach
    void setup(){}

    @AfterEach
    void teardown(){}

    @Test
    void getAllTest(){

        //Arrange
        List<Customer> list;

        //Act
        list = customerService.getAll();

        //Assert
        assert (!list.isEmpty());

    }

    @Test
    void createCustomerTest(){

        //Arrange
        Customer customer = new Customer("John Testman", "12345678", "109 Test Ave.", "Mail@te.st", LocalDate.parse("2001-09-11"), 12345);

        //Act
        customerService.createCustomer(customer);

        //Assert
        assert customerService.getCustomerById(12345)!=null;

        //Cleanup
        customerService.deleteCustomer(12345);
    }

    @Test
    void getCustomerByIdTest(){

        //Arrange
        customerService.deleteCustomer(12345);
        Customer customer = new Customer("John Testman", "12345678", "109 Test Ave.", "Mail@te.st", LocalDate.parse("2001-09-11"), 12345);

        //Act
        customerService.createCustomer(customer);

        //Assert
        assert customerService.getCustomerById(12345)!=null;

        //Cleanup
        customerService.deleteCustomer(12345);
    }

    @Test
    void updateCustomerTest(){

        //Arrange
        Customer customer = new Customer("John Testman", "12345678", "109 Test Ave.", "Mail@te.st", LocalDate.parse("2001-09-11"), 12345);
        customerService.createCustomer(customer);

        //Act
        customer.setName("Jim Testman");
        customerService.updateCustomer(customer);

        //Assert
        assert customerService.getCustomerById(12345).getName().equals("Jim Testman");

        //Cleanup
        customerService.deleteCustomer(12345);

    }

    @Test
    void deleteCustomerTest(){

        //Arrange
        Customer customer = new Customer("John Testman", "12345678", "109 Test Ave.", "Mail@te.st", LocalDate.parse("2001-09-11"), 12345);
        customerService.createCustomer(customer);

        //Act
        customerService.deleteCustomer(12345);

        //Assert
        assert customerService.getCustomerById(12345)==null;
    }
}
