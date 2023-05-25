package com.voronina.gateway.controllers.rest;

import com.voronina.api.PlaceService;
import com.voronina.api.domain.Place;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/places")
public class PlaceRestController {

    private final PlaceService placeService;

    public PlaceRestController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public Iterable<Place> showAll() {
        return placeService.readAll();
    }

    @GetMapping(path = "/{id}")
    public Place showUpdateForm(@PathVariable String id) {
        return placeService.readOne(id);
    }

    @PostMapping
    public Place addOne(@RequestBody @Valid Place place) {
        return placeService.create(place);
    }

    @PutMapping("/{id}")
    public Place update(@RequestBody @Valid Place place, @PathVariable String id) {
        place.setId(id);
        return placeService.update(place);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        placeService.delete(id);
    }
}

