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
        String sql = "INSERT INTO users (name, username, password, email, id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void update(User user) {
        String sql = "UPDATE users SET name=?, username=?, password=?, email=? WHERE id=?";
        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, username);
    }
}
