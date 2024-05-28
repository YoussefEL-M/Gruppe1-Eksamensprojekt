package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    //Metode for at oprette bruger i databasen
    public void create(Customer customer) {
        String sql = "INSERT INTO customer (name, telnr, address, birthdate, id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getName(), customer.getTelnr(), customer.getAddress(), customer.getBirthdate(), customer.getId());
    }

    //Metode for at hente kunde ved deres ID i databasen
    public Customer getCustomerById(int id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM customer WHERE id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    //Metode der opdatere kundes oplysninger i databasen
    public void update(Customer customer) {
        String sql = "UPDATE customer SET name=?, telnr=?, address=?, birthdate=? WHERE id=?";
        jdbcTemplate.update(sql, customer.getName(), customer.getTelnr(), customer.getAddress(), customer.getBirthdate(), customer.getId());
    }

    //Metode for at slette kunde i databasen
    public void delete(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    //Metode der henter alle kunder i databasen
    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.query(sql, rowMapper);
    }


}
