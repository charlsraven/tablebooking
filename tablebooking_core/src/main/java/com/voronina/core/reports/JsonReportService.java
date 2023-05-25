package com.voronina.core.reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voronina.api.BookingService;
import com.voronina.api.GuestService;
import com.voronina.api.domain.Booking;
import com.voronina.api.domain.Guest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Slf4j
@Service
public class JsonReportService extends GenericReportService {

    private final ObjectMapper objectMapper;

    public JsonReportService(GuestService guestService, BookingService bookingService, ObjectMapper objectMapper) {
        super(log, guestService, bookingService);
        this.objectMapper = objectMapper;
    }

    @Override
    public MimeType getType() {
        return APPLICATION_JSON;
    }

    @Override
    @SneakyThrows
    public String marshallGuest(Guest guest) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(guest);
    }

    @Override
    @SneakyThrows
    public String marshallBooking(Booking booking) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
    }
}
