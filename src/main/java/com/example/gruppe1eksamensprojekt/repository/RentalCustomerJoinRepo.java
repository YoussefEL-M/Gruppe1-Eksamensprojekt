package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.model.RentalCustomerJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class RentalCustomerJoinRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<RentalCustomerJoin> getAll() {
        String sql = "SELECT rental.id, rental.customer_id, rental.car_id, rental.user_id, rental.pickUpLocation, rental.returnLocation, rental.type, rental.startDate, rental.endDate, rental.status, customer.name, customer.telnr, customer.email, customer.address, customer.birthDate " +
                "FROM rental " +
                "LEFT JOIN customer " +
                "ON rental.customer_id = customer.id";
        RowMapper<RentalCustomerJoin> rowMapper = new BeanPropertyRowMapper<>(RentalCustomerJoin.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RentalCustomerJoin> getAllByUserId(int id) {
        String sql = "SELECT rental.id, rental.customer_id, rental.car_id, rental.user_id, rental.pickUpLocation, rental.returnLocation, rental.type, rental.startDate, rental.endDate, rental.status, customer.name, customer.telnr, customer.email, customer.address, customer.birthDate " +
                "FROM rental " +
                "LEFT JOIN customer " +
                "ON rental.customer_id = customer.id WHERE user_id = ?";
        RowMapper<RentalCustomerJoin> rowMapper = new BeanPropertyRowMapper<>(RentalCustomerJoin.class);
        return jdbcTemplate.query(sql, rowMapper, id);
    }


    public RentalCustomerJoin getRentalByCar(int id) {
        String sql = "SELECT rental.id, rental.customer_id, rental.car_id, rental.user_id, rental.pickUpLocation, rental.returnLocation, rental.type, rental.startDate, rental.endDate, rental.status, customer.name, customer.telnr, customer.email, customer.address, customer.birthDate " +
                "FROM rental " +
                "LEFT JOIN customer " +
                "ON rental.customer_id = customer.id "+
                "WHERE car_id = ? and status='CURRENT' ";

        RowMapper<RentalCustomerJoin> rowMapper = new BeanPropertyRowMapper<>(RentalCustomerJoin.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


}
