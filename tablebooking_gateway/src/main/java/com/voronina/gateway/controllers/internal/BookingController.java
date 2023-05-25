package com.voronina.gateway.controllers.internal;

import com.voronina.api.BookingService;
import com.voronina.api.GuestService;
import com.voronina.api.PlaceService;
import com.voronina.api.ReportService;
import com.voronina.api.domain.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final PlaceService placeService;
    private final GuestService guestService;
    private final Map<MimeType, ReportService> reportServiceMap;

    public BookingController(BookingService bookingService, PlaceService placeService,
                             GuestService guestService, Map<MimeType, ReportService> reportServiceMap) {
        this.bookingService = bookingService;
        this.guestService = guestService;
        this.reportServiceMap = reportServiceMap;
        this.placeService = placeService;
    }

    @GetMapping
    public String showAll(Model model) {
        var bookings = bookingService.readAll();
        model.addAttribute("bookings", bookings);
        model.addAttribute("formats", reportServiceMap.keySet());
        return "bookings/bookings";
    }

    @GetMapping(path = "/add")
    public String showCreateForm(Model model) {
        var guests = guestService.readAll();
        var places = placeService.readAll();
        model.addAttribute("guests", guests);
        model.addAttribute("places", places);
        return "bookings/add-booking";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        var booking = bookingService.readOne(id);
        var guests = guestService.readAll();
        var places = placeService.readAll();
        model.addAttribute("booking", booking);
        model.addAttribute("guests", guests);
        model.addAttribute("places", places);
        return "bookings/update-booking";
    }

    @PostMapping
    public String addOne(@Valid Booking booking) {
        bookingService.create(booking);
        return "redirect:/bookings";
    }

    @PutMapping("/{id}")
    public String update(@Valid Booking booking, @PathVariable String id, BindingResult result) {
        if (result.hasErrors()) {
            booking.setId(id);
            return "bookings/update-booking";
        }
        bookingService.update(booking);
        return "redirect:/bookings";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        bookingService.delete(id);
        return "redirect:/bookings";
    }

    @GetMapping("/report")
    public String getReportByBookingName(@RequestParam("name") String name,
                                         @RequestParam("type") MimeType type, Model model) {
        var reportService = reportServiceMap.get(type);
        var report = reportService.generateReportByBookingName(name);
        model.addAttribute("report", report);
        model.addAttribute("name", name);
        return "bookings/booking-report";
    }
}
