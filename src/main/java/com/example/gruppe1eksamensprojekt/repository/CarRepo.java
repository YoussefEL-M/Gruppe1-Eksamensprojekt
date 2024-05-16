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
        String sql = "INSERT INTO car (id, brand, serialNumber, model, color, trimLevel, steelPrice, registrationTax, emission, damaged, ds, licensePlate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getId(), car.getBrand(), car.getSerialNumber(), car.getModel(), car.getColor(), car.getTrimLevel(), car.getSteelPrice(), car.getRegistrationTax(), car.getEmission(), car.isDamaged(), car.isDs(), car.getLicensePlate());
    }

    public Car getCarById(int id){
        String sql = "SELECT * FROM car WHERE id = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void update(Car car) {
        String sql = "UPDATE car SET brand=?, serialNumber=?, model=?, color=?, trimLevel=?, steelPrice=?, registrationTax=?, emission=?, damaged=?, ds=?, licensePlate=? WHERE id=?";
        jdbcTemplate.update(sql, car.getBrand(), car.getSerialNumber(), car.getModel(), car.getColor(), car.getTrimLevel(), car.getSteelPrice(), car.getRegistrationTax(), car.getEmission(), car.isDamaged(), car.isDs(), car.getLicensePlate(), car.getId());
    }
    public void delete(int id) {
        String sql = "DELETE FROM car WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Car> getAll() {
        String sql = "SELECT * FROM car";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Car> getRented(){
        String sql = "SELECT * FROM car WHERE status = 'RENTED'";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

}
