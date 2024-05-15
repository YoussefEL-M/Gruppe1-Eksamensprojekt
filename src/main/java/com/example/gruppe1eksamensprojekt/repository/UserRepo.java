package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

//Youssef
@Repository
public class UserRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(User user) {
        String sql = "INSERT INTO user (name, username, password, email, type) VALUES (?, ?, ?, ?, ?)";

//        if(user.getType().equals("Dataregistrering")) {
//
//            String statementForRental = "GRANT SELECT, INSERT, UPDATE, DELETE ON car_rental_gruppe1.rental TO user";
//            String statementForCustomer = "GRANT SELECT, INSERT, UPDATE, DELETE ON car_rental_gruppe1.customer TO user";
//            String statementForCar = "GRANT SELECT, INSERT, UPDATE, DELETE ON car_rental_gruppe1.car TO user";
//            String statementForCarId = "GRANT SELECT, INSERT, UPDATE, DELETE ON car_rental_gruppe1.carIdentification TO user";
//
//            RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
//            jdbcTemplate.queryForObject(statementForRental, rowMapper, user);
//            jdbcTemplate.queryForObject(statementForCustomer, rowMapper, user);
//            jdbcTemplate.queryForObject(statementForCar, rowMapper, user);
//            jdbcTemplate.queryForObject(statementForCarId, rowMapper, user);
//        }
//
//        if(user.getType().equals("SkadeOgUdbedring")) {
//
//            String statementForCar = "GRANT SELECT, UPDATE ON car_rental_gruppe1.car TO user";
//            String statementForReport = "GRANT SELECT, UPDATE, INSERT, DELETE ON car_rental_gruppe1.report TO user";
//
//            RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
//            jdbcTemplate.queryForObject(statementForCar, rowMapper, user);
//            jdbcTemplate.queryForObject(statementForReport, rowMapper, user);
//        }

        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getType());

    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void update(User user) {
        String sql = "UPDATE user SET name=?, username=?, password=?, email=? WHERE id=?";
        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, username);
    }
}
