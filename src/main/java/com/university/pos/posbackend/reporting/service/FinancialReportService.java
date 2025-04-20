package com.university.pos.posbackend.reporting.service;

import com.university.pos.posbackend.reporting.model.FinancialReportData;
import org.springframework.stereotype.Service;
import com.university.pos.posbackend.reporting.model.*;


import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;


@Service
public class FinancialReportService implements DefaultReportService {

    @Override
    public List<SalesReportData> generateSalesReports(LocalDate startDate, LocalDate endDate, String groupBy) {
        throw new UnsupportedOperationException("FinancialReportService does not generate sales reports.");
    }

    @Override
    public List<InventoryReportData> generateLowStockReports() {
        throw new UnsupportedOperationException("FinancialReportService does not generate inventory reports.");
    }

    @Override
    public List<InventoryReportData> generateInventoryReports() {
        throw new UnsupportedOperationException("FinancialReportService does not generate inventory reports.");
    }

    @Override
    public List<FinancialReportData> generateFinancialReports(LocalDate startDate, LocalDate endDate) {
        throw new UnsupportedOperationException("FinancialReportService does not generate financial reports with date range.");
    }

    @Override
    public List<CustomerReportData> generateCustomerReports() {
        throw new UnsupportedOperationException("FinancialReportService does not generate customer reports.");
    }

    public List<FinancialReportData> generateFinancialReport() {
        return List.of(
                new FinancialReportData("Total Revenue", new BigDecimal("15000.00")),
                new FinancialReportData("Total Expenses", new BigDecimal("5000.00")),
                new FinancialReportData("Net Profit", new BigDecimal("10000.00"))
        );
    }

    @Override
    public List<Map<String, Object>> generateCustomReport(CustomReportRequest request) {
        throw new UnsupportedOperationException("FinancialReportService does not generate custom reports.");
    }
}