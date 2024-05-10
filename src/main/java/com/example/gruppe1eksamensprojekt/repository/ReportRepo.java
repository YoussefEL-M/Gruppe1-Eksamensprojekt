package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Report> getAll() {
        String sql = "SELECT * FROM report";
        RowMapper<Report> rowMapper = new BeanPropertyRowMapper<>(Report.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void create(Report report) {
        String sql = "INSERT INTO report (rentalId, title, date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, report.getRentalId(), report.getTitle(), report.getDate());
    }

    public void update(Report report) {
        String sql = "UPDATE report SET rentalId=?, title=?, date=? WHERE id=?";
        jdbcTemplate.update(sql, report.getRentalId(), report.getTitle(), report.getDate(), report.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM report WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Report getReportById(int id) {
        String sql = "SELECT * FROM report WHERE id = ?";
        RowMapper<Report> rowMapper = new BeanPropertyRowMapper<>(Report.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
