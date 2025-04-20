package com.university.pos.posbackend.reporting.controller;

import com.university.pos.posbackend.reporting.model.CustomerReportData;
import com.university.pos.posbackend.reporting.model.FinancialReportData;
import com.university.pos.posbackend.reporting.model.InventoryReportData;
import com.university.pos.posbackend.reporting.model.SalesReportData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final DefaultReportService reportService;

    @Autowired
    public ReportController(DefaultReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/sales")
    public ResponseEntity<List<SalesReportData>> getSalesReports(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "groupBy", required = false) String groupBy
    ) {
        List<SalesReportData> salesReports = reportService.generateSalesReports(startDate, endDate, groupBy);
        return ResponseEntity.ok(salesReports);
    }

    @GetMapping("/inventory/low-stock")
    public ResponseEntity<List<InventoryReportData>> getLowStockReports() {
        List<InventoryReportData> lowStockReports = reportService.generateLowStockReports();
        return ResponseEntity.ok(lowStockReports);
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryReportData>> getInventoryReports() {
        List<InventoryReportData> inventoryReports = reportService.generateInventoryReports();
        return ResponseEntity.ok(inventoryReports);
    }

    @GetMapping("/financial")
    public ResponseEntity<List<FinancialReportData>> getFinancialReports(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate
    ) {
        List<FinancialReportData> financialReports = reportService.generateFinancialReports(startDate, endDate);
        return ResponseEntity.ok(financialReports);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerReportData>> getCustomerReports() {
        List<CustomerReportData> customerReports = reportService.generateCustomerReports();
        return ResponseEntity.ok(customerReports);
    }
}