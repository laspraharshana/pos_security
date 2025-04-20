package com.university.pos.posbackend.reporting.service;

import com.university.pos.posbackend.reporting.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DefaultReportService extends ReportService {
    List<Map<String, Object>> generateCustomReport(CustomReportRequest request);
}