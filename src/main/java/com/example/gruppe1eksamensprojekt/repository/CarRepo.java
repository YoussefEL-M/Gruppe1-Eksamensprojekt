package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Youssef
@Repository
public class CarRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Car car) {
        String sql = "INSERT INTO car (id, serialNumber, color, trimLevel, steelPrice, registrationTax, emission, status, ds, licensePlate, fuelType, kmTraveled, fuelEfficiency, price, manual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String carId = "INSERT INTO carIdentification (serialnumber, brand, model) VALUES (?,?,?)";
        jdbcTemplate.update(sql, car.getId(), car.getSerialNumber(), car.getColor(), car.getTrimLevel(), car.getSteelPrice(), car.getRegistrationTax(), car.getEmission(), car.getStatus(), car.isDs(), car.getLicensePlate(), car.getFuelType(), car.getKmTraveled(), car.getFuelEfficiency(), car.getPrice(), car.isManual());
        jdbcTemplate.update(carId, car.getSerialNumber(), car.getBrand(), car.getModel());
    }

    public Car getCarById(int id){
        String sql = "SELECT * FROM car WHERE id = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void update(Car car) {
        String sql = "UPDATE car SET serialNumber=?, color=?, trimLevel=?, steelPrice=?, registrationTax=?, emission=?, status=?, ds=?, licensePlate=?, fuelType = ?, kmTraveled = ?, fuelEfficiency = ?, price = ?, manual  = ? WHERE id=?";
        String updateCarIdentification  = "UPDATE carIdentification SET brand = ?, model = ? WHERE serialNumber = ?";
        jdbcTemplate.update(sql, car.getSerialNumber(), car.getColor(), car.getTrimLevel(), car.getSteelPrice(), car.getRegistrationTax(), car.getEmission(), car.getStatus(), car.isDs(), car.getLicensePlate(), car.getId());
        jdbcTemplate.update(updateCarIdentification, car.getBrand(), car.getModel());
    }
    public void delete(int id) {
        String sql = "DELETE FROM car WHERE id = ?";
        String deleteFromCarId = "DELETE FROM carIdentification WHERE serialNumber = ?";
        jdbcTemplate.update(sql, id);
        jdbcTemplate.update(deleteFromCarId, id);
    }

    public List<Car> getAll() {
        String sql = "SELECT * FROM car " +
                "LEFT JOIN carIdentification " +
                "ON car.serialNumber = carIdentification.serialNumber";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Car> getRented(){
        String sql = "SELECT * FROM car WHERE status = 'RENTED'";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    //Clara
    public List<Car> getDamagedCars() {
        String sql = "SELECT * FROM car WHERE status = 'DAMAGED' AND ds = false";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

}
