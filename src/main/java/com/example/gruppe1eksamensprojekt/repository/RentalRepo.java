package com.example.gruppe1eksamensprojekt.repository;


import com.example.gruppe1eksamensprojekt.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Youssef
@Repository
public class RentalRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(Rental rental) {
        String sql = "INSERT INTO rentals (pickUpLocation, returnLocation, type, id, customerId, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, rental.getPickUpLocation(), rental.getReturnLocation(), rental.getType(), rental.getId(), rental.getCustomerId(), rental.getStartDate(), rental.getEndDate());
    }

    public Rental getRentalById(int id) {
        String sql = "SELECT * FROM rentals WHERE id = ?";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void update(Rental rental) {
        String sql = "UPDATE rentals SET pickUpLocation=?, returnLocation=?, type=?, customerId=?, startDate=?, endDate=? WHERE id=?";
        jdbcTemplate.update(sql, rental.getPickUpLocation(), rental.getReturnLocation(), rental.getType(), rental.getCustomerId(), rental.getStartDate(), rental.getEndDate(), rental.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM rentals WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    public List<Rental> getAll() {
        String sql = "SELECT * FROM rentals";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
