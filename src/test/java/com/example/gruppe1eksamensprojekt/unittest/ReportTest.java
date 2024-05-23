package com.example.gruppe1eksamensprojekt.unittest;


import com.example.gruppe1eksamensprojekt.model.Damages;
import com.example.gruppe1eksamensprojekt.model.Rental;
import com.example.gruppe1eksamensprojekt.model.Report;
import com.example.gruppe1eksamensprojekt.repository.RentalRepo;
import com.example.gruppe1eksamensprojekt.repository.ReportRepo;
import com.example.gruppe1eksamensprojekt.service.ReportService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//Youssef
@SpringBootTest
public class ReportTest {


    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepo reportRepo;

    private Report report;


    @BeforeEach
    void setup(){


        //Laver en rapport
        report = new Report();
        report.setRentalId(1);
        report.setUser_id(1);
        report.setTitle("Test Report");
        report.setDate(LocalDate.now());
        report.setTreatment("Treatment");
        report.setComment("Comment");
        Map<String, Double> damages = new HashMap<>();
        damages.put("Scratch", 100.0);
        report.setDamages(damages);
        reportService.createReport(report);
        report.setId(reportService.lastId());
    }

    @AfterEach
    void teardown(){
        reportService.deleteReport(report.getId());
    }

    @Test
    void createReportTest() {
        // Arrange
        Report newReport = new Report();
        newReport.setRentalId(1);
        newReport.setUser_id(1);
        newReport.setTitle("Test Report");
        newReport.setDate(LocalDate.of(2024,6,17));
        newReport.setComment("Test Comment");
        newReport.setTreatment("Test Treatment");

        // Act
        reportService.createReport(newReport);

        System.out.println("New Report ID: " + newReport.getId());

        // Assert
        assertNotNull(reportService.getReportById(newReport.getId()));
        assertEquals("Test Report", newReport.getTitle());
        assertEquals("Test Comment", newReport.getComment());
    }


    @Test
    void deleteReportTest() {
        // Arrange
        int idToDelete = report.getId();

        // Act
        reportService.deleteReport(idToDelete);

        // Assert
        assertNull(reportService.getReportById(idToDelete));

    }

    @Test
    void lastIdTest() {
        // Act
        int result = reportService.lastId();

        // Assert
        assertEquals(report.getId(), result);
    }


    @Test
    void getReportByIdNotFoundTest() {
        // Act
        Report result = reportService.getReportById(9999);

        // Assert
        assertNull(result);
    }

    @Test
    void getAllTest() {
        // Act
        List<Report> result = reportService.getAll();

        // Assert
        assertFalse(result.isEmpty());
    }
    @Test
    void updateReportTest() {
        // Arrange
        report.setTitle("Updated Report");
        report.setTreatment("Updated Treatment");
        report.setComment("Updated Comment");
        Map<String, Double> updatedDamages = new HashMap<>();
        updatedDamages.put("Updated Damage", 200.0);
        report.setDamages(updatedDamages);

        // Act
        reportService.updateReport(report);

        // Assert
        Report updatedReport = reportService.getReportById(report.getId());
        assertEquals("Updated Report", updatedReport.getTitle());
        assertEquals("Updated Treatment", updatedReport.getTreatment());
        assertEquals("Updated Comment", updatedReport.getComment());
        assertEquals(200.0, updatedReport.getDamages().get("Updated Damage"));
    }

    @Test
    void submitReportTest() {
        // Arrange
        String rental = "1. Rental description";
        int userId = 1;
        String reportTitle = "Test Report";
        LocalDate reportDate = LocalDate.now();
        String comment = "Test Comment";
        String treatment = "Test Treatment";
        String report0damage = "Scratch";
        String report1damage = "";
        String report2damage = "";
        String report3damage = "";
        String report4damage = "";
        String report0price = "100";
        String report1price = "";
        String report2price = "";
        String report3price = "";
        String report4price = "";
        String status = "AVAILABLE";
        RedirectAttributes redirectAttributes = null;

        // Act
        String result = reportService.submitReport(rental, userId, reportTitle, reportDate, treatment, comment, report0damage, report1damage, report2damage, report3damage, report4damage, report0price, report1price, report2price, report3price, report4price, status, redirectAttributes);

        // Assert
        assertEquals("redirect:/damage", result);
        Report submittedReport = reportService.getReportById(reportService.lastId());
        assertNotNull(submittedReport);
        assertEquals(reportTitle, submittedReport.getTitle());
        assertEquals(comment, submittedReport.getComment());
        assertEquals(100.0, submittedReport.getDamages().get("Scratch"));
    }

    @Test
    void getDamagesByReportIDTest() {
        // Arrange
        int reportId = report.getId();
        Map<String, Double> damages = new HashMap<>();
        damages.put("Scratch", 100.0);
        report.setDamages(damages);
        reportService.updateReport(report);

        // Act
        List<Damages> actualDamages = reportService.getDamagesByReportID(reportId);

        // Assert
        assertEquals(1, actualDamages.size());
        assertEquals("Scratch", actualDamages.get(0).getDamage());
        assertEquals(100.0, actualDamages.get(0).getPrice());
    }

    @Test
    void populateDamagesListTest() {
        // Arrange
        Report report1 = new Report();
        report1.setRentalId(2);
        report1.setUser_id(1);
        report1.setTitle("Report 1");
        report1.setDate(LocalDate.now());
        Map<String, Double> damages1 = new HashMap<>();
        damages1.put("Scratch", 100.0);
        report1.setDamages(damages1);
        reportService.createReport(report1);
        report1.setId(reportService.lastId());

        Report report2 = new Report();
        report2.setRentalId(3);
        report2.setUser_id(1);
        report2.setTitle("Report 2");
        report2.setDate(LocalDate.now());
        Map<String, Double> damages2 = new HashMap<>();
        damages2.put("Dent", 150.0);
        report2.setDamages(damages2);
        reportService.createReport(report2);
        report2.setId(reportService.lastId());

        List<Report> reports = List.of(report1, report2);

        // Act
        reportService.populateDamages(reports);

        // Assert
        assertEquals(100.0, reports.get(0).getDamages().get("Scratch"));
        assertEquals(150.0, reports.get(1).getDamages().get("Dent"));
    }

    @Test
    void populateDamagesSingleTest() {
        // Arrange
        Report report = new Report();
        report.setRentalId(1);
        report.setUser_id(1);
        report.setTitle("Single Report");
        report.setDate(LocalDate.now());
        Map<String, Double> damages = new HashMap<>();
        damages.put("Scratch", 100.0);
        report.setDamages(damages);
        reportService.createReport(report);
        report.setId(reportService.lastId());

        // Act
        reportService.populateDamages(report);

        // Assert
        assertEquals(100.0, report.getDamages().get("Scratch"));
    }

}
