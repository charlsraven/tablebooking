package com.voronina.core.reports;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.voronina.api.BookingService;
import com.voronina.api.GuestService;
import com.voronina.api.domain.Booking;
import com.voronina.api.domain.Guest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import static org.springframework.util.MimeTypeUtils.APPLICATION_XML;

@Slf4j
@Service
public class XmlReportService extends GenericReportService {

    private final XmlMapper xmlMapper;

    public XmlReportService(GuestService guestService, BookingService bookingService, XmlMapper xmlMapper) {
        super(log, guestService, bookingService);
        this.xmlMapper = xmlMapper;
    }

    @Override
    public MimeType getType() {
        return APPLICATION_XML;
    }

    @Override
    @SneakyThrows
    public String marshallGuest(Guest guest) {
        return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(guest);
    }

    @Override
    @SneakyThrows
    public String marshallBooking(Booking booking) {
        return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
    }
}
