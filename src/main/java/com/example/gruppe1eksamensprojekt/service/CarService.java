package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarService { // Severin
    // Metoder oprettet inden Repo er færdig.
    // Todo: test at alle metoderne virker efter Repo er pushet.

    @Autowired
    CarRepo carRepo;

    public List<Car> getAll(){
        return carRepo.getAll();
    }

    //Clara
    //Fejlhåndtering i tilfælde af at bilen ikke findes i databasen
    public Car getCarById(int id){

        Car car;
        try {
            car = carRepo.getCarById(id);
        } catch (EmptyResultDataAccessException ERDA) {
            return null;
        }
        if(car.getId() == id) {
            return car;
        }

        return null;
    }

    public void updateCar(Car car){
        carRepo.update(car);
    }

    public void deleteCar(String serialNumber){
        carRepo.delete(serialNumber);
    }

    public List<Car> getAvailable(){
        return carRepo.getAvailable();
    }

    public List<Car> getRented(){
        return carRepo.getRented();
    }



    public double getRentedCarsTotalPrice(){
        return carRepo.getRented().stream().mapToDouble(Car::getPrice).sum();
    }

    //Clara
    public List<Car> getDamagedCars() {return carRepo.getDamagedCars();}


//Bjarke
    public List<Car> getNotUpdated() {
        String date = LocalDate.now().minusDays(4).toString();

        return carRepo.getNotUpdated(date);
    }


}
