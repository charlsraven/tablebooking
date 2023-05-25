package com.voronina.api;

import org.springframework.util.MimeType;

public interface ReportService {

    String generateReportByBookingName(String name);

    String generateReportByGuestName(String name);

    MimeType getType();
}
