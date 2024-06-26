package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    //Metode der opretter lejeaftaler i database
    public void create(Rental rental) {
        String sql = "INSERT INTO rental (pickUpLocation, returnLocation, type, customer_id, startDate, endDate, car_id, status, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, rental.getPickUpLocation(), rental.getReturnLocation(), rental.getType(), rental.getCustomerId(), rental.getStartDate(), rental.getEndDate(), rental.getCarId(), rental.getStatus(), rental.getUserID());
    }

    //Metoden der henter lejeaftaler ved deres ID i databasen
    public Rental getRentalById(int id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM rental WHERE id = ?";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    //Metode der opdatere lejeaftaler i databasen
    public void update(Rental rental) {
        String sql = "UPDATE rental SET pickUpLocation=?, returnLocation=?, type=?, customer_id=?, startDate=?, endDate=?, status=?, car_id=? WHERE id=?";
        jdbcTemplate.update(sql, rental.getPickUpLocation(), rental.getReturnLocation(), rental.getType(), rental.getCustomerId(), rental.getStartDate(), rental.getEndDate(), rental.getStatus(), rental.getCarId(), rental.getId());
    }

    //Metode der sletter lejeaftaler efter ID i databasen
    public void delete(int id) {
        String sql = "DELETE FROM rental WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    //Metode der henter alle lejeaftaler
    public List<Rental> getAll() {
        String sql = "SELECT * FROM rental";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    //Bjarke
    //Henter de rentals fra hvor en bil sidst er tilbageleveret
    public List<Rental> getCurrentRentals() {
        String sql = "SELECT * FROM rental WHERE status = 'CURRENT'";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    //Bjarke
    public Rental getCurrentRentalByCarID( int id) {
        String sql = "SELECT * FROM rental WHERE status = 'CURRENT' and car_id = ?";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }




}
