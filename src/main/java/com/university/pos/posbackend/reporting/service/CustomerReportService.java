package com.university.pos.posbackend.reporting.service;
import com.university.pos.posbackend.reporting.model.CustomerReportData;
import com.university.pos.posbackend.reporting.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerReportService implements ReportService {

    @Override
    public List<SalesReportData> generateSalesReports(LocalDate startDate, LocalDate endDate, String groupBy) {
        throw new UnsupportedOperationException("CustomerReportService does not generate sales reports.");
    }

    @Override
    public List<InventoryReportData> generateLowStockReports() {
        throw new UnsupportedOperationException("CustomerReportService does not generate inventory reports.");
    }

    @Override
    public List<InventoryReportData> generateInventoryReports() {
        throw new UnsupportedOperationException("CustomerReportService does not generate inventory reports.");
    }

    @Override
    public List<FinancialReportData> generateFinancialReports(LocalDate startDate, LocalDate endDate) {
        throw new UnsupportedOperationException("CustomerReportService does not generate financial reports.");
    }

    @Override
    public List<CustomerReportData> generateCustomerReports() {
        return List.of(
                new CustomerReportData("John Doe", "john.doe@example.com", 15),
                new CustomerReportData("Jane Smith", "jane.smith@example.com", 22)
        );
    }
}