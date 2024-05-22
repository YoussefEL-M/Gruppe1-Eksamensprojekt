package com.example.gruppe1eksamensprojekt.repository;

import com.example.gruppe1eksamensprojekt.model.Damages;
import com.example.gruppe1eksamensprojekt.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    //Clara
    public List<Report> getYourReports(int userId) {
        String sql = "SELECT * FROM report WHERE user_id = ?";
        RowMapper<Report> rowMapper = new BeanPropertyRowMapper<>(Report.class);
        return jdbcTemplate.query(sql, rowMapper, userId);
    }

    public void create(Report report) {
        String sql = "INSERT INTO report (rentalId, user_id, title, date, comment, treatment) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, report.getRentalId(), report.getUser_id(), report.getTitle(), report.getDate(), report.getComment(), report.getTreatment());
    }

    public void update(Report report) {
        String sql = "UPDATE report SET rentalId=?, title=?, date=?, comment=?, treatment=? WHERE id=?";
        jdbcTemplate.update(sql, report.getRentalId(), report.getTitle(), report.getDate(), report.getComment(), report.getTreatment(), report.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM damages WHERE report_id = ?";
        jdbcTemplate.update(sql, id);
        String sql2 = "DELETE FROM report WHERE id = ?";
        jdbcTemplate.update(sql2, id);
    }

    public Report getReportById(int id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM report WHERE id = ?";
        RowMapper<Report> rowMapper = new BeanPropertyRowMapper<>(Report.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void createDamage(int id, String damage, double price) {
        String sql = "INSERT INTO damages (report_id, damage, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id, damage, price);
    }

    //Bjarke
    //Henter den report med højeste id for at få den nyligst lavede rapport
    public int lastId() {
        String sql = "SELECT MAX(id) FROM report";
        return jdbcTemplate.queryForObject(sql, int.class);
    }

    public List<Damages> getDamagesByReportId(int reportId) {
        String sql = "SELECT * FROM damages WHERE report_id = ?";
        RowMapper<Damages> rowMapper = new BeanPropertyRowMapper<>(Damages.class);
        return jdbcTemplate.query(sql, rowMapper, reportId);
    }
    public void updateDamage(int reportId, Damages damage) {
        String sql = "UPDATE damages SET price = ? WHERE report_id = ? AND damage = ?";
        jdbcTemplate.update(sql, damage.getPrice(), reportId, damage.getDamage());
    }

    public void deleteDamagesByReportId(int id){
        String sql = "DELETE FROM damages WHERE report_id=?";
        jdbcTemplate.update(sql,id);
    }

}
