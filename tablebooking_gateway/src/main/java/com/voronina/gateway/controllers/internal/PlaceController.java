package com.voronina.gateway.controllers.internal;

import com.voronina.api.PlaceService;
import com.voronina.api.domain.Place;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public String showAll(Model model) {
        var places = placeService.readAll();
        model.addAttribute("places", places);
        return "places/places";
    }

    @GetMapping(path = "/add")
    public String showCreateForm(Model model) {
        return "places/add-place";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        var student = placeService.readOne(id);
        model.addAttribute("place", student);
        return "places/update-place";
    }

    @PostMapping
    public String addOne(@Valid Place place, BindingResult result) {
        if (result.hasErrors()) {
            return "places/add-place";
        }
        placeService.create(place);
        return "redirect:/places";
    }

    @PutMapping("/{id}")
    public String update(@Valid Place place, @PathVariable String id, BindingResult result) {
        if (result.hasErrors()) {
            place.setId(id);
            return "places/update-place";
        }
        placeService.update(place);
        return "redirect:/places";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable String id) {
        placeService.delete(id);
        return "redirect:/places";
    }
}
