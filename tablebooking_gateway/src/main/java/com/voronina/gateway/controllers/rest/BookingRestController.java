package com.voronina.gateway.controllers.rest;

import com.voronina.api.BookingService;
import com.voronina.api.ReportService;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import com.voronina.api.domain.Booking;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingRestController {

    private final BookingService bookingService;
    private final Map<MimeType, ReportService> reportServiceMap;

    public BookingRestController(BookingService bookingService,
                                 Map<MimeType, ReportService> reportServiceMap) {
        this.bookingService = bookingService;
        this.reportServiceMap = reportServiceMap;
    }

    @GetMapping
    public Iterable<Booking> showAll() {
        return bookingService.readAll();
    }

    @GetMapping(path = "/{id}")
    public Booking showUpdateForm(@PathVariable String id) {
        return bookingService.readOne(id);
    }

    @PostMapping
    public Booking addOne(@RequestBody @Valid Booking booking) {
        return bookingService.create(booking);
    }

    @PutMapping("/{id}")
    public Booking update(@RequestBody @Valid Booking booking,
                          @PathVariable String id) {
        booking.setId(id);
        return bookingService.update(booking);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        bookingService.delete(id);
    }

    @GetMapping("/report")
    public String getReportByBookingName(@RequestParam("name") String name,
                                         @RequestParam("type") MimeType type) {
        var reportService = reportServiceMap.get(type);
        return reportService.generateReportByBookingName(name);
    }
}

