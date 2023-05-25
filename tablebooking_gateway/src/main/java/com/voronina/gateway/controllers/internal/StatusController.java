package com.voronina.gateway.controllers.internal;

import com.voronina.api.BookingService;
import com.voronina.api.GuestService;
import com.voronina.api.StatusService;
import com.voronina.api.domain.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusService;
    private final BookingService bookingService;

    public StatusController(StatusService statusService, BookingService bookingService) {
        this.statusService = statusService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public String showAll(Model model) {
        var statusList = statusService.readAll();
        model.addAttribute("statusList", statusList);
        return "status/status";
    }

    @GetMapping(path = "/add")
    public String showCreateForm(Model model) {
        var bookings = bookingService.readAll();
        model.addAttribute("bookings", bookings);
        return "status/add-status";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        var bookings = bookingService.readAll();
        var status = statusService.readOne(id);
        model.addAttribute("bookings", bookings);
        model.addAttribute("status", status);
        return "status/update-status";
    }

    @PostMapping
    public String addOne(@Valid Status status, BindingResult result) {
        if (result.hasErrors()) {
            return "status/add-status";
        }
        statusService.create(status);
        return "redirect:/status";
    }

    @PutMapping("/{id}")
    public String update(@Valid Status status, @PathVariable String id, BindingResult result) {
        if (result.hasErrors()) {
            status.setId(id);
            return "status/update-status";
        }
        statusService.update(status);
        return "redirect:/status";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        statusService.delete(id);
        return "redirect:/status";
    }
}
