package com.voronina.gateway.controllers.internal;

import com.voronina.api.GuestService;
import com.voronina.api.ReportService;
import com.voronina.api.domain.Guest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final GuestService guestService;
    private final Map<MimeType, ReportService> reportServiceMap;

    public GuestController(GuestService guestService, Map<MimeType, ReportService> reportServiceMap) {
        this.guestService = guestService;
        this.reportServiceMap = reportServiceMap;
    }

    @GetMapping
    public String showAll(Model model) {
        var guests = guestService.readAll();
        model.addAttribute("guests", guests);
        model.addAttribute("formats", reportServiceMap.keySet());
        return "guests/guests";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        var guest = guestService.readOne(id);
        model.addAttribute("guest", guest);
        return "guests/update-guest";
    }

    @PostMapping
    public String addOne(@Valid Guest guest, BindingResult result) {
        if (result.hasErrors()) {
            return "guests/add-guest";
        }
        guestService.create(guest);
        return "redirect:/guests";
    }

    @GetMapping(path = "/add")
    public String showCreateForm() {
        return "guests/add-guest";
    }

    @PutMapping("/{id}")
    public String update(@Valid Guest guest, @PathVariable String id, BindingResult result) {
        if (result.hasErrors()) {
            guest.setId(id);
            return "guests/update-guest";
        }
        guestService.update(guest);
        return "redirect:/guests";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        guestService.delete(id);
        return "redirect:/guests";
    }

    @GetMapping("/report")
    public String getReportByGuestName(@RequestParam("name") String name,
                                       @RequestParam("type") MimeType type,
                                       Model model) {
        var reportService = reportServiceMap.get(type);
        var report = reportService.generateReportByGuestName(name);
        model.addAttribute("report", report);
        model.addAttribute("name", name);
        return "guests/guest-report";
    }
}
