package com.voronina.gateway.controllers.rest;

import com.voronina.api.GuestService;
import com.voronina.api.ReportService;
import com.voronina.api.domain.Guest;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v1/guests")
public class GuestRestController {

    private final GuestService guestService;
    private final Map<MimeType, ReportService> reportServiceMap;

    public GuestRestController(GuestService guestService,
                               Map<MimeType, ReportService> reportServiceMap) {
        this.guestService = guestService;
        this.reportServiceMap = reportServiceMap;
    }

    @GetMapping
    public Iterable<Guest> showAll() {
        return guestService.readAll();
    }

    @GetMapping(path = "/{id}")
    public Guest showUpdateForm(@PathVariable String id) {
        return guestService.readOne(id);
    }

    @PostMapping
    public Guest addOne(@RequestBody @Valid Guest guest) {
        return guestService.create(guest);
    }

    @PutMapping("/{id}")
    public Guest update(@RequestBody @Valid Guest guest, @PathVariable String id) {
        guest.setId(id);
        return guestService.update(guest);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        guestService.delete(id);
    }

    @GetMapping("/report")
    public String getReportByGuestName(@RequestParam("name") String name,
                                       @RequestParam("type") MimeType type) {
        var reportService = reportServiceMap.get(type);
        return reportService.generateReportByGuestName(name);
    }
}

