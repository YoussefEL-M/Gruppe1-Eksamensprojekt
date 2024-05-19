package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
        String sql = "INSERT INTO report (rentalId, title, date, comment, treatment) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, report.getRentalId(), report.getTitle(), report.getDate(), report.getComment(), report.getTreatment());
    }

    public void update(Report report) {
        String sql = "UPDATE report SET rentalId=?, title=?, date=?, comment=?, treatment=? WHERE id=?";
        jdbcTemplate.update(sql, report.getRentalId(), report.getTitle(), report.getDate(), report.getComment(), report.getTreatment(), report.getId());
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

    public void createDamage(int id, String damage, double price) {
        String sql = "INSERT INTO damages (report_id, damage, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id, damage, price);
    }

    public int lastId() {
        String sql = "SELECT MAX(id) FROM report";
        return jdbcTemplate.queryForObject(sql, int.class);
    }
}
