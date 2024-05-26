package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CarRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Clara
    public void create(Car car) {
        String sql = "INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String carId = "INSERT INTO carIdentification (serialnumber, brand, model) VALUES (?,?,?)";
        jdbcTemplate.update(sql, car.getId(), car.getSerialNumber(), car.getColor(), car.getTrimLevel(), car.getSteelPrice(), car.getRegistrationTax(), car.getEmission(), car.getStatus(), car.isDs(), car.getLicensePlate(), car.getFuelType(), car.getKmTraveled(), car.getFuelEfficiency(), car.getPrice(), car.isManual());
        jdbcTemplate.update(carId, car.getSerialNumber(), car.getBrand(), car.getModel());
    }

    // Henter bilen med det givne id fra databasen, er lavet med en join da nogle af bilernes oplysninger ligger i carIdentification tabellen
    public Car getCarById(int id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM car " +
                "LEFT JOIN carIdentification " +
                "ON car.serialNumber = carIdentification.serialNumber WHERE id = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void update(Car car) {
        String sql = "UPDATE car SET serialNumber=?, color=?, trimLevel=?, steelPrice=?, registrationTax=?, emission=?, status=?, ds=?, licensePlate=?, fuelType = ?, kmTraveled = ?, fuelEfficiency = ?, price = ?, manual  = ?, lastUpdated = ? WHERE id=?";
        jdbcTemplate.update(sql, car.getSerialNumber(), car.getColor(), car.getTrimLevel(), car.getSteelPrice(), car.getRegistrationTax(), car.getEmission(), car.getStatus().name(), car.isDs(), car.getLicensePlate(), car.getFuelType().name(), car.getKmTraveled(), car.getFuelEfficiency(), car.getPrice(), car.isManual(), car.getLastUpdated(), car.getId());
    }

    //Clara
    public void delete(String serialNumber) {
        String sql = "DELETE FROM car WHERE serialNumber = ?";
        String deleteFromCarId = "DELETE FROM carIdentification WHERE serialNumber = ?";
        jdbcTemplate.update(sql, serialNumber);
        jdbcTemplate.update(deleteFromCarId, serialNumber);
    }


    public List<Car> getAll() {
        String sql = "SELECT * FROM car " +
                "LEFT JOIN carIdentification " +
                "ON car.serialNumber = carIdentification.serialNumber";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    //Severin
    public List<Car> getRented(){
        String sql = "SELECT * FROM car " +
                "LEFT JOIN carIdentification " +
                "ON car.serialNumber = carIdentification.serialNumber WHERE status = 'RENTED'";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    //Severin
    public List<Car> getAvailable(){
        String sql = "SELECT * FROM car " +
                "LEFT JOIN carIdentification " +
                "ON car.serialNumber = carIdentification.serialNumber WHERE status = 'AVAILABLE'";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    //Clara
    public List<Car> getDamagedCars() {
        String sql = "SELECT * FROM car WHERE status = 'DAMAGED' AND ds = false";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }


    //Bjarke
    //Henter skadede og afventende biler der ikke er opdateret siden den givne dato
    public List<Car> getNotUpdated(String date){
        String sql = "SELECT * FROM car LEFT JOIN carIdentification ON car.serialNumber = carIdentification.serialNumber " +
                "WHERE (status='DAMAGED' OR status='PENDING') AND lastUpdated<?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql,rowMapper, date);
    }






}
