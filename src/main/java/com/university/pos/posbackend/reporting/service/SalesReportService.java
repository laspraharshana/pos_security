package com.university.pos.posbackend.reporting.service;

import com.university.pos.posbackend.reporting.model.SalesReportData;
import org.springframework.stereotype.Service;
import com.university.pos.posbackend.reporting.model.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class SalesReportService implements DefaultReportService {

    @Override
    public List<SalesReportData> generateSalesReports(LocalDate startDate, LocalDate endDate, String groupBy) {
        System.out.println("Generating sales reports...");
        return List.of(new SalesReportData("Product A", 10, 100.00));
    }

    @Override
    public List<InventoryReportData> generateLowStockReports() {
        throw new UnsupportedOperationException("SalesReportService does not generate inventory reports.");
    }

    @Override
    public List<InventoryReportData> generateInventoryReports() {
        throw new UnsupportedOperationException("SalesReportService does not generate inventory reports.");
    }

    @Override
    public List<FinancialReportData> generateFinancialReports(LocalDate startDate, LocalDate endDate) {
        throw new UnsupportedOperationException("SalesReportService does not generate financial reports.");
    }

    @Override
    public List<CustomerReportData> generateCustomerReports() {
        throw new UnsupportedOperationException("SalesReportService does not generate customer reports.");
    }

    public List<SalesReportData> generateSalesReport() {
        return List.of(
                new SalesReportData("Product A", 100, 500.00),
                new SalesReportData("Product B", 50, 300.00)
        );
    }

    @Override
    public List<Map<String, Object>> generateCustomReport(CustomReportRequest request) {
        throw new UnsupportedOperationException("SalesReportService does not generate custom reports.");
    }
}