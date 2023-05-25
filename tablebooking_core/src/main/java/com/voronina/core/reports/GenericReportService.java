package com.voronina.core.reports;

import com.voronina.api.BookingService;
import com.voronina.api.GuestService;
import com.voronina.api.ReportService;

import com.voronina.api.domain.Booking;
import com.voronina.api.domain.Guest;
import org.slf4j.Logger;

public abstract class GenericReportService implements ReportService {

    private final Logger log;
    private final GuestService guestService;
    private final BookingService bookingService;

    protected GenericReportService(Logger logger, GuestService guestService, BookingService bookingService) {
        log = logger;
        this.guestService = guestService;
        this.bookingService = bookingService;
    }

    @Override
    public String generateReportByBookingName(String name) {
        log.debug("Generating report by booking name={}", name);
        var booking = bookingService.readOneByName(name);
        var report = marshallBooking(booking);
        log.debug("Generated report by booking name={}, report={}", name, report);
        return report;
    }

    @Override
    public String generateReportByGuestName(String name) {
        log.debug("Generating report by guest name={}", name);
        var guest = guestService.readOneByName(name);
        var report = marshallGuest(guest);
        log.debug("Generated report by guest name={}, report={}", name, report);
        return report;
    }

    public abstract String marshallGuest(Guest guest);

    public abstract String marshallBooking(Booking booking);

}
