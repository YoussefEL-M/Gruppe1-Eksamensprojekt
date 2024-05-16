package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//Youssef
@Repository
public class RentalRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(Rental rental) {
        String sql = "INSERT INTO rental (pickUpLocation, returnLocation, type, customer_id, startDate, endDate, car_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, rental.getPickUpLocation(), rental.getReturnLocation(), rental.getType(), rental.getCustomerId(), rental.getStartDate(), rental.getEndDate(), rental.getCarId());
    }

    public Rental getRentalById(int id) {
        String sql = "SELECT * FROM rental WHERE id = ?";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void update(Rental rental) {
        String sql = "UPDATE rental SET pickUpLocation=?, returnLocation=?, type=?, customer_id=?, startDate=?, endDate=?, car_id=? WHERE id=?";
        jdbcTemplate.update(sql, rental.getPickUpLocation(), rental.getReturnLocation(), rental.getType(), rental.getCustomerId(), rental.getStartDate(), rental.getEndDate(), rental.getCarId(), rental.getId());
    }




    public void delete(int id) {
        String sql = "DELETE FROM rental WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    public List<Rental> getAll() {
        String sql = "SELECT * FROM rental";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return jdbcTemplate.query(sql, rowMapper);
    }





}
