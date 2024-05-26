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

//Youssef
@Repository
public class ReportRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //Metode der henter alle rapporter i databasen
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

    //Metode der opretter rapporter i databasen
    public void create(Report report) {
        String sql = "INSERT INTO report (rentalId, user_id, title, date, comment, treatment) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, report.getRentalId(), report.getUser_id(), report.getTitle(), report.getDate(), report.getComment(), report.getTreatment());
    }

    //Metode der opdatere rapporter i databasen
    public void update(Report report) {
        String sql = "UPDATE report SET rentalId=?, title=?, date=?, comment=?, treatment=? WHERE id=?";
        jdbcTemplate.update(sql, report.getRentalId(), report.getTitle(), report.getDate(), report.getComment(), report.getTreatment(), report.getId());
    }

    //Metode der sletter rapporter ved deres ID i databasen
    public void delete(int id) {
        String sql = "DELETE FROM damages WHERE report_id = ?";
        jdbcTemplate.update(sql, id);
        String sql2 = "DELETE FROM report WHERE id = ?";
        jdbcTemplate.update(sql2, id);
    }

    //Metode for at henter rapporter ved deres ID i databasen
    public Report getReportById(int id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM report WHERE id = ?";
        RowMapper<Report> rowMapper = new BeanPropertyRowMapper<>(Report.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    //Bjarke
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

    //Metode for at hente skader i rapporter ved rapport ID
    public List<Damages> getDamagesByReportId(int reportId) {
        String sql = "SELECT * FROM damages WHERE report_id = ?";
        RowMapper<Damages> rowMapper = new BeanPropertyRowMapper<>(Damages.class);
        return jdbcTemplate.query(sql, rowMapper, reportId);
    }

    //Bjarke
    public void deleteDamagesByReportId(int id){
        String sql = "DELETE FROM damages WHERE report_id=?";
        jdbcTemplate.update(sql,id);
    }

}
