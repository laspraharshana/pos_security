package com.university.pos.posbackend.reporting.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReportUtils {

    public String formatDate(Date date) {
        return date != null ? date.toString() : "";
    }
}