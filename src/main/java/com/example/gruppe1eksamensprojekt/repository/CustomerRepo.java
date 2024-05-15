package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Youssef
@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(Customer customer) {
        String sql = "INSERT INTO customer (name, telnr, address, birthdate, id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getName(), customer.getTelnr(), customer.getAddress(), customer.getBirthdate(), customer.getId());
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void update(Customer customer) {
        String sql = "UPDATE customer SET name=?, telnr=?, address=?, birthdate=? WHERE id=?";
        jdbcTemplate.update(sql, customer.getName(), customer.getTelnr(), customer.getAddress(), customer.getBirthdate(), customer.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.query(sql, rowMapper);
    }


}
