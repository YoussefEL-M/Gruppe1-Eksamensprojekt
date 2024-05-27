package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.BusinessUser;
import com.example.gruppe1eksamensprojekt.model.DamageUser;
import com.example.gruppe1eksamensprojekt.model.DataUser;
import com.example.gruppe1eksamensprojekt.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//Youssef
@Repository
public class UserRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Løsning til mapping af nedarvede objekter fundet på StackOverflow og justeret.
    // https://stackoverflow.com/questions/1834341/spring-jdbc-rowmapper-with-class-hierarchies
    // - Severin

    final RowMapper<User> dataMapper = new BeanPropertyRowMapper(DataUser.class);
    final RowMapper<User> damageMapper = new BeanPropertyRowMapper(DamageUser.class);
    final RowMapper<User> businessMapper = new BeanPropertyRowMapper(BusinessUser.class);

    //Severin
    //RowMapper klasse, som benytter en switch til at mappe brugere til forskellige subklasser.
    RowMapper<User> mapperSwitch = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            String userType = rs.getString("type");
            switch (userType){
                case ("Dataregistrering") -> {
                    return dataMapper.mapRow(rs, rowNum);
                }
                case ("SkadeOgUdbedring") -> {
                    return damageMapper.mapRow(rs, rowNum);
                }
                case ("Forretningsudvikler") -> {
                    return businessMapper.mapRow(rs, rowNum);
                }
                default -> {
                    break;
                }
            }
            return null;
        }
    };

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


    public User getUserById(int id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, mapperSwitch, id);
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
        return jdbcTemplate.query(sql, mapperSwitch);
    }

    public User getUserByUsername(String username) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, mapperSwitch, username);
    }
}
