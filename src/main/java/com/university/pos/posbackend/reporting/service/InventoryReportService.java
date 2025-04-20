package com.university.pos.posbackend.reporting.service;


import com.university.pos.posbackend.reporting.model.InventoryReportData;
import org.springframework.stereotype.Service;
import com.university.pos.posbackend.reporting.model.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class InventoryReportService implements DefaultReportService {

    @Override
    public List<SalesReportData> generateSalesReports(LocalDate startDate, LocalDate endDate, String groupBy) {
        throw new UnsupportedOperationException("InventoryReportService does not generate sales reports.");
    }

    @Override
    public List<InventoryReportData> generateLowStockReports() {
        return List.of(new InventoryReportData("Product B", 5, 5.00));
    }

    @Override
    public List<InventoryReportData> generateInventoryReports() {
        return List.of(
                new InventoryReportData("Product A", 50, 10.00),
                new InventoryReportData("Product C", 120, 25.50)
        );
    }

    @Override
    public List<FinancialReportData> generateFinancialReports(LocalDate startDate, LocalDate endDate) {
        throw new UnsupportedOperationException("InventoryReportService does not generate financial reports.");
    }

    @Override
    public List<CustomerReportData> generateCustomerReports() {
        throw new UnsupportedOperationException("InventoryReportService does not generate customer reports.");
    }

    @Override
    public List<Map<String, Object>> generateCustomReport(CustomReportRequest request) {
        throw new UnsupportedOperationException("InventoryReportService does not generate custom reports.");
    }
}