package com.university.pos.posbackend.reporting.service;

import com.university.pos.posbackend.reporting.model.*;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<SalesReportData> generateSalesReports(LocalDate startDate, LocalDate endDate, String groupBy);

    List<InventoryReportData> generateLowStockReports();

    List<InventoryReportData> generateInventoryReports();

    List<FinancialReportData> generateFinancialReports(LocalDate startDate, LocalDate endDate);

    List<CustomerReportData> generateCustomerReports();
}