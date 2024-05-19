package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.Car;
import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public void createCar(Car car){
        carRepo.create(car);
    }

    //Noter i klassediagram
    public Car getCarById(int id, Model model){

        Car car;
        try {
            car = carRepo.getCarById(id);
        } catch (EmptyResultDataAccessException ERDA) {
            model.addAttribute("unableToFindCar", true);
            return null;
        }
        if(car.getId() == id) {
            return car;
        }

        return null;
    }

    public void updateCar(Car car){
        // Skal den ikke have et Car objekt som parameter?
        // Indtil videre gør jeg, som der står i klassediagrammet - Severin.
        carRepo.update(car);
    }

    public void deleteCar(int id){
        carRepo.delete(id);
    }

    public List<Car> getRented(){
        return carRepo.getRented();
    }

    public double getRentedCarsTotalPrice(){
        return carRepo.getRented().stream().mapToDouble(Car::getPrice).sum();
    }

    //Clara
    public List<Car> getDamagedCars() {return carRepo.getDamagedCars();}
}
