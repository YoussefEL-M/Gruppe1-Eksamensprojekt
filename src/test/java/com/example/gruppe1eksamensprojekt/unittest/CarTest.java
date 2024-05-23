package com.example.gruppe1eksamensprojekt.unittest;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.service.CarService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.List;

import static com.example.gruppe1eksamensprojekt.model.CarStatus.PENDING;
import static org.junit.jupiter.api.Assertions.*;

//Clara


@SpringBootTest
public class CarTest {

    //Arrange

    @Autowired
    CarService carService;



    @BeforeEach
    void setup(){}

    @AfterEach
    void teardown(){}

    @Test
    public void getCarByIdTest(){

        int carId = 3;
        //Act

        Car car = carService.getCarById(carId);

        //Assert

        assertNotNull(car, "Test med at finde en bil ud fra id");

    }

    //Spørgsmål til Jarl...
    @Test
    public void getCarByNonExistingIdTest(){

        // Act / Assert

        assertThrows(EmptyResultDataAccessException.class, () -> carService.getCarById(20));
    }

    @Test
    public void getAllTest() {

        //Act
        List<Car> carList = carService.getAll();

        //Assert
        assertFalse(carList.isEmpty());
    }

    //Virker heller ikke!!!
    //Spørg Jarl om det!!!!!!!!!!!!!!!!!
    @Test
    public void createTest() {

        //Spørgsmål til Jarl: Hvor går grænsen mellem Arrange og Act??

        //Arrange eller Act ????

        Car car = new Car();

        carService.createCar(car);

        //Assert
        assertNotNull(car, "Test med oprettelse af bil");

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

        //Act
        carService.deleteCar(car.getSerialNumber());

        //Assert
        Car deletedCar = carService.getCarById(car.getId());
        assertNull(deletedCar);
    }

    @Test
    public void getRentedTest() {

        //Arrange / Act
        List<Car> rentedList = carService.getRented();

        //Assert
        assertNotNull(rentedList);
    }

    @Test
    public void getAvailableTest() {

        //Arrange / Act
        List<Car> available = carService.getAvailable();

        //Assert
        assertNotNull(available);

    }

    @Test
    public void getDamagedTest() {

        //Arrange / Act
        List<Car> damagedCars = carService.getDamagedCars();

        //Assert
        assertNotNull(damagedCars);
    }

    //Spørgsmål til Jarl: Hvorfor er den ikke null, når der ikke er noget i listen??
    //Den vil ikke acceptere en assertNull
    @Test
    public void getNotUpdatedTest() {

        //Arrange / Act
        List<Car> notUpdated = carService.getNotUpdated();

        //Assert
        //Skal vel være null, idet der ikke på nuværende tidspunkt er biler i listen
        assertNotNull(notUpdated);


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
