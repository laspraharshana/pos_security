package com.university.pos.posbackend.reporting.service;

import com.university.pos.posbackend.reporting.model.SalesReportData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportExportService {

    private final SalesReportService salesReportService;

    public ReportExportService(SalesReportService salesReportService) {
        this.salesReportService = salesReportService;
    }

    public byte[] exportSalesToCsv(LocalDate startDate, LocalDate endDate, String groupBy) {
        List<SalesReportData> salesReports = salesReportService.generateSalesReports(startDate, endDate, groupBy);
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("Product Name,Quantity Sold,Total Revenue\n");

        for (SalesReportData report : salesReports) {
            csvBuilder.append(String.format("%s,%d,%.2f\n",
                    report.getProductName(),
                    report.getQuantitySold(),
                    report.getTotalRevenue()));
        }
        return csvBuilder.toString().getBytes();
    }
}