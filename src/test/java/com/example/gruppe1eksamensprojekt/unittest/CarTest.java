package com.example.gruppe1eksamensprojekt.unittest;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.model.CarStatus;
import com.example.gruppe1eksamensprojekt.model.FuelType;
import com.example.gruppe1eksamensprojekt.repository.CarRepo;
import com.example.gruppe1eksamensprojekt.service.CarService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.gruppe1eksamensprojekt.model.CarStatus.AVAILABLE;
import static com.example.gruppe1eksamensprojekt.model.CarStatus.PENDING;
import static org.junit.jupiter.api.Assertions.*;

//Clara


@SpringBootTest
public class CarTest {

    //Arrange

    @Autowired
    CarService carService;
    @Autowired
    CarRepo carRepo;



    @BeforeEach
    void setup(){}

    @AfterEach
    void teardown(){}

    @Test
    public void getCarByIdTest(){

        //Arrange
        int carId = 3;

        //Act
        Car car = carService.getCarById(carId);
        List<Car> aCar = new ArrayList<>();
        aCar.add(car);

        //Assert
        assertTrue(aCar.contains(car));
        assertEquals(3, car.getId());

    }

    @Test
    public void getCarByNonExistingIdTest(){

        // Act / Assert

        assertThrows(EmptyResultDataAccessException.class, () -> carRepo.getCarById(300));
    }

    @Test
    public void getAllTest() {

        //Act
        List<Car> carList = carService.getAll();

        //Assert
        assertFalse(carList.isEmpty());
    }

    @Test
    public void updateTest() {

        //Arrange
        Car car = carService.getCarById(6);

        car.setStatus(PENDING);
        car.setLastUpdated(LocalDate.parse("2024-05-23"));

        //Act
        carService.updateCar(car);

        //Assert
        Car updatedCar = carService.getCarById(car.getId());
        assertEquals(PENDING, updatedCar.getStatus());
        assertEquals(LocalDate.parse("2024-05-23"), updatedCar.getLastUpdated());
    }


    //Virker ikke da car ikke er null, hvilket må betyde, at den ikke er slettet og at metoden derfor ikke virker
    @Test
    public void deleteTest() {

        //Arrange
        Car car = carService.getCarById(6);
        //int result;

        //Act
        carService.deleteCar(car.getSerialNumber());
        List<Car> cars = carService.getAll();
        //result = cars.size();


        //Assert
        Car deletedCar = carService.getCarById(car.getId());
        assertFalse(cars.contains(deletedCar));
    }

    @Test
    public void getRentedTest() {

        //Arrange
        int result;

        // Act
        List<Car> rentedList = carService.getRented();

        result = rentedList.size();

        //Assert
        assertEquals(2, result, "Test af metode til at finde udlejede biler");
    }

    @Test
    public void getAvailableTest() {

        //Arrange
        int result;

        // Act
        List<Car> available = carService.getAvailable();
        result = available.size();

        //Assert
        assertEquals(4, result, "Test med at få fat i tilgængelige biler");

    }

    @Test
    public void getDamagedTest() {

        //Arrange / Act
        List<Car> damagedCars = carService.getDamagedCars();

        //Assert
        assertTrue(damagedCars.isEmpty());
    }


    @Test
    public void getNotUpdatedTest() {

        //Arrange / Act
        List<Car> notUpdated = carService.getNotUpdated();

        //Assert
        assertTrue(notUpdated.isEmpty());


    }


    @Test
    public void getRentedCarsTotalPriceTest() {

        //Act
        double totalPrice = carService.getRentedCarsTotalPrice();

        //Assert
        assertEquals(4500, totalPrice, "Test af metode til at se samlet pris for udlejede biler");

    }

    @Test
    public void getRentedCarsTotalPriceAccuracyTest(){

        //Act
        double totalPrice = carService.getRentedCarsTotalPrice();

        //Assert + spørgsmål til Jarl: Hvad skriver man i message?
        assertEquals(4500, totalPrice, 0.01, "Fejl på decimaler");
    }


}
